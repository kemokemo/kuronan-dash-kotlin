package com.t2wonderland.kurona.Models

import com.t2wonderland.kurona.Assets
import com.t2wonderland.kurona.Interfaces.IWorldListener

class WorldListener : IWorldListener {
    override fun sp() {
        Assets.playSound(Assets.spSound)
    }

    override fun food() {
        Assets.playSound(Assets.foodSound)
    }
}
