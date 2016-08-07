package com.t2wonderland.kurona;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class KuronanDash extends Game {
	
	SpriteBatch batch;
    BitmapFont font;
	public static int displayWidth, displayHeight;
    
    ShapeRenderer renderer;
    
	@Override
	public void create() {
		displayWidth = 800;
		displayHeight = 480;
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
        //Use LibGDX's default Arial font.
        font = new BitmapFont();
        Assets.load();
        this.setScreen(new TitleScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		getScreen().dispose();
		
		batch.dispose();
		font.dispose();
	}

	@Override
	public void render() {		
		super.render(); //important!
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
