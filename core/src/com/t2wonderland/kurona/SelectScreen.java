package com.t2wonderland.kurona;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class SelectScreen implements Screen {

	final KuronanDash game;
	OrthographicCamera camera;
	Rectangle kuronaBounds;
	Rectangle komaBounds;
	Rectangle shishimaruBounds;
	Rectangle backBounds;
	Rectangle startBounds;

	public SelectScreen(final KuronanDash gam){
		game = gam;
		camera = new OrthographicCamera(game.displayWidth, game.displayHeight);
		camera.setToOrtho(false, game.displayWidth, game.displayHeight);
		
		// regions of the buttons
		kuronaBounds = Assets.selectKurona.getBoundingRectangle();
		kuronaBounds.set(kuronaBounds.getX(), kuronaBounds.getY(), kuronaBounds.width, kuronaBounds.height);
		komaBounds = Assets.selectKoma.getBoundingRectangle();
		komaBounds.set(komaBounds.getX(), komaBounds.getY(), komaBounds.width, komaBounds.height);
		shishimaruBounds = Assets.selectShishimaru.getBoundingRectangle();
		shishimaruBounds.set(shishimaruBounds.getX(), shishimaruBounds.getY(), shishimaruBounds.width, shishimaruBounds.height);
		backBounds = Assets.selectBack.getBoundingRectangle();
		backBounds.set(backBounds.getX(), backBounds.getY(), backBounds.width, backBounds.height);
		startBounds = Assets.selectStart.getBoundingRectangle();
		startBounds.set(startBounds.getX(), startBounds.getY(), startBounds.width, startBounds.height);
	}

	public void update (float deltaTime) {
		if (Gdx.input.isTouched(0)) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			if (OverlapTester.pointInRectangle(kuronaBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				return;
			}
			if (OverlapTester.pointInRectangle(komaBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				return;
			}
			if (OverlapTester.pointInRectangle(shishimaruBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				return;
			}
			if (OverlapTester.pointInRectangle(backBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new TitleScreen(game));
				return;
			}
			if (OverlapTester.pointInRectangle(startBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new GameScreen(game));
				return;
			}
		}
	}
	
	public void draw (float deltaTime) {
		Gdx.gl.glClearColor(0, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        Assets.selectKurona.draw(game.batch);
        Assets.selectKoma.draw(game.batch);
        Assets.selectShishimaru.draw(game.batch);
        Assets.selectDescription.draw(game.batch);
        Assets.selectBack.draw(game.batch);
        Assets.selectStart.draw(game.batch);
		game.batch.end();
	}
	
	@Override
	public void render(float delta) {
		update(delta);
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
