package com.t2wonderland.kurona;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
	public static Music gameMusic;

	public static void load() {
		// title pictures
		titleAtlas = new TextureAtlas(Gdx.files.internal("title.pack"));
		titleBack = titleAtlas.createSprite("titleBack");
		titleBack.setPosition(0, 0);		
		titleStart = titleAtlas.createSprite("titleStart");
		titleStart.setPosition(32, 32);		
		titleScore = titleAtlas.createSprite("titleScore");
		titleScore.setPosition(32*6, 32);		
		titleTutorial = titleAtlas.createSprite("titleTutorial");
		titleTutorial.setPosition(32*11, 32);		
		titleExit = titleAtlas.createSprite("titleExit");
		titleExit.setPosition(32 * 20, 32);

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
		selectKurona.setPosition(32, 32*11);
		selectKoma = selectAtlas.createSprite("selectKoma");
		selectKoma.setPosition(32, 32*6);
		selectShishimaru = selectAtlas.createSprite("selectShishimaru");
		selectShishimaru.setPosition(32, 32);
		selectDescription = selectAtlas.createSprite("selectDescription");
		selectDescription.setPosition(32*15, 32*4);
		selectBack = selectAtlas.createSprite("selectBack");
		selectBack.setPosition(32*15, 32);
		selectStart = selectAtlas.createSprite("selectStart");
		selectStart.setPosition(32*20, 32);

		// game
		gameAtlas = new TextureAtlas(Gdx.files.internal("game.pack"));
		for (int i = 0; i < 3; i++) {
			runFrames[i] = gameAtlas.findRegion( (i+1) + " - kurona-run");
		}
		kuronaRun = new Animation(0.1f, runFrames);
		Texture bgImg = new Texture("bgSmall.png");
		// ƒQ[ƒ€‰æ–Ê‚Ì”wŒi‚ð‰æ‘œ20–‡•ª‚ÌƒXƒe[ƒW‚É‚·‚é
		bgImg.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		gameBack = new Sprite(bgImg, 0,0, 25*20, 15);
		gameBack.setU(0);
		gameBack.setU2(20);
		gameBack.setV(0);
		gameBack.setV2(1);
		gameMusic = Gdx.audio.newMusic(Gdx.files.internal("game1.mp3"));
		gameMusic.setLooping(true);
		gameMusic.setVolume(0.5f);
	}

	public static void playSound(Sound sound) {
		if (Settings.soundEnabled)
			sound.play(1);
	}
}
