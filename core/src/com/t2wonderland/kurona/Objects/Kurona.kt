package com.t2wonderland.kurona.Objects

import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import com.t2wonderland.kurona.Interfaces.CharacterState
import com.t2wonderland.kurona.Interfaces.ICharacterObject

class Kurona : ICharacterObject {

    private val VELOCITY_DASH = 10f
    private val VELOCITY_SLOW = 2.5f
    private val VELOCITY_SP = 20f

    private val WIDTH = 2f
    private val HEIGHT = 2f

    override val size = Vector2(WIDTH, HEIGHT)
    override var position: Vector2 = Vector2()
    override var bounds: Rectangle = Rectangle()
        private set
    private val _velocity: Vector2
    override var characterState: CharacterState = CharacterState.Dash
        private set
    override var stateTime: Float = 0.toFloat()
        private set
    private val _maxVelocityX: Float
    private val _maxVelocityY: Float

    init {
        characterState = CharacterState.Slow
        stateTime = 0f

        // starting at the slow mode
        _velocity = Vector2()
        _velocity.x = VELOCITY_SLOW
        _maxVelocityX = VELOCITY_DASH
        _maxVelocityY = VELOCITY_DASH
    }

    override fun updatePosition(deltaTime: Float, accelValue: Vector2) {
        updateVelocity(deltaTime, accelValue)
        updateState(deltaTime)
        updatePositionAndBounds(deltaTime)
    }

    private fun updateVelocity(deltaTime: Float, accelValue: Vector2) {
        val velocity = calculateVelocity(deltaTime, accelValue)
        _velocity.add(velocity)
        adjustVelocity()
    }

    private fun calculateVelocity(deltaTime: Float, accelValue: Vector2): Vector2 {
        val velocity = Vector2()
        if (_maxVelocityX < _velocity.x) {
            velocity.x = 0f
        } else {
            velocity.x = accelValue.x * deltaTime
        }

        if (_maxVelocityY < _velocity.y) {
            velocity.y = 0f
        } else {
            velocity.y = accelValue.y * deltaTime
        }

        return velocity
    }

    private fun adjustVelocity() {
        if (_maxVelocityX < _velocity.x) {
            _velocity.x = _maxVelocityX
        }

        if (_maxVelocityY < _velocity.y) {
            _velocity.y = _maxVelocityY
        }
    }

    private fun updateState(deltaTime: Float) {
        if (characterState === CharacterState.Slow) {
            stateTime += deltaTime / 2
        } else {
            stateTime += deltaTime
        }
    }

    private fun updatePositionAndBounds(deltaTime: Float) {
        position!!.add(_velocity.x * deltaTime, _velocity.y * deltaTime)
        bounds!!.x = position!!.x - bounds!!.width / 2
        bounds!!.y = position!!.y - bounds!!.height / 2
    }

    override fun hitBarricade() {
        _velocity.x = VELOCITY_SLOW
        characterState = CharacterState.Slow
    }

    override fun releaseBarricade() {
        _velocity.x = VELOCITY_DASH
        characterState = CharacterState.Dash
    }

    override fun setInitialPosition(x: Float, y: Float) {
        this.position.x = x
        this.position.y = y

        this.bounds.x = x - WIDTH / 2
        this.bounds.y = y - HEIGHT / 2
        this.bounds.width = WIDTH
        this.bounds.height = HEIGHT
    }
}
