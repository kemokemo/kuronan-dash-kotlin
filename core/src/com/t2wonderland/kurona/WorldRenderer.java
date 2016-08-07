package com.t2wonderland.kurona;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class WorldRenderer {
	static final float FRUSTUM_WIDTH = 25; // 800/32=25
	static final float FRUSTUM_HEIGHT = 15; // 480/32=15
	World world;
	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;
	
	public WorldRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		
		// initialize the camera
		this.camera = new OrthographicCamera(FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		this.camera.setToOrtho(false, FRUSTUM_WIDTH, FRUSTUM_HEIGHT);
		viewport = new FitViewport(FRUSTUM_WIDTH, FRUSTUM_HEIGHT, camera);
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
		case Kurona.KURONA_STATE_HIT:
			keyFrame = Assets.kuronaWalk.getKeyFrame(world.kurona.stateTime ,Animation.ANIMATION_LOOPING);
			break;
		case Kurona.KURONA_STATE_SLOW:
			keyFrame = Assets.kuronaWalk.getKeyFrame(world.kurona.stateTime ,Animation.ANIMATION_LOOPING);
			break;
		case Kurona.KURONA_STATE_SP:
			keyFrame = Assets.kuronaWalk.getKeyFrame(world.kurona.stateTime ,Animation.ANIMATION_LOOPING);
			break;
		case Kurona.KURONA_STATE_DASH:
		default:
			keyFrame = Assets.kuronaWalk.getKeyFrame(world.kurona.stateTime ,Animation.ANIMATION_LOOPING);
		}
		
		batch.draw(keyFrame, world.kurona.position.x, world.kurona.position.y, 1, 1.5f);
	}
	
	private void renderPlatforms () {
	}
	
	private void renderItems () {
	}
	
	private void renderGoal () {
	}
}
