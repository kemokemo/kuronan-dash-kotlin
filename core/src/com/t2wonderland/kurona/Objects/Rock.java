package com.t2wonderland.kurona.objects;

import com.t2wonderland.kurona.models.GameObject;

public class Rock extends GameObject {

    public static final float WIDTH = 1f;
    public static final float HEIGHT = 1f;

    public Rock(float x, float y) {
        super(x, y, WIDTH, HEIGHT);
    }
}
