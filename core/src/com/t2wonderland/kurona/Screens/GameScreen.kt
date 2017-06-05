package com.t2wonderland.kurona.Screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.t2wonderland.kurona.*
import com.t2wonderland.kurona.Models.*

class GameScreen(internal val _game: KuronanDash, internal val selectedCharacter: CharacterSelect) : Screen {

    internal var _state: GameState
    internal var _camera: OrthographicCamera
    internal var _batcher: SpriteBatch

    internal var _world: World
    internal var _worldListener: WorldListener
    internal var _renderer: WorldRenderer

    internal var _lastScore: Int = 0
    internal var _scoreString: String

    init {
        _camera = OrthographicCamera(_game.Width.toFloat(), _game.Height.toFloat())
        _camera.setToOrtho(false, _game.Width.toFloat(), _game.Height.toFloat())
        _state = GameState.Ready
        _batcher = SpriteBatch()
        _lastScore = 0
        _scoreString = "SCORE: 0"

        _worldListener = WorldListener()
        _world = World(_worldListener, selectedCharacter)
        _renderer = WorldRenderer(_batcher, _world)

        if (Settings.soundEnabled) {
            Assets.titleMusic.stop()
            Assets.gameMusic.play()
        }
    }

    override fun render(delta: Float) {
        if(_state == GameState.Running){
            update(delta)
        }
        draw()
    }

    fun update(deltaTime: Float) {
        var deltaTime = deltaTime
        if (deltaTime > 0.1f) deltaTime = 0.1f
        updateRunning(deltaTime)
    }

    private fun updateRunning(deltaTime: Float) {
        _world.update(deltaTime, World.acceleration)
    }

    fun draw() {
        // ゲーム世界自体の描画
        _renderer.render()

        // ゲーム世界のメタ要素の描画
        _camera.update()
        _batcher.projectionMatrix = _camera.combined
        _batcher.enableBlending()
        when (_state) {
            GameState.Ready -> presentReady()
            GameState.Running -> presentRunning()
            GameState.Paused -> presentPaused()
            GameState.LevelEnd -> presentLevelEnd()
            GameState.GameOver -> presentGameOver()
        }
    }

    private fun presentReady() {
        // ゲーム開始前のReady画面表示
        _game.batch.begin()
        _game.font.draw(_game.batch, "Touch to start.", 100f, 150f)
        _game.batch.end()

        if (Gdx.input.justTouched()) {
            _state = GameState.Running
        }
    }

    private fun presentRunning() {
        // TODO: ゲーム中の画面表示
        // 必要に応じて一時停止ボタンやスコアを表示する
    }

    private fun presentPaused() {
        // TODO: 一時停止中の画面表示
        // ゲーム再開ボタンやゲームを中断してタイトルに戻るボタンあると良いかも
    }

    private fun presentLevelEnd() {
        // TODO: 全クリア時の表示
        // 豪華にやるなら別画面でアドベンチャー風にしたいなぁ
    }

    private fun presentGameOver() {
        // TODO: ゲームオーバー画面の表示
        // スコア表示したりタイトルに戻るボタンあっても良いかも
    }

    override fun resize(width: Int, height: Int) {
    }

    override fun show() {
    }

    override fun hide() {
        // TODO: 最小化された時の処理
        pause()
    }

    override fun pause() {
        // TODO: 一時停止した時の処理
    }

    override fun resume() {
        // TODO: 再開時の処理
    }

    override fun dispose() {
    }

}
