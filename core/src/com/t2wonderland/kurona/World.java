/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.t2wonderland.kurona;

import com.badlogic.gdx.math.Vector2;

public class World {
	// play sound
	public interface WorldListener {
		public void sp ();
		public void food ();
	}

	// 800/32=25 is the width of the window.
	public static final float WORLD_WIDTH = 25;
	public static final float WORLD_HEIGHT = 15;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public final Kurona kurona;
	public static Vector2 acel = new Vector2(1f, 0);
	public final WorldListener listener;

	public int score;
	public int state;

	public World (WorldListener listener) {
		this.kurona = new Kurona(1, 1);
		this.listener = listener;
		generateLevel();

		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}

	private void generateLevel () {
		// settings of the level
	}

	public void update (float deltaTime, float accelX) {
		updateKurona(deltaTime, accelX);
		if (kurona.state != Kurona.KURONA_STATE_HIT) checkCollisions();
		checkGameOver();
	}

	private void updateKurona (float deltaTime, float accelX) {
		kurona.update(deltaTime);
	}

	private void checkCollisions () {
		// check colision
	}

	private void checkGameOver () {
		// check gama over
	}
}
