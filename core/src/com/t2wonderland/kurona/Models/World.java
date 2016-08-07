package com.t2wonderland.kurona.models;

import com.badlogic.gdx.math.Vector2;
import com.t2wonderland.kurona.objects.Candy;
import com.t2wonderland.kurona.objects.Kurona;
import com.t2wonderland.kurona.objects.Rock;

import java.util.ArrayList;
import java.util.Random;

public class World {
	// play sound
	public interface WorldListener {
		void sp ();
		void food ();
	}

	// 800*480がターゲット解像度で、32*32を1マスとすると25*15マス
	public static final int WIDTH = 25;
	public static final int HEIGHT = 15;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	public final Kurona kurona;
	public final Rock rock;
	public final ArrayList<Candy> candys;
	public static Vector2 acel = new Vector2(1f, 0f);
	public final WorldListener listener;
	public final Random rand;

	public int score;
	public int state;

	public World (WorldListener listener) {
		this.kurona = new Kurona(1, 1);
		this.rock = new Rock(10,1);
		this.candys = new ArrayList();
		this.listener = listener;
		rand = new Random();
		generateLevel();

		this.score = 0;
		this.state = WORLD_STATE_RUNNING;
	}

	private void generateLevel () {
		// settings of the level
		while (candys.size() < 10){
				Candy candy = new Candy(WIDTH*20*rand.nextFloat(), 1);
				candys.add(candy);
		}
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
		if (OverlapChecker.overlapRectangles(kurona.bounds, rock.bounds)){
			kurona.hitRock();
		}
		else{
			kurona.releaseBlock();
		}
	}

	private void checkCandyCollisions(){
		int len = candys.size();
		for (int counter = 0; counter < len; counter++) {
			Candy candy = candys.get(counter);
			if (OverlapChecker.overlapRectangles(kurona.bounds, candy.bounds)) {
				candys.remove(candy);
				len = candys.size();
				listener.food();
			}

		}
	}

	private void checkGameOver () {
		// check gama over
	}
}
