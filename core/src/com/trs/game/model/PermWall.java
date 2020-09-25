package com.trs.game.model;

import com.badlogic.gdx.math.Rectangle;

public class PermWall implements Wall {

    private double rotation;
    private Rectangle rect;

    public PermWall(double rotation, Rectangle rect){
        this.rotation = rotation;
        this.rect = rect;
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

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
}
