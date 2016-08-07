package com.t2wonderland.kurona;

public class Kurona extends DynamicGameObject {
	public static final int STATE_DASH = 0;
	public static final int STATE_HIT = 1;
	public static final int STATE_SLOW = 2;
	public static final int STATE_SP = 3;
	public static final float VELOCITY_DASH = 10f;
	public static final float VELOCITY_SLOW = 2.5f;
	public static final float VELOCITY_SP = 20f;
	public static final float WIDTH = 2f;
	public static final float HEIGHT = 2f;

	int state;
	float stateTime;
	float maxVelX, maxVelY;
	
	public Kurona(float x, float y) {
		super(x, y, WIDTH, HEIGHT);
		state = STATE_DASH;
		// starting at the slow mode
		velocity.x = VELOCITY_SLOW;
		stateTime = 0;
		maxVelX = VELOCITY_DASH;
		maxVelY = VELOCITY_DASH;
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
		if (state == STATE_SLOW){
			// SLOWモードでは描画もアニメーションもゆっくりにしたいので、経過時間を半分にする
			stateTime += deltaTime/2;
		}
		else{
			stateTime += deltaTime;
		}
	}
	
	// hit
	public void hitBlock () {
		velocity.x = VELOCITY_SLOW;
		state = STATE_SLOW;
	}

	// Release
	public void releaseBlock(){
		velocity.x = VELOCITY_DASH;
		state = STATE_DASH;
	}
}
