package com.t2wonderland.kurona;

public class Kurona extends DynamicGameObject {
	public static final int KURONA_STATE_DASH = 0;
	public static final int KURONA_STATE_HIT= 1;
	public static final int KURONA_STATE_SLOW = 2;
	public static final int KURONA_STATE_SP = 3;
	public static final float KURONA_DASH_VELOCITY = 5;
	public static final float KURONA_SLOW_VELOCITY = 2.5f;
	public static final float KURONA_SP_VELOCITY = 10;
	public static final float KURONA_WIDTH = 100f;
	public static final float KURONA_HEIGHT = 100f;

	int state;
	float stateTime;
	float maxVelX, maxVelY;
	
	public Kurona(float x, float y) {
		super(x, y, KURONA_WIDTH, KURONA_HEIGHT);
		state = KURONA_STATE_DASH;
		// starting at the slow mode
		velocity.x = KURONA_SLOW_VELOCITY;
		stateTime = 0;
		maxVelX = 10f;
		maxVelY = 10f;
	}
	
	public void update (float deltaTime) {
		float velX;
		if (maxVelX < velocity.x){
			velX = 0;
		}
		else{
			velX = World.acel.x * deltaTime;
		}
		float velY;
		if (maxVelY < velocity.y){
			velY = 0;
		}
		else{
			velY = World.acel.y * deltaTime;
		}
		velocity.add(velX, velY);

		// update position
		position.add(velocity.x * deltaTime, velocity.y * deltaTime);
		bounds.x = position.x - bounds.width / 2;
		bounds.y = position.y - bounds.height / 2;
		
		// update status
		stateTime += deltaTime;
	}
	
	// hit
	public void hitBlock () {
		velocity.x = KURONA_SLOW_VELOCITY;
		state = KURONA_STATE_SLOW;
		stateTime = 0;
	}
}
