package com.t2wonderland.kurona

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.t2wonderland.kurona.Screens.TitleScreen

class KuronanDash : Game() {

    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit internal var renderer: ShapeRenderer

    override fun create() {
        displayWidth = 800
        displayHeight = 480
        batch = SpriteBatch()
        renderer = ShapeRenderer()
        //Use LibGDX's default Arial font.
        font = BitmapFont()
        Assets.load()
        this.setScreen(TitleScreen(this))
    }

    override fun dispose() {
        super.dispose()
        getScreen().dispose()

        batch.dispose()
        font.dispose()
    }

    override fun render() {
        super.render() //important!
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun pause() {
    }

    override fun resume() {
    }

    companion object {
        var displayWidth: Int = 0
        var displayHeight: Int = 0
    }
}
