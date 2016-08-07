package com.t2wonderland.kurona.Models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.t2wonderland.kurona.Assets
import com.t2wonderland.kurona.Interfaces.CharacterState
import com.t2wonderland.kurona.Interfaces.HeightCalculator

class WorldRenderer(internal var batch: SpriteBatch, internal var world:

World) {
    internal var camera: OrthographicCamera
    internal var viewport: Viewport
    internal var pos: Vector2

    init {

        // initialize the camera
        this.camera = OrthographicCamera(World.WIDTH.toFloat(), World.HEIGHT.toFloat())
        this.camera.setToOrtho(false, World.WIDTH.toFloat(), World.HEIGHT.toFloat())
        viewport = FitViewport(World.WIDTH.toFloat(), World.HEIGHT.toFloat(), camera)
        pos = Vector2()
    }

    fun render() {
        Gdx.gl.glClearColor(0f, 0.3f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)

        val position = world._character.position
        if (position.x > camera.position.x) {
            camera.position.x = position.x
        }
        camera.update()
        batch.projectionMatrix = camera.combined

        renderBackground()
        renderObjects()
    }

    fun renderBackground() {
        batch.begin()
        Assets.gameBack.draw(batch)
        batch.end()
    }

    fun renderObjects() {
        batch.begin()
        renderCharacter()
        renderPlatforms()
        renderBarricades()
        renderItems()
        renderGoal()
        batch.end()
    }

    private fun renderCharacter() {
        val keyFrame = updateKeyFrame()

        val position = world._character.position
        position.y = getHeightFromUserInput() ?: position.y

        val size = world._character.size
        batch.draw(keyFrame, position.x, position.y, size.x, size.y)
    }

    private fun getHeightFromUserInput(): Float? {
        if (Gdx.input.justTouched()) {
            val touchPos = Vector3().set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)

            return HeightCalculator.Calculate(touchPos.y)
        }
        return null
    }

    private fun updateKeyFrame(): TextureRegion {
        val keyFrame: TextureRegion
        val state = world._character.characterState
        val stateTime = world._character.stateTime
        when (state) {
            CharacterState.Slow, CharacterState.Special, CharacterState.Dash -> keyFrame = Assets.kuronaRun.getKeyFrame(stateTime, true)

            else -> keyFrame = Assets.kuronaRun.getKeyFrame(stateTime, true)
        }

        return keyFrame
    }

    private fun renderPlatforms() {
        // TODO: 地面とかの構成要素を描画
    }

    private fun renderBarricades() {
        for (barricade in world.barricadeList){
            val position = barricade.position
            val size = barricade.size
            batch.draw(Assets.gameRock, position.x, position.y, size.x, size.y)
        }
    }

    private fun renderItems() {
        for (item in world.itemList) {
            val position = item.position
            val size = item.size
            batch.draw(Assets.gameCandy, position.x, position.y, size.x, size.y)
        }
    }

    private fun renderGoal() {
        // TODO: ゴールを描画
    }
}
