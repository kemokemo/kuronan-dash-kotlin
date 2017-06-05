package com.t2wonderland.kurona.Screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.t2wonderland.kurona.*
import com.t2wonderland.kurona.Models.*
import com.t2wonderland.kurona.Objects.ScoreBoard

class GameScreen(internal val game: KuronanDash, internal val selectedCharacter: CharacterSelect) : Screen {

    var state: GameState
    var camera: OrthographicCamera
    var batch: SpriteBatch
    val width: Float
    val height: Float

    var world: World
    var listener: WorldListener
    var renderer: WorldRenderer

    var score: ScoreBoard

    init {
        width = game.Width.toFloat()
        height = game.Height.toFloat()
        camera = OrthographicCamera(width, height)
        camera.setToOrtho(false, width, height)
        state = GameState.Ready

        score = ScoreBoard(width, height)
        listener = WorldListener()
        world = World(listener, selectedCharacter, score)

        batch = game.batch
        renderer = WorldRenderer(batch, world)

        if (Settings.soundEnabled) {
            Assets.titleMusic.stop()
            Assets.gameMusic.play()
        }
    }

    override fun render(delta: Float) {
        if(state == GameState.Running){
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
        world.update(deltaTime, World.acceleration)
    }

    fun draw() {
        // ゲーム世界自体の描画
        renderer.render()

        // ゲーム世界のメタ要素の描画
        camera.update()
        batch.projectionMatrix = camera.combined
        batch.enableBlending()
        when (state) {
            GameState.Ready -> presentReady()
            GameState.Running -> presentRunning()
            GameState.Paused -> presentPaused()
            GameState.LevelEnd -> presentLevelEnd()
            GameState.GameOver -> presentGameOver()
        }
    }

    private fun presentReady() {
        // ゲーム開始前のReady画面表示
        game.batch.begin()
        game.font.draw(game.batch, "Touch to start.", width*0.1f, height*0.1f)
        game.batch.end()

        if (Gdx.input.justTouched()) {
            state = GameState.Running
        }
    }

    private fun presentRunning() {
        // TODO: ゲーム中の画面表示
        // 必要に応じて一時停止ボタンやスコアを表示する
        game.batch.begin()
        game.font.draw(game.batch, score.toString(), score.position.x, score.position.y)
        game.batch.end()
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
