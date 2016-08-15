package com.t2wonderland.kurona.Models

import com.badlogic.gdx.math.Vector2
import com.t2wonderland.kurona.Models.HeightCalculator
import java.util.ArrayList
import java.util.Random

import com.t2wonderland.kurona.Interfaces.ICharacterObject
import com.t2wonderland.kurona.Interfaces.IStaticObject
import com.t2wonderland.kurona.Objects.Candy
import com.t2wonderland.kurona.Objects.Rock

class World(val listener: WorldListener, val selectedCharacter: CharacterSelect) {

    private val _characterFactory: CharacterFactory
    val _character: ICharacterObject

    val barricadeList: ArrayList<IStaticObject>
    val itemList: ArrayList<IStaticObject>
    val rand: Random
    var score: Int = 0
    var state: WorldState

    init {
        // 外部からキャクター選択情報をもらってFactoryに渡す
        this._characterFactory = CharacterFactory(selectedCharacter)
        this._character = _characterFactory.createCharacter()

        this.barricadeList = ArrayList()
        this.itemList = ArrayList()
        this.score = 0
        this.state = WorldState.Running
        this.rand = Random()

        generateLevel(1)
    }

    private fun generateLevel(level : Int) {
        for (counter in 0..10 * (10-level)) {
            val item = Candy()
            // 乱数から3段階の高さ情報を作る
            item.setInitialPosition(WIDTH.toFloat() * 20f * rand.nextFloat(),
                    HeightCalculator.Calculate(HEIGHT.toFloat() * rand.nextFloat()) ?: 1f)
            itemList.add(item)
        }

        for(counter in 0..20 * level){
            val barricade = Rock()
            // 乱数から3段階の高さ情報を作る
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
        _character.updatePosition(deltaTime, acceleration)
    }

    private fun checkCollisions() {
        checkBarricadeCollisions()
        checkItemCollisions()
    }

    private fun checkBarricadeCollisions() {
        var hit = false
        for(barricade in barricadeList){
            if (OverlapChecker.overlapRectangles(_character.bounds, barricade.bounds)) {
                _character.hitBarricade()
                hit = true
            }
        }

        // どの障害物ともあたってない状態ならリリースする
        if(!hit) _character.releaseBarricade()
    }

    private fun checkItemCollisions() {
        var hitItemList = ArrayList<IStaticObject>()
        for (candy in itemList) {
            if (OverlapChecker.overlapRectangles(_character.bounds, candy.bounds)) {
                hitItemList.add(candy)
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
