package com.t2wonderland.kurona.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.t2wonderland.kurona.KuronanDash;

public class ExitScreen implements Screen {

	final KuronanDash game;
	OrthographicCamera camera;
	
	public ExitScreen(final KuronanDash gam){
		game = gam;
		
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
	}
	
	public void draw (float deltaTime) {
		Gdx.gl.glClearColor(0, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Exit Screen!!", 100, 150);
        game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
	}
	
	@Override
	public void render(float delta) {
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
