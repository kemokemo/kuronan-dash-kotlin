package com.t2wonderland.kurona.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.t2wonderland.kurona.*;
import com.t2wonderland.kurona.models.World;
import com.t2wonderland.kurona.models.WorldRenderer;
import com.t2wonderland.kurona.models.WorldListener;

public class GameScreen implements Screen {

	World.Game_State _state;

	final KuronanDash _game;
	OrthographicCamera _camera;
	SpriteBatch _batcher;

	World _world;
	WorldListener _worldListener;
	WorldRenderer _renderer;

	int _lastScore;
	String _scoreString;
	
	public GameScreen(final KuronanDash game){
		this._game = game;
		_camera = new OrthographicCamera(KuronanDash.displayWidth, KuronanDash.displayHeight);
		_camera.setToOrtho(false, KuronanDash.displayWidth, KuronanDash.displayHeight);
		_state = World.Game_State.Running;
        _batcher = new SpriteBatch();
        _worldListener = new WorldListener();
		_world = new World(_worldListener);
		_renderer = new WorldRenderer(_batcher, _world);
		_lastScore = 0;
		_scoreString = "SCORE: 0";
		
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
		_world.update(deltaTime, World.acel);

		// 点数を更新
	}
	
	public void draw (float deltaTime) {
		_renderer.render();

        // UI部品を描画
		_camera.update();
        _batcher.setProjectionMatrix(_camera.combined);
        _batcher.enableBlending();
		switch (_state) {
			case Ready:
				presentReady();
				break;
			case Running:
				presentRunning();
				break;
			case Paused:
				presentPaused();
				break;
			case LevelEnd:
				presentLevelEnd();
				break;
			case GameOver:
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
