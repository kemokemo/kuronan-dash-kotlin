package com.t2wonderland.kurona;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WorldRenderer {

	World world;
	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;
	
	public WorldRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		
		// initialize the camera
		this.camera = new OrthographicCamera(World.WIDTH, World.HEIGHT);
		this.camera.setToOrtho(false, World.WIDTH, World.HEIGHT);
		viewport = new FitViewport(World.WIDTH, World.HEIGHT, camera);
	}
	
	public void render () {
		Gdx.gl.glClearColor(0, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if (world.kurona.position.x > camera.position.x) {
			camera.position.x = world.kurona.position.x;
		}
		camera.update();
		batch.setProjectionMatrix(camera.combined);

		renderBackground();
		renderObjects();
	}
	
	public void renderBackground () {
		batch.begin();
		Assets.gameBack.draw(batch);
		batch.end();
	}
	
	public void renderObjects () {
		batch.begin();
		renderKurona();
		renderPlatforms();
		renderItems();
		renderGoal();
		batch.end();		
	}
	
	private void renderKurona () {
		TextureRegion keyFrame;
		switch (world.kurona.state) {
			case Kurona.STATE_HIT:
				keyFrame = Assets.kuronaRun.getKeyFrame(world.kurona.stateTime, true);
				break;
			case Kurona.STATE_SLOW:
				keyFrame = Assets.kuronaRun.getKeyFrame(world.kurona.stateTime, true);
				break;
			case Kurona.STATE_SP:
				keyFrame = Assets.kuronaRun.getKeyFrame(world.kurona.stateTime, true);
				break;
			case Kurona.STATE_DASH:
			default:
				keyFrame = Assets.kuronaRun.getKeyFrame(world.kurona.stateTime, true);
		}

		batch.draw(keyFrame, world.kurona.position.x, world.kurona.position.y, Kurona.WIDTH, Kurona.HEIGHT);
	}
	
	private void renderPlatforms () {
	}
	
	private void renderItems () {
		batch.draw(Assets.gameRock, world.rock.position.x, world.rock.position.y, Rock.WIDTH, Rock.HEIGHT);
		batch.draw(Assets.gameCandy, world.candy.position.x, world.candy.position.y, Candy.WIDTH, Candy.HEIGHT);
	}
	
	private void renderGoal () {
	}
}
