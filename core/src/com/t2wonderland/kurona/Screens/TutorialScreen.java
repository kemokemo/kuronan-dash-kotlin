package com.t2wonderland.kurona.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.t2wonderland.kurona.KuronanDash;

public class TutorialScreen implements Screen {

	final KuronanDash game;
	OrthographicCamera camera;
	
	public TutorialScreen(final KuronanDash gam){
		game = gam;
		camera = new OrthographicCamera(KuronanDash.displayWidth, KuronanDash.displayHeight);
		camera.setToOrtho(false, KuronanDash.displayWidth, KuronanDash.displayHeight);
	}
	
	public void draw (float deltaTime) {
		Gdx.gl.glClearColor(0, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
        game.font.draw(game.batch, "Welcome to Tutorial Screen!!", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
	}
	
	@Override
	public void render(float delta) {
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
