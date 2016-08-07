package com.t2wonderland.kurona.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.t2wonderland.kurona.Interfaces.CharacterState;
import com.t2wonderland.kurona.Interfaces.ICharacterObject;

public class Kurona implements ICharacterObject {

	private float VELOCITY_DASH = 10f;
	private float VELOCITY_SLOW = 2.5f;
	private float VELOCITY_SP = 20f;

	private float WIDTH = 2f;
	private float HEIGHT = 2f;

	private Vector2 _size = new Vector2(WIDTH, HEIGHT);
	private Vector2 _position;
	private Rectangle _bounds;
	private Vector2 _velocity;
	private CharacterState _state;
	private float _stateTime;
	private float _maxVelocityX, _maxVelocityY;
	
	public Kurona() {
		_state = CharacterState.Slow;
		_stateTime = 0;

		// starting at the slow mode
		_velocity = new Vector2();
		_velocity.x = VELOCITY_SLOW;
		_maxVelocityX = VELOCITY_DASH;
		_maxVelocityY = VELOCITY_DASH;
	}

	@Override
	public void updatePosition(float deltaTime, Vector2 accelValue) {
		updateVelocity(deltaTime, accelValue);
		updateState(deltaTime);
		updatePositionAndBounds(deltaTime);
	}

	private void updateVelocity(float deltaTime, Vector2 accelValue) {
		Vector2 velocity = calculateVelocity(deltaTime, accelValue);
		_velocity.add(velocity);
		adjustVelocity();
	}

	private Vector2 calculateVelocity(float deltaTime, Vector2 accelValue) {
		Vector2 velocity = new Vector2();
		if (_maxVelocityX < _velocity.x){
			velocity.x = 0;
		}
		else{
			velocity.x = accelValue.x * deltaTime;
		}

		if (_maxVelocityY < _velocity.y){
			velocity.y = 0;
		}
		else{
			velocity.y = accelValue.y * deltaTime;
		}

		return velocity;
	}

	private void adjustVelocity() {
		if(_maxVelocityX < _velocity.x)
		{
			_velocity.x = _maxVelocityX;
		}

		if (_maxVelocityY<_velocity.y)
		{
			_velocity.y = _maxVelocityY;
		}
	}

	private void updateState(float deltaTime) {
		if (_state == CharacterState.Slow){
			_stateTime += deltaTime/2;
		}
		else{
			_stateTime += deltaTime;
		}
	}

	private void updatePositionAndBounds(float deltaTime) {
		_position.add(_velocity.x * deltaTime, _velocity.y * deltaTime);
		_bounds.x = _position.x - _bounds.width / 2;
		_bounds.y = _position.y - _bounds.height / 2;
	}

	@Override
	public CharacterState getCharacterState() {
		return _state;
	}

	@Override
	public float getStateTime() {
		return _stateTime;
	}

	@Override
	public void hitBarricade() {
		_velocity.x = VELOCITY_SLOW;
		_state = CharacterState.Slow;
	}

	@Override
	public void releaseBarricade() {
		_velocity.x = VELOCITY_DASH;
		_state = CharacterState.Dash;
	}

	@Override
	public void setInitialPosition(float x, float y) {
		this._position = new Vector2(x, y);
		this._bounds = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
	}

	@Override
	public Vector2 getSize() {
		return _size;
	}

	@Override
	public void setPosition(Vector2 position) {
		_position = position;
	}

	@Override
	public Vector2 getPosition() {
		return _position;
	}

	@Override
	public Rectangle getBounds() {
		return _bounds;
	}
}
