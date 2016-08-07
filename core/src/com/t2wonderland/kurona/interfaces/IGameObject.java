package com.t2wonderland.kurona.interfaces;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public interface IGameObject {
    void setInitialPosition(int x, int y);
    Vector2 getSize();
    Vector2 getPosition();
    Rectangle getBounds();
}
