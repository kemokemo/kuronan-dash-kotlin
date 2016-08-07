package com.t2wonderland.kurona.Objects

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.t2wonderland.kurona.Interfaces.IStaticObject

class Rock : IStaticObject {

    private val WIDTH = 1f
    internal var HEIGHT = 1f

    override var size = Vector2(WIDTH, HEIGHT)
        internal set
    override var position: Vector2 = Vector2()
    override var bounds: Rectangle = Rectangle()
        internal set

    override fun setInitialPosition(x: Float, y: Float) {
        this.position.x = x
        this.position.y = y

        this.bounds.x = x - WIDTH / 2
        this.bounds.y = y - HEIGHT / 2
        this.bounds.width = WIDTH
        this.bounds.height = HEIGHT
    }
}
