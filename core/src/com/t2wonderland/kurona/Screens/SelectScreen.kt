package com.t2wonderland.kurona.Screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.t2wonderland.kurona.Assets
import com.t2wonderland.kurona.KuronanDash
import com.t2wonderland.kurona.Models.CharacterSelect
import com.t2wonderland.kurona.Models.OverlapChecker

class SelectScreen(internal val game: KuronanDash) : Screen {
    internal var camera: OrthographicCamera
    internal var boundsKurona: Rectangle
    internal var boundsKoma: Rectangle
    internal var boundsShishimaru: Rectangle
    internal var boundsBack: Rectangle
    internal var boundsStart: Rectangle
    internal var selectedCharacter: CharacterSelect

    init {
        camera = OrthographicCamera(KuronanDash.displayWidth.toFloat(), KuronanDash.displayHeight.toFloat())
        camera.setToOrtho(false, KuronanDash.displayWidth.toFloat(), KuronanDash.displayHeight.toFloat())

        // regions of the buttons
        boundsKurona = Assets.selectKurona.boundingRectangle
        boundsKoma = Assets.selectKoma.boundingRectangle
        boundsShishimaru = Assets.selectShishimaru.boundingRectangle
        boundsBack = Assets.selectBack.boundingRectangle
        boundsStart = Assets.selectStart.boundingRectangle

        selectedCharacter = CharacterSelect.Kurona
    }

    fun update(deltaTime: Float) {
        if (Gdx.input.isTouched(0)) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)

            if (OverlapChecker.pointInRectangle(boundsKurona, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                selectedCharacter = CharacterSelect.Kurona
                return
            }
            if (OverlapChecker.pointInRectangle(boundsKoma, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                selectedCharacter = CharacterSelect.Koma
                return
            }
            if (OverlapChecker.pointInRectangle(boundsShishimaru, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                selectedCharacter = CharacterSelect.Shishimaru
                return
            }
            if (OverlapChecker.pointInRectangle(boundsBack, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                game.screen = TitleScreen(game)
                return
            }
            if (OverlapChecker.pointInRectangle(boundsStart, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                game.screen = GameScreen(game, selectedCharacter)
                return
            }
        }
    }

    fun draw(deltaTime: Float) {
        Gdx.gl.glClearColor(0f, 0.3f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()
        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        Assets.selectKurona.draw(game.batch)
        Assets.selectKoma.draw(game.batch)
        Assets.selectShishimaru.draw(game.batch)
        Assets.selectDescription.draw(game.batch)
        Assets.selectBack.draw(game.batch)
        Assets.selectStart.draw(game.batch)
        game.batch.end()
    }

    override fun render(delta: Float) {
        update(delta)
        draw(delta)
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun show() {
    }

    override fun hide() {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun dispose() {
    }

}
