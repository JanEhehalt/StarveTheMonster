package com.trs.game.model;

import com.badlogic.gdx.math.Rectangle;

public interface Wall {
    public Wall timerStep();
    public Rectangle getRect();
    public double getRotation();
}
