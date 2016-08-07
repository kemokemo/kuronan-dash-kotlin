package com.t2wonderland.kurona.Interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IStaticObject {

    // 初期位置を設定する
    void setInitialPosition(float x, float y);

    // サイズ、位置、領域など描画に必要な情報をもらう
    Vector2 getSize();
    Vector2 getPosition();
    Rectangle getBounds();
}
