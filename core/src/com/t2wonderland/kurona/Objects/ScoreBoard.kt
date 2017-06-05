package com.t2wonderland.kurona.Objects

import com.badlogic.gdx.math.Vector2

/**
 * ScoreBoard describes current score.
 */
class ScoreBoard(width: Float, height: Float) {
    val position: Vector2
    var score: Int = 0

    init {
        position = Vector2(width * 0.85f, height * 0.95f)
    }

    override fun toString(): String {
        return "Score: $score"
    }

    fun AddScore(point: Int) {
        score += point
    }
}