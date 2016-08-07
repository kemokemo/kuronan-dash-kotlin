package com.t2wonderland.kurona.models;

import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.interfaces.IWorldListener;

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
