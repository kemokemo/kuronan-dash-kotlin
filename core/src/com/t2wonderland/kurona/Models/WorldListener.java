package com.t2wonderland.kurona.models;

import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.interfaces.IWorldListener;

public class WorldListener implements IWorldListener {
    @Override
    public void sp() {
        // 技発動サウンド
        Assets.playSound(Assets.spSound);
    }

    @Override
    public void food() {
        // 食べ物たべるサウンド
        Assets.playSound(Assets.foodSound);
    }
}
