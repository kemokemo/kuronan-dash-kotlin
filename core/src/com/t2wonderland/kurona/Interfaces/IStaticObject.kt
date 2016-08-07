package com.t2wonderland.kurona.Interfaces

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2

interface IStaticObject {

    // 初期位置を設定する
    fun setInitialPosition(x: Float, y: Float)

    // 位置の設定と取得
    var position: Vector2

    // サイズ、領域など描画に必要な情報を取得する
    val size: Vector2
    val bounds: Rectangle
}
