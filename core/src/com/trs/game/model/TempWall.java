package com.trs.game.model;


import com.badlogic.gdx.math.Polygon;
import com.trs.game.StaticMath;

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
        return null;
    }

    public double getRotation() {
        float[] vertices = polygon.getVertices();

        return Math.toDegrees(StaticMath.calculateAngle((int) vertices[0], (int) vertices[1], (int) vertices[6], (int) vertices[7]));
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
