package com.t2wonderland.kurona

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import com.badlogic.gdx.Gdx

object Settings {
    var soundEnabled = true
    // high score
    val highScores = intArrayOf(100, 80, 50, 30, 10)
    val file = ".kuronandash"

    fun load() {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(InputStreamReader(Gdx.files.external(file).read()))
            soundEnabled = java.lang.Boolean.parseBoolean(reader.readLine())
            for (i in 0..4) {
                highScores[i] = Integer.parseInt(reader.readLine())
            }
        } catch (e: Throwable) {
            // TODO: ログ記録
        } finally {
            try {
                if (reader != null) reader.close()
            } catch (e: IOException) {
                // TODO: ログ記録
            }

        }
    }

    fun save() {
        var writer: BufferedWriter? = null
        try {
            writer = BufferedWriter(OutputStreamWriter(Gdx.files.external(file).write(false)))
            writer.write(java.lang.Boolean.toString(soundEnabled))
            for (i in 0..4) {
                writer.write(Integer.toString(highScores[i]))
            }

        } catch (e: Throwable) {
            // TODO: ログ記録
        } finally {
            try {
                if (writer != null) writer.close()
            } catch (e: IOException) {
                // TODO: ログ記録
            }

        }
    }

    fun addScore(score: Int) {
        for (i in 0..4) {
            if (highScores[i] < score) {
                for (j in 4 downTo i + 1)
                    highScores[j] = highScores[j - 1]
                highScores[i] = score
                break
            }
        }
    }
}
