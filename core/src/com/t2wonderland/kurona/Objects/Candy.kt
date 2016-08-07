package com.t2wonderland.kurona.Objects

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.t2wonderland.kurona.Interfaces.IStaticObject

class Candy : IStaticObject {

    private var _width = 1f
    private var _height = 1f

    var _position: Vector2 = Vector2()
    var _bounds: Rectangle = Rectangle()
    var _size : Vector2 = Vector2(_width, _height)

    override fun setInitialPosition(x: Float, y: Float) {
        this._position.x = x
        this._position.y = y
        this._bounds = Rectangle(x - _width / 2, y - _height / 2, _width, _height)
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
