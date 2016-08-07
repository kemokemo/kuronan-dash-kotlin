package com.t2wonderland.kurona.Models;

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
import com.t2wonderland.kurona.Interfaces.CharacterState;
import com.t2wonderland.kurona.Interfaces.IStaticObject;

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
		this.camera = new OrthographicCamera(World.Companion.getWIDTH(), World.Companion.getHEIGHT());
		this.camera.setToOrtho(false, World.Companion.getWIDTH(), World.Companion.getHEIGHT());
		viewport = new FitViewport(World.Companion.getWIDTH(), World.Companion.getHEIGHT(), camera);
		pos = new Vector2();
	}
	
	public void render () {
		Gdx.gl.glClearColor(0, 0.3f, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Vector2 position = world.get_character().getPosition();
		if (position.x > camera.position.x) {
			camera.position.x = position.x;
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
		renderCharacter();
		renderPlatforms();
		renderItems();
		renderGoal();
		batch.end();		
	}
	
	private void renderCharacter() {
		TextureRegion keyFrame = updateKeyFrame();

		Vector2 position = world.get_character().getPosition();
		position = updatePositionFromUserInput(position);
		world.get_character().setPosition(position);

		Vector2 size = world.get_character().getSize();
		batch.draw(keyFrame, position.x, position.y, size.x, size.y);
	}

	private Vector2 updatePositionFromUserInput(Vector2 position) {
		if (Gdx.input.justTouched()){
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);

			if ( 0 <= touchPos.y && touchPos.y < 5 ){
				position.y = 1;
			}
			else if ( 5 <= touchPos.y && touchPos.y < 10 ){
				position.y = 6;
			}
			else if (10 <= touchPos.y && touchPos.y < 15){
				position.y = 11;
			}
			else{
				// touched point is out of the range
			}
		}

		return position;
	}

	private TextureRegion updateKeyFrame() {
		TextureRegion keyFrame;
		CharacterState state = world.get_character().getCharacterState();
		float stateTime = world.get_character().getStateTime();
		switch (state) {
			case Slow:
			case Special:
			case Dash:
				keyFrame = Assets.kuronaRun.getKeyFrame(stateTime, true);
				break;

			default:
				keyFrame = Assets.kuronaRun.getKeyFrame(stateTime, true);
		}

		return keyFrame;
	}

	private void renderPlatforms () {
        // TODO: 地面とかの構成要素を描画
	}
	
	private void renderItems () {
		Vector2 position = world.getBarricade().getPosition();
		Vector2 size = world.getBarricade().getSize();
		batch.draw(Assets.gameRock, position.x, position.y, size.x, size.y);

		int len = world.getItemList().size();
		for (int counter = 0; counter < len; counter++) {
			IStaticObject candy = world.getItemList().get(counter);
			Vector2 candyPosition = candy.getPosition();
			Vector2 candySize = candy.getSize();
			batch.draw(Assets.gameCandy, candyPosition.x, candyPosition.y, candySize.x, candySize.y);
		}
	}
	
	private void renderGoal () {
        // TODO: ゴールを描画
	}
}
