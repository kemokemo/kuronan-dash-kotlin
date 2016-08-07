package com.t2wonderland.kurona.Interfaces

import com.badlogic.gdx.math.Vector2

interface ICharacterObject : IStaticObject {

    // 経過時間と加速度を渡して、現在位置をアップデートさせる
    fun updatePosition(deltaTime: Float, accelValue: Vector2)

    // キャラクターのステータスを取得する
    val characterState: CharacterState
    val stateTime: Float

    // 障害物への衝突と開放
    fun hitBarricade()

    fun releaseBarricade()
}
