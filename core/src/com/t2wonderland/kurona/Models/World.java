package com.t2wonderland.kurona.Models;

import com.badlogic.gdx.math.Vector2;
import java.util.ArrayList;
import java.util.Random;

import com.t2wonderland.kurona.Interfaces.ICharacterObject;
import com.t2wonderland.kurona.Interfaces.IStaticObject;
import com.t2wonderland.kurona.Objects.Candy;
import com.t2wonderland.kurona.Objects.Rock;

public class World {

	public static final int WIDTH = 25;
	public static final int HEIGHT = 15;
	public static final int WORLD_STATE_RUNNING = 0;
	public static final int WORLD_STATE_NEXT_LEVEL = 1;
	public static final int WORLD_STATE_GAME_OVER = 2;

	private CharacterFactory _characterFactory;
	public final ICharacterObject _character;

	public final Rock rock;
	public final ArrayList<IStaticObject> candys;
	public static Vector2 acel = new Vector2(1f, 0f);
	public final WorldListener listener;
	public final Random rand;

	public int score;
	public int state;

	public World (WorldListener listener) {
		this._characterFactory = new CharacterFactory();

		// 外部からキャクター選択情報をもらってFactoryに渡す
		this._character = _characterFactory.createCharacter();

		this.rock = new Rock();
		this.rock.setInitialPosition(10,1);
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
			Candy candy = new Candy();
			candy.setInitialPosition(WIDTH*20*rand.nextFloat(), 1);
			candys.add(candy);
		}
	}

	public void update (float deltaTime, Vector2 accel) {
		checkGameOver();
		checkCollisions();
		updateKurona(deltaTime, accel);
	}

	private void updateKurona (float deltaTime, Vector2 accel) {
		_character.updatePosition(deltaTime, accel);
	}

	private void checkCollisions () {
		checkRockCollisions();
		checkCandyCollisions();
	}

	private void checkRockCollisions () {
		if (OverlapChecker.overlapRectangles(_character.getBounds(), rock.getBounds())){
			_character.hitBarricade();
		}
		else{
			_character.releaseBarricade();
		}
	}

	private void checkCandyCollisions(){
		int len = candys.size();
		for (int counter = 0; counter < len; counter++) {
			IStaticObject candy = candys.get(counter);
			if (OverlapChecker.overlapRectangles(_character.getBounds(), candy.getBounds())) {
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
