package com.t2wonderland.kurona.models;

import com.t2wonderland.kurona.Assets;
import com.t2wonderland.kurona.interfaces.IWorldListener;

public class WorldListener implements IWorldListener {
    @Override
    public void sp() {
        // �Z�����T�E���h
        Assets.playSound(Assets.spSound);
    }

    @Override
    public void food() {
        // �H�ו����ׂ�T�E���h
        Assets.playSound(Assets.foodSound);
    }
}
