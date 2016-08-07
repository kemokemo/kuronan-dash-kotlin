package com.t2wonderland.kurona.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.KuronanDash;
import com.t2wonderland.kurona.models.OverlapCheker;

public class TitleScreen implements Screen {

	final KuronanDash game;
	OrthographicCamera camera;
	Rectangle startBounds;
	Rectangle scoresBounds;
	Rectangle tutorialBounds;
	Rectangle exitBounds;

	public TitleScreen(final KuronanDash gam)
	{
		game = gam;
		camera = new OrthographicCamera(KuronanDash.displayWidth, KuronanDash.displayHeight);
		camera.setToOrtho(false, KuronanDash.displayWidth, KuronanDash.displayHeight);

		// regions of the buttons
		startBounds = Assets.titleStart.getBoundingRectangle();
		scoresBounds = Assets.titleScore.getBoundingRectangle();
		tutorialBounds = Assets.titleTutorial.getBoundingRectangle();
		exitBounds = Assets.titleExit.getBoundingRectangle();
	}
	
	public void update (float deltaTime) {
		if (Gdx.input.isTouched(0)) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			if (OverlapCheker.pointInRectangle(startBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new SelectScreen(game));
				return;
			}
			if (OverlapCheker.pointInRectangle(scoresBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new ScoresScreen(game));
				return;
			}
			if (OverlapCheker.pointInRectangle(tutorialBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new TutorialScreen(game));
				return;
			}
			if (OverlapCheker.pointInRectangle(exitBounds, touchPos.x, touchPos.y)) {
				Assets.playSound(Assets.clickSound);
				game.setScreen(new ExitScreen(game));
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
		Assets.titleBack.draw(game.batch);
		Assets.titleStart.draw(game.batch);
		Assets.titleScore.draw(game.batch);
		Assets.titleTutorial.draw(game.batch);
		Assets.titleExit.draw(game.batch);
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
