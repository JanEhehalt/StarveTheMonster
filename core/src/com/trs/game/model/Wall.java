package com.trs.game.model;

import com.badlogic.gdx.math.Polygon;

public interface Wall {
    public Wall timerStep();
    public Polygon getPolygon();
    public double getRotation();
}
