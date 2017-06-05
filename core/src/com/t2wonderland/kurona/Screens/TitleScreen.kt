package com.t2wonderland.kurona.Screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.t2wonderland.kurona.Assets
import com.t2wonderland.kurona.KuronanDash
import com.t2wonderland.kurona.Models.OverlapChecker

class TitleScreen(internal val game: KuronanDash) : Screen {
    internal var camera: OrthographicCamera
    internal var startBounds: Rectangle
    internal var scoresBounds: Rectangle
    internal var tutorialBounds: Rectangle
    internal var exitBounds: Rectangle

    init {
        this.camera = OrthographicCamera(game.Width.toFloat(), game.Height.toFloat())
        this.camera.setToOrtho(false, game.Width.toFloat(), game.Height.toFloat())

        // regions of the buttons
        this.startBounds = Assets.titleStart.boundingRectangle
        this.scoresBounds = Assets.titleScore.boundingRectangle
        this.tutorialBounds = Assets.titleTutorial.boundingRectangle
        this.exitBounds = Assets.titleExit.boundingRectangle
    }

    fun update(deltaTime: Float) {
        if (Gdx.input.isTouched(0)) {
            val touchPos = Vector3()
            touchPos.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
            camera.unproject(touchPos)

            if (OverlapChecker.pointInRectangle(startBounds, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                game.screen = SelectScreen(game)
                return
            }
            if (OverlapChecker.pointInRectangle(scoresBounds, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                game.screen = ScoresScreen(game)
                return
            }
            if (OverlapChecker.pointInRectangle(tutorialBounds, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                game.screen = TutorialScreen(game)
                return
            }
            if (OverlapChecker.pointInRectangle(exitBounds, touchPos.x, touchPos.y)) {
                Assets.playSound(Assets.clickSound)
                game.screen = ExitScreen(game)
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
        Assets.titleBack.draw(game.batch)
        Assets.titleStart.draw(game.batch)
        Assets.titleScore.draw(game.batch)
        Assets.titleTutorial.draw(game.batch)
        Assets.titleExit.draw(game.batch)
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
