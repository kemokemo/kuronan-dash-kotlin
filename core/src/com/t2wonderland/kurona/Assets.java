package com.t2wonderland.kurona;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.t2wonderland.kurona.Models.World;

public class Assets {
	// title
	public static TextureAtlas titleAtlas;
	public static Sprite titleBack, titleExit, titleTutorial, titleStart, titleScore;
	public static Music titleMusic;
	public static Sound clickSound;
	// select
	public static TextureAtlas selectAtlas;
	public static Sprite selectKurona, selectKoma, selectShishimaru, selectDescription, selectBack, selectStart;
	// game
	public static TextureAtlas gameAtlas;
	public static TextureRegion[] runFrames = new TextureRegion[3];
	public static Animation kuronaRun;
	public static Sprite gameBack;
	public static TextureRegion gameRock, gameCandy;
	public static Music gameMusic;
	public static Sound foodSound, jumpSound, spSound;

	public static int dot;

	public static void load() {
		dot = 32;

		// title pictures
		titleAtlas = new TextureAtlas(Gdx.files.internal("title.pack"));
		titleBack = titleAtlas.createSprite("titleBack");
		titleBack.setPosition(0, 0);		
		titleStart = titleAtlas.createSprite("titleStart");
		titleStart.setPosition(dot, dot);
		titleScore = titleAtlas.createSprite("titleScore");
		titleScore.setPosition(dot*6, dot);
		titleTutorial = titleAtlas.createSprite("titleTutorial");
		titleTutorial.setPosition(dot*11, dot);
		titleExit = titleAtlas.createSprite("titleExit");
		titleExit.setPosition(dot*20, dot);

		// title music
		titleMusic = Gdx.audio.newMusic(Gdx.files.internal("title.mp3"));
		titleMusic.setLooping(true);
		titleMusic.setVolume(0.5f);
		if (Settings.soundEnabled) {
			titleMusic.play();
		}
		clickSound = Gdx.audio.newSound(Gdx.files.internal("clickSound.wav"));

		// select
		selectAtlas = new TextureAtlas(Gdx.files.internal("select.pack"));
		selectKurona = selectAtlas.createSprite("selectKurona");
		selectKurona.setPosition(dot, dot*11);
		selectKoma = selectAtlas.createSprite("selectKoma");
		selectKoma.setPosition(dot, dot*6);
		selectShishimaru = selectAtlas.createSprite("selectShishimaru");
		selectShishimaru.setPosition(dot, dot);
		selectDescription = selectAtlas.createSprite("selectDescription");
		selectDescription.setPosition(dot*15, dot*4);
		selectBack = selectAtlas.createSprite("selectBack");
		selectBack.setPosition(dot*15, dot);
		selectStart = selectAtlas.createSprite("selectStart");
		selectStart.setPosition(dot*20, dot);

		// game
		gameAtlas = new TextureAtlas(Gdx.files.internal("game.pack"));
		for (int i = 0; i < 3; i++) {
			// TODO: 黒菜のリソース専用になってるのを要修正
			runFrames[i] = gameAtlas.findRegion( (i+1) + " - kurona-run");
		}
		kuronaRun = new Animation(0.1f, runFrames);
		gameRock = gameAtlas.findRegion("rock-normal");
		gameCandy = gameAtlas.findRegion("candy-normal");
		Texture bgImg = new Texture("bgSmall.png");
		bgImg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		gameBack = new Sprite(bgImg, 0,0, World.Companion.getWIDTH() *20, World.Companion.getHEIGHT());
		gameBack.setU(0);
		gameBack.setU2(20);
		gameBack.setV(0);
		gameBack.setV2(1);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game1.mp3"));
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
		foodSound = Gdx.audio.newSound(Gdx.files.internal("foodSound.wav"));
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("jumpSound.wav"));
		spSound = Gdx.audio.newSound(Gdx.files.internal("spSound.wav"));
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
