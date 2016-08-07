package com.t2wonderland.kurona;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.badlogic.gdx.Gdx;

public class Settings {
	public static boolean soundEnabled = true;
	// hich score
	public final static int[] highScores = new int[] {100, 80, 50, 30, 10};
	public final static String file = ".kuronandash";

	public static void load () {
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(Gdx.files.external(file).read()));
			soundEnabled = Boolean.parseBoolean(in.readLine());
			for (int i = 0; i < 5; i++) {
				highScores[i] = Integer.parseInt(in.readLine());
			}
		} catch (Throwable e) {
			// TODO: ログ記録
		} finally {
			try {
				if (in != null) in.close();
			} catch (IOException e) {
				// TODO: ログ記録
			}
		}
	}

	public static void save () {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new OutputStreamWriter(Gdx.files.external(file).write(false)));
			out.write(Boolean.toString(soundEnabled));
			for (int i = 0; i < 5; i++) {
				out.write(Integer.toString(highScores[i]));
			}

		} catch (Throwable e) {
			// TODO: ログ記録
		} finally {
			try {
				if (out != null) out.close();
			} catch (IOException e) {
				// TODO: ログ記録
			}
		}
	}

	public static void addScore (int score) {
		for (int i = 0; i < 5; i++) {
			if (highScores[i] < score) {
				for (int j = 4; j > i; j--)
					highScores[j] = highScores[j - 1];
				highScores[i] = score;
				break;
			}
		}
	}
}
