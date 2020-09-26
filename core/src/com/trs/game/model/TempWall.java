package com.trs.game.model;


import com.badlogic.gdx.math.Polygon;

public class TempWall implements Wall {

    private double rotation;
    private Polygon polygon;
    private int lifetime;

    public TempWall(double rotation, Polygon polygon, int lifetime){
        this.rotation = rotation;
        this.polygon = polygon;
        this.lifetime = lifetime;
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

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
