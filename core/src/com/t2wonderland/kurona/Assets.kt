package com.t2wonderland.kurona

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.g2d.Animation
import com.t2wonderland.kurona.Models.World

object Assets {
    // title
    lateinit var titleAtlas: TextureAtlas
    lateinit var titleBack: Sprite
    lateinit var titleExit: Sprite
    lateinit var titleTutorial: Sprite
    lateinit var titleStart: Sprite
    lateinit var titleScore: Sprite
    lateinit var titleMusic: Music
    lateinit var clickSound: Sound
    // select
    lateinit var selectAtlas: TextureAtlas
    lateinit var selectKurona: Sprite
    lateinit var selectKoma: Sprite
    lateinit var selectShishimaru: Sprite
    lateinit var selectDescription: Sprite
    lateinit var selectBack: Sprite
    lateinit var selectStart: Sprite
    // game
    lateinit var gameAtlas: TextureAtlas
    lateinit var gameBack: Sprite
    lateinit var gameRock: TextureRegion
    lateinit var gameCandy: TextureRegion
    lateinit var gameMusic: Music
    lateinit var foodSound: Sound
    lateinit var jumpSound: Sound
    lateinit var spSound: Sound
    // game - character
    lateinit var runAnimationKurona: Animation<TextureRegion>
    lateinit var runAnimationKoma: Animation<TextureRegion>
    lateinit var runAnimationShishimaru: Animation<TextureRegion>

    var dot: Int = 0

    fun load() {
        dot = 32

        loadTitle()
        loadSelect()
        loadGame()
        loadTutorial()
    }

    private fun loadTitle() {
        // picture
        titleAtlas = TextureAtlas(Gdx.files.internal("title.pack"))
        titleBack = titleAtlas.createSprite("titleBack")
        titleBack.setPosition(0f, 0f)
        titleStart = titleAtlas.createSprite("titleStart")
        titleStart.setPosition(dot.toFloat(), dot.toFloat())
        titleScore = titleAtlas.createSprite("titleScore")
        titleScore.setPosition((dot * 6).toFloat(), dot.toFloat())
        titleTutorial = titleAtlas.createSprite("titleTutorial")
        titleTutorial.setPosition((dot * 11).toFloat(), dot.toFloat())
        titleExit = titleAtlas.createSprite("titleExit")
        titleExit.setPosition((dot * 20).toFloat(), dot.toFloat())

        // music
        titleMusic = Gdx.audio.newMusic(Gdx.files.internal("title.mp3"))
        titleMusic.isLooping = true
        titleMusic.volume = 0.5f
        if (Settings.soundEnabled) {
            titleMusic.play()
        }

        // sound
        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.wav"))
    }

    private fun loadSelect() {
        // picture
        selectAtlas = TextureAtlas(Gdx.files.internal("select.pack"))
        selectKurona = selectAtlas.createSprite("selectKurona")
        selectKurona.setPosition(dot.toFloat(), (dot * 11).toFloat())
        selectKoma = selectAtlas.createSprite("selectKoma")
        selectKoma.setPosition(dot.toFloat(), (dot * 6).toFloat())
        selectShishimaru = selectAtlas.createSprite("selectShishimaru")
        selectShishimaru.setPosition(dot.toFloat(), dot.toFloat())
        selectDescription = selectAtlas.createSprite("selectDescription")
        selectDescription.setPosition((dot * 15).toFloat(), (dot * 4).toFloat())
        selectBack = selectAtlas.createSprite("selectBack")
        selectBack.setPosition((dot * 15).toFloat(), dot.toFloat())
        selectStart = selectAtlas.createSprite("selectStart")
        selectStart.setPosition((dot * 20).toFloat(), dot.toFloat())
    }

    private fun loadGame() {
        gameAtlas = TextureAtlas(Gdx.files.internal("game.pack"))

        // character animation
        runAnimationKurona = loadAnimation(3, "kurona_run")
        runAnimationKoma = loadAnimation(4, "koma_run")
        runAnimationShishimaru = loadAnimation(4, "shishimaru_run")

        // static objects
        gameRock = gameAtlas.findRegion("rock-normal")
        gameCandy = gameAtlas.findRegion("candy-normal")

        // pictures
        val bgImg = Texture("bgSmall.png")
        bgImg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
        gameBack = Sprite(bgImg, 0, 0, World.WIDTH * 20, World.HEIGHT)
        gameBack.u = 0f
        gameBack.u2 = 20f
        gameBack.v = 0f
        gameBack.v2 = 1f

        // music
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game1.mp3"))
        gameMusic.isLooping = true
        gameMusic.volume = 0.5f

        // sound
        foodSound = Gdx.audio.newSound(Gdx.files.internal("foodSound.wav"))
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumpSound.wav"))
        spSound = Gdx.audio.newSound(Gdx.files.internal("spSound.wav"))
    }

    private fun loadAnimation(frameNum: Int, prefix: String): Animation<TextureRegion> {
        var frames = arrayOfNulls<TextureRegion>(frameNum)
        for (index in 0..(frameNum-1)) {
            frames[index] = gameAtlas.findRegion(prefix, index)
        }
        return Animation<TextureRegion>(0.1f, *frames)
    }

    private fun loadTutorial() {
        // TODO: チュートリアル画面のリソース読み込み
    }

    fun playSound(sound: Sound) {
        if (Settings.soundEnabled)
            sound.play(1f)
    }
}
