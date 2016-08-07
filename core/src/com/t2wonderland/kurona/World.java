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

	// 800*480がターゲット解像度で、32*32を1マスとすると25*15マス
	public static final int WIDTH = 25;
	public static final int HEIGHT = 15;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public final Kurona kurona;
	public final Rock rock;
	public final Candy candy;
	public static Vector2 acel = new Vector2(1f, 0f);
	public final WorldListener listener;

	public int score;
	public int state;

	public World (WorldListener listener) {
		this.kurona = new Kurona(1, 1);
		this.rock = new Rock(10,1);
		this.candy = new Candy(20,1);
		this.listener = listener;
		generateLevel();

		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}

	private void generateLevel () {
		// settings of the level
	}

	public void update (float deltaTime, Vector2 accel) {
		checkGameOver();
		checkCollisions();
		updateKurona(deltaTime, accel);
	}

	private void updateKurona (float deltaTime, Vector2 accel) {
		kurona.update(deltaTime, accel);
	}

	private void checkCollisions () {
		checkRockCollisions();
		checkCandyCollisions();
	}

	private void checkRockCollisions () {
		if (OverlapTester.overlapRectangles(kurona.bounds, rock.bounds)){
			kurona.hitRock();
		}
		else{
			kurona.releaseBlock();
		}
	}

	private void checkCandyCollisions(){
		if (OverlapTester.overlapRectangles(kurona.bounds, candy.bounds)){
			listener.food();
		}
	}

	private void checkGameOver () {
		// check gama over
	}
}
