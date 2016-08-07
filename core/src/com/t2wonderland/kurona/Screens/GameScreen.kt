package com.t2wonderland.kurona.Screens

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.t2wonderland.kurona.*
import com.t2wonderland.kurona.Models.World
import com.t2wonderland.kurona.Models.WorldRenderer
import com.t2wonderland.kurona.Models.WorldListener
import com.t2wonderland.kurona.Models.GameState

class GameScreen(internal val _game: KuronanDash) : Screen {

    internal var _state: GameState
    internal var _camera: OrthographicCamera
    internal var _batcher: SpriteBatch

    internal var _world: World
    internal var _worldListener: WorldListener
    internal var _renderer: WorldRenderer

    internal var _lastScore: Int = 0
    internal var _scoreString: String

    init {
        _camera = OrthographicCamera(KuronanDash.displayWidth.toFloat(), KuronanDash.displayHeight.toFloat())
        _camera.setToOrtho(false, KuronanDash.displayWidth.toFloat(), KuronanDash.displayHeight.toFloat())
        _state = GameState.Running
        _batcher = SpriteBatch()
        _worldListener = WorldListener()
        _world = World(_worldListener)
        _renderer = WorldRenderer(_batcher, _world)
        _lastScore = 0
        _scoreString = "SCORE: 0"

        if (Settings.soundEnabled) {
            Assets.titleMusic.stop()
            Assets.gameMusic.play()
        }
    }

    fun update(deltaTime: Float) {
        var deltaTime = deltaTime
        if (deltaTime > 0.1f) deltaTime = 0.1f
        updateRunning(deltaTime)
    }

    private fun updateRunning(deltaTime: Float) {
        _world.update(deltaTime, World.acceleration)
    }

    fun draw(deltaTime: Float) {
        _renderer.render()

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
        // TODO: ゲーム開始前のReady画面表示
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

    override fun render(delta: Float) {
        update(delta)
        draw(delta)
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
