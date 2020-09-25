package com.trs.game.model;


import com.badlogic.gdx.math.Rectangle;

public class TempWall implements Wall {

    private double rotation;
    private Rectangle rect;
    private int lifetime;

    public TempWall(double rotation, Rectangle rect, int lifetime){
        this.rotation = rotation;
        this.rect = rect;
        this.lifetime = lifetime;
    }

    @Override
    public Wall timerStep() {
        return null;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }
}
