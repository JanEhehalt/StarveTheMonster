package com.trs.game.model;

import com.badlogic.gdx.math.Polygon;

public class PermWall implements Wall {

    private double rotation;
    private Polygon polygon;

    public PermWall(double rotation, Polygon polygon){
        this.rotation = rotation;
        this.polygon = polygon;
    }

    @Override
    public Wall timerStep() {
        return this;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public void setPolygon(Polygon polygon) {
        this.polygon = polygon;
    }

}
