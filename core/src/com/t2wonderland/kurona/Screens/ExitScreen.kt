package com.t2wonderland.kurona.Screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.t2wonderland.kurona.KuronanDash

class ExitScreen(internal val game: KuronanDash) : Screen {
    internal var camera: OrthographicCamera

    init {

        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f)
    }

    fun draw(deltaTime: Float) {
        Gdx.gl.glClearColor(0f, 0.3f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        camera.update()
        game.batch.projectionMatrix = camera.combined

        game.batch.begin()
        game.font.draw(game.batch, "Welcome to Exit Screen!!", 100f, 150f)
        game.font.draw(game.batch, "Tap anywhere to begin!", 100f, 100f)
        game.batch.end()
    }

    override fun render(delta: Float) {
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
