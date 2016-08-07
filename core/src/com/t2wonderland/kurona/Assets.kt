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
    lateinit var runFrames : Array<TextureRegion?>
    lateinit var kuronaRun: Animation
    lateinit var gameBack: Sprite
    lateinit var gameRock: TextureRegion
    lateinit var gameCandy: TextureRegion
    lateinit var gameMusic: Music
    lateinit var foodSound: Sound
    lateinit var jumpSound: Sound
    lateinit var spSound: Sound

    var dot: Int = 0

    fun load() {
        dot = 32

        // title pictures
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

        // title music
        titleMusic = Gdx.audio.newMusic(Gdx.files.internal("title.mp3"))
        titleMusic.isLooping = true
        titleMusic.volume = 0.5f
        if (Settings.soundEnabled) {
            titleMusic.play()
        }
        clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.wav"))

        // select
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

        // game
        runFrames = arrayOfNulls<TextureRegion>(3)
        gameAtlas = TextureAtlas(Gdx.files.internal("game.pack"))
        for (i in 0..2) {
            // TODO: 黒菜のリソース専用になってるのを要修正
            runFrames[i] = gameAtlas.findRegion((i + 1).toString() + " - kurona-run")
        }
        kuronaRun = Animation(0.1f, *runFrames)
        gameRock = gameAtlas.findRegion("rock-normal")
        gameCandy = gameAtlas.findRegion("candy-normal")
        val bgImg = Texture("bgSmall.png")
        bgImg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
        gameBack = Sprite(bgImg, 0, 0, World.WIDTH * 20, World.HEIGHT)
        gameBack.u = 0f
        gameBack.u2 = 20f
        gameBack.v = 0f
        gameBack.v2 = 1f
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game1.mp3"))
        gameMusic.isLooping = true
        gameMusic.volume = 0.5f
        foodSound = Gdx.audio.newSound(Gdx.files.internal("foodSound.wav"))
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumpSound.wav"))
        spSound = Gdx.audio.newSound(Gdx.files.internal("spSound.wav"))
    }

    fun playSound(sound: Sound) {
        if (Settings.soundEnabled)
            sound.play(1f)
    }
}
