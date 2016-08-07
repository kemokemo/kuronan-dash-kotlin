package com.t2wonderland.kurona.Interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IGameObject {
    void setInitialPosition(float x, float y);
    Vector2 getSize();
    Vector2 getPosition();
    Rectangle getBounds();
}
