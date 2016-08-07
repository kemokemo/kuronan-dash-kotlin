package com.t2wonderland.kurona.Interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IStaticObject {

    // 初期位置を設定する
    void setInitialPosition(float x, float y);

    // 位置の設定と取得
    void setPosition(Vector2 position);
    Vector2 getPosition();

    // サイズ、領域など描画に必要な情報を取得する
    Vector2 getSize();
    Rectangle getBounds();
}
