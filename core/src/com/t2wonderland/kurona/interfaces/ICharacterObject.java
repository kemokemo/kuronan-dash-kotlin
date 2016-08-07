package com.t2wonderland.kurona.Interfaces;

import com.badlogic.gdx.math.Vector2;

public interface ICharacterObject extends IStaticObject {

    // 経過時間と加速度を渡して、現在位置をアップデートさせる
    void updatePosition(float deltaTime, Vector2 accelValue);

    // キャラクターのステータスを取得する
    CharacterState getCharacterState();

    // 障害物への衝突と開放
    void hitBarricade();
    void releaseBarricade();
}
