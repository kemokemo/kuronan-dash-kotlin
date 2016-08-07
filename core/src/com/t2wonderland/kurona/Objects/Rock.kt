package com.t2wonderland.kurona.Objects

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.t2wonderland.kurona.Assets
import com.t2wonderland.kurona.Interfaces.IStaticObject

class Rock : IStaticObject {

    val WIDTH = 1f
    val HEIGHT = 1f

    override var position: Vector2 = Vector2()
    override val size = Vector2(WIDTH, HEIGHT)
    override val bounds: Rectangle = Rectangle()
    override val image: TextureRegion = Assets.gameRock

    override fun setInitialPosition(x: Float, y: Float) {
        this.position.x = x
        this.position.y = y

        this.bounds.x = x - WIDTH / 2
        this.bounds.y = y - HEIGHT / 2
        this.bounds.width = WIDTH
        this.bounds.height = HEIGHT
    }
}
