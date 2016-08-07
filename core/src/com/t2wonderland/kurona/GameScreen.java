package com.t2wonderland.kurona;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.t2wonderland.kurona.World.WorldListener;

public class GameScreen implements Screen {
	static final int GAME_READY = 0;
	static final int GAME_RUNNING = 1;
	static final int GAME_PAUSED = 2;
	static final int GAME_LEVEL_END = 3;
	static final int GAME_OVER = 4;

	int state;

	final KuronanDash game;
	OrthographicCamera camera;
	SpriteBatch batcher;
	World world;
	WorldListener worldListener;
	WorldRenderer renderer;
	int lastScore;
	String scoreString;
	
	public GameScreen(final KuronanDash game){
		this.game = game;
		camera = new OrthographicCamera(game.displayWidth, game.displayHeight);
		camera.setToOrtho(false, game.displayWidth, game.displayHeight);
		state = GAME_READY;
        batcher = new SpriteBatch();
        worldListener = new WorldListener() {
			@Override
			public void sp () {
				// 技発動サウンド
				//Assets.playSound(Assets.spSound);
			}

			@Override
			public void food () {
				// 食べ物たべるサウンド
				//Assets.playSound(Assets.foodSound);
			}
		};
		world = new World(worldListener);
		renderer = new WorldRenderer(batcher, world);
		lastScore = 0;
		scoreString = "SCORE: 0";
		
		if (Settings.soundEnabled) {
			Assets.titleMusic.stop();
			Assets.gameMusic.play();
		}
	}
	
	public void update (float deltaTime) {
		if (deltaTime > 0.1f) deltaTime = 0.1f;		
		updateRunning(deltaTime);
	}
	
	private void updateRunning (float deltaTime) {
		world.update(deltaTime, 1);

		// 点数を更新
	}
	
	public void draw (float deltaTime) {
		renderer.render();

        // UI部品を描画
		camera.update();
        batcher.setProjectionMatrix(camera.combined);
        batcher.enableBlending();
		switch (state) {
		case GAME_READY:
			presentReady();
			break;
		case GAME_RUNNING:
			presentRunning();
			break;
		case GAME_PAUSED:
			presentPaused();
			break;
		case GAME_LEVEL_END:
			presentLevelEnd();
			break;
		case GAME_OVER:
			presentGameOver();
			break;
		}
	}

	private void presentReady () {
		//batcher.draw(Assets.ready, 160 - 192 / 2, 240 - 32 / 2, 192, 32);
	}

	private void presentRunning () {
		//batcher.draw(Assets.pause, 320 - 64, 480 - 64, 64, 64);
		//Assets.font.draw(batcher, scoreString, 16, 480 - 20);
	}

	private void presentPaused () {
		//batcher.draw(Assets.pauseMenu, 160 - 192 / 2, 240 - 96 / 2, 192, 96);
		//Assets.font.draw(batcher, scoreString, 16, 480 - 20);
	}

	private void presentLevelEnd () {
		//String topText = "the princess is ...";
		//String bottomText = "in another castle!";
		//float topWidth = Assets.font.getBounds(topText).width;
		//float bottomWidth = Assets.font.getBounds(bottomText).width;
		//Assets.font.draw(batcher, topText, 160 - topWidth / 2, 480 - 40);
		//Assets.font.draw(batcher, bottomText, 160 - bottomWidth / 2, 40);
	}

	private void presentGameOver () {
		//batcher.draw(Assets.gameOver, 160 - 160 / 2, 240 - 96 / 2, 160, 96);
		//float scoreWidth = Assets.font.getBounds(scoreString).width;
		//Assets.font.draw(batcher, scoreString, 160 - scoreWidth / 2, 480 - 20);
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
