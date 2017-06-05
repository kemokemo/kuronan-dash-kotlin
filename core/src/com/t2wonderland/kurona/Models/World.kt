package com.t2wonderland.kurona.Models

import com.badlogic.gdx.math.Vector2
import java.util.ArrayList
import java.util.Random

import com.t2wonderland.kurona.Interfaces.ICharacterObject
import com.t2wonderland.kurona.Interfaces.IStaticObject
import com.t2wonderland.kurona.Objects.Candy
import com.t2wonderland.kurona.Objects.Rock
import com.t2wonderland.kurona.Objects.ScoreBoard

class World(val listener: WorldListener, val selected: CharacterSelect, val score: ScoreBoard) {

    val character: ICharacterObject

    val barricadeList: ArrayList<IStaticObject>
    val itemList: ArrayList<IStaticObject>
    var state: WorldState

    init {
        character = CharacterFactory(selected).createCharacter()
        barricadeList = ArrayList()
        itemList = ArrayList()
        state = WorldState.Running

        generateLevel(1)
    }

    // 乱数から3段階の高さ情報を作って、レベルに応じて数を増やしながら飴と岩を配置する
    private fun generateLevel(level : Int) {
        val rand = Random()

        for (counter in 0..10 * (10-level)) {
            val item = Candy()
            item.setInitialPosition(WIDTH.toFloat() * 20f * rand.nextFloat(),
                    HeightCalculator.Calculate(HEIGHT.toFloat() * rand.nextFloat()) ?: 1f)
            itemList.add(item)
        }

        for(counter in 0..20 * level){
            val barricade = Rock()
            barricade.setInitialPosition(WIDTH.toFloat() * 20f * rand.nextFloat(),
                    HeightCalculator.Calculate(HEIGHT.toFloat() * rand.nextFloat()) ?: 1f)
            barricadeList.add(barricade)
        }
    }

    fun update(deltaTime: Float, acceleration: Vector2) {
        checkGameOver()
        checkCollisions()
        updateCharacter(deltaTime, acceleration)
    }

    private fun updateCharacter(deltaTime: Float, acceleration: Vector2) {
        character.updatePosition(deltaTime, acceleration)
    }

    private fun checkCollisions() {
        checkBarricadeCollisions()
        checkItemCollisions()
    }

    private fun checkBarricadeCollisions() {
        var hit = false
        for(barricade in barricadeList){
            if (OverlapChecker.overlapRectangles(character.bounds, barricade.bounds)) {
                character.hitBarricade()
                hit = true
            }
        }

        // どの障害物ともあたってない状態ならリリースする
        if(!hit) character.releaseBarricade()
    }

    private fun checkItemCollisions() {
        var hitItemList = ArrayList<IStaticObject>()
        for (candy in itemList) {
            if (OverlapChecker.overlapRectangles(character.bounds, candy.bounds)) {
                hitItemList.add(candy)
                score.AddScore(10)
                listener.food()
            }
        }

        if(hitItemList.size == 0) return
        for(hitItem in hitItemList){
            itemList.remove(hitItem)
        }
    }

    private fun checkGameOver() {
        // TODO: ゲームオーバーをチェック
    }

    companion object {
        val WIDTH = 25
        val HEIGHT = 15
        var acceleration = Vector2(1f, 0f)
    }
}
