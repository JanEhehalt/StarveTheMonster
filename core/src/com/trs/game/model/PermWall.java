package com.trs.game.model;

import com.badlogic.gdx.math.Polygon;
import com.trs.game.StaticMath;

public class PermWall implements Wall {

    private double rotation;
    private Polygon polygon;
    private Polygon[] collisionPolygons;

    public PermWall(double rotation, Polygon polygon){
        this.rotation = rotation;
        this.polygon = polygon;
        collisionPolygons = StaticMath.createCollisionPolygons(polygon);
    }

    @Override
    public Wall timerStep() {
        return this;
    }

    public double getRotation() {
        float[] vertices = polygon.getVertices();

        return StaticMath.calculateAngle((int) vertices[0], (int) vertices[1], (int) vertices[6], (int) vertices[7]);
    }

    @Override
    public Polygon[] getCollisionPolygons() {
        return collisionPolygons;
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
