package com.t2wonderland.kurona.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.interfaces.IGameObject;
import com.t2wonderland.kurona.objects.Candy;
import com.t2wonderland.kurona.objects.Kurona;

public class WorldRenderer {

	World world;
	OrthographicCamera camera;
	SpriteBatch batch;
	Viewport viewport;
	Vector2 pos;
	
	public WorldRenderer(SpriteBatch batch, World world) {
		this.world = world;
		this.batch = batch;
		
		// initialize the camera
		this.camera = new OrthographicCamera(World.WIDTH, World.HEIGHT);
		this.camera.setToOrtho(false, World.WIDTH, World.HEIGHT);
		viewport = new FitViewport(World.WIDTH, World.HEIGHT, camera);
		pos = new Vector2();
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

		if (Gdx.input.justTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			if ( 0 <= touchPos.y && touchPos.y < 5 ){
				world.kurona.position.y = 1;
			}
			else if ( 5 <= touchPos.y && touchPos.y < 10 ){
				world.kurona.position.y = 6;
			}
			else if (10 <= touchPos.y && touchPos.y < 15){
				world.kurona.position.y = 11;
			}
			else{
				// touched point is out of the range
			}
		}

		batch.draw(keyFrame, world.kurona.position.x, world.kurona.position.y, Kurona.WIDTH, Kurona.HEIGHT);
	}
	
	private void renderPlatforms () {
	}
	
	private void renderItems () {
		Vector2 position = world.rock.getPosition();
		Vector2 size = world.rock.getSize();
		batch.draw(Assets.gameRock, position.x, position.y, size.x, size.y);

		int len = world.candys.size();
		for (int counter = 0; counter < len; counter++) {
			IGameObject candy = world.candys.get(counter);
			Vector2 candyPosition = candy.getPosition();
			Vector2 candySize = candy.getSize();
			batch.draw(Assets.gameCandy, candyPosition.x, candyPosition.y, candySize.x, candySize.y);
		}
	}
	
	private void renderGoal () {
	}
}
