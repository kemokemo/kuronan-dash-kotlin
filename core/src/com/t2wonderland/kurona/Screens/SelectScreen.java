package com.t2wonderland.kurona.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.KuronanDash;
import com.t2wonderland.kurona.Models.OverlapChecker;

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
		camera = new OrthographicCamera(KuronanDash.displayWidth, KuronanDash.displayHeight);
		camera.setToOrtho(false, KuronanDash.displayWidth, KuronanDash.displayHeight);
		
		// regions of the buttons
		kuronaBounds = Assets.selectKurona.getBoundingRectangle();
		komaBounds = Assets.selectKoma.getBoundingRectangle();
		shishimaruBounds = Assets.selectShishimaru.getBoundingRectangle();
		backBounds = Assets.selectBack.getBoundingRectangle();
		startBounds = Assets.selectStart.getBoundingRectangle();
	}

	public void update (float deltaTime) {
		if (Gdx.input.isTouched(0)) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			if (OverlapChecker.INSTANCE.pointInRectangle(kuronaBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				return;
			}
			if (OverlapChecker.INSTANCE.pointInRectangle(komaBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				return;
			}
			if (OverlapChecker.INSTANCE.pointInRectangle(shishimaruBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				return;
			}
			if (OverlapChecker.INSTANCE.pointInRectangle(backBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new TitleScreen(game));
				return;
			}
			if (OverlapChecker.INSTANCE.pointInRectangle(startBounds, touchPos.x, touchPos.y)) {
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
