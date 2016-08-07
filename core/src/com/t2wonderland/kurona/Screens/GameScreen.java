package com.t2wonderland.kurona.Screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.t2wonderland.kurona.*;
import com.t2wonderland.kurona.Models.World;
import com.t2wonderland.kurona.Models.WorldRenderer;
import com.t2wonderland.kurona.Models.WorldListener;
import com.t2wonderland.kurona.Models.GameState;

public class GameScreen implements Screen {

	GameState _state;

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
		_state = GameState.Running;
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
		_world.update(deltaTime, World.Companion.getAcceleration());
	}
	
	public void draw (float deltaTime) {
		_renderer.render();

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
		// TODO: ゲーム開始前のReady画面表示
	}

	private void presentRunning () {
		// TODO: ゲーム中の画面表示
		// 必要に応じて一時停止ボタンやスコアを表示する
	}

	private void presentPaused () {
		// TODO: 一時停止中の画面表示
		// ゲーム再開ボタンやゲームを中断してタイトルに戻るボタンあると良いかも
	}

	private void presentLevelEnd () {
		// TODO: 全クリア時の表示
		// 豪華にやるなら別画面でアドベンチャー風にしたいなぁ
	}

	private void presentGameOver () {
		// TODO: ゲームオーバー画面の表示
		// スコア表示したりタイトルに戻るボタンあっても良いかも
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
		// TODO: 最小化された時の処理
		pause();
	}

	@Override
	public void pause() {
		// TODO: 一時停止した時の処理
	}

	@Override
	public void resume() {
		// TODO: 再開時の処理
	}

	@Override
	public void dispose() {
	}

}
