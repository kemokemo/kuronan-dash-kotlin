package com.t2wonderland.kurona

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

import com.badlogic.gdx.Gdx

object Settings {
    var soundEnabled = true
    // hich score
    val highScores = intArrayOf(100, 80, 50, 30, 10)
    val file = ".kuronandash"

    fun load() {
        var `in`: BufferedReader? = null
        try {
            `in` = BufferedReader(InputStreamReader(Gdx.files.external(file).read()))
            soundEnabled = java.lang.Boolean.parseBoolean(`in`.readLine())
            for (i in 0..4) {
                highScores[i] = Integer.parseInt(`in`.readLine())
            }
        } catch (e: Throwable) {
            // TODO: ログ記録
        } finally {
            try {
                if (`in` != null) `in`.close()
            } catch (e: IOException) {
                // TODO: ログ記録
            }

        }
    }

    fun save() {
        var out: BufferedWriter? = null
        try {
            out = BufferedWriter(OutputStreamWriter(Gdx.files.external(file).write(false)))
            out.write(java.lang.Boolean.toString(soundEnabled))
            for (i in 0..4) {
                out.write(Integer.toString(highScores[i]))
            }

        } catch (e: Throwable) {
            // TODO: ログ記録
        } finally {
            try {
                if (out != null) out.close()
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
