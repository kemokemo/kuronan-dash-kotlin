package com.t2wonderland.kurona.Objects;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.t2wonderland.kurona.Interfaces.IGameObject;

public class Candy implements IGameObject {

    float WIDTH = 1f;
    float HEIGHT = 1f;

    Vector2 _size = new Vector2(WIDTH, HEIGHT);
    Vector2 _position;
    Rectangle _bounds;

    @Override
    public void setInitialPosition(float x, float y) {
        this._position = new Vector2(x, y);
        this._bounds = new Rectangle(x - WIDTH / 2, y - HEIGHT / 2, WIDTH, HEIGHT);
    }

    @Override
    public Vector2 getSize() {
        return _size;
    }

    @Override
    public Vector2 getPosition() {
        return _position;
    }

    @Override
    public Rectangle getBounds() {
        return _bounds;
    }
}
