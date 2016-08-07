package com.t2wonderland.kurona.Models;

import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.Interfaces.IWorldListener;

public class WorldListener implements IWorldListener {
    @Override
    public void sp() {
        Assets.playSound(Assets.spSound);
    }

    @Override
    public void food() {
        Assets.playSound(Assets.foodSound);
    }
}
