package com.t2wonderland.kurona.Objects

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.t2wonderland.kurona.Interfaces.IStaticObject

class Candy : IStaticObject {

    private var WIDTH = 1f
    private var HEIGHT = 1f

    var _position: Vector2 = Vector2()
    var _bounds: Rectangle = Rectangle()
    var _size : Vector2 = Vector2(WIDTH, HEIGHT)

    override fun setInitialPosition(x: Float, y: Float) {
        this._position.x = x
        this._position.y = y
        this._bounds = Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT)
    }

    override fun getSize(): Vector2 {
        return _size
    }

    override fun setPosition(position: Vector2) {
        _position = position
    }

    override fun getPosition(): Vector2 {
        return _position
    }

    override fun getBounds(): Rectangle {
        return _bounds
    }
}
