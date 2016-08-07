package com.t2wonderland.kurona.Interfaces

object HeightCalculator{
    fun Calculate(height : Float) : Float?{
        if (0 <= height && height < 5) {
            return 1f
        } else if (5 <= height && height < 10) {
            return 6f
        } else if (10 <= height && height < 15) {
            return 11f
        } else {
            // touched point is out of the range
            return null
        }
    }
}
