package com.trs.game.model;

import com.badlogic.gdx.math.Circle;
import com.trs.game.StaticMath;

public class Projectile {

    private final int SPEED = 3;

    private double movementX;
    private double movementY;

    private Circle circle;

    public Projectile(int xPos, int yPos, int xPosMonster, int yPosMonster){
        this.circle = new Circle(xPos, yPos, 5);

        // calculating values for movementX and movementY
        double angle = StaticMath.calculateAngle((int) circle.x, (int) circle.y, xPosMonster, yPosMonster);

        movementX = Math.cos(angle) * SPEED;
        movementY = Math.sin(angle) * SPEED;
    }

    public void collision(){

    }

    public void move(){
        circle.x += this.movementX;
        circle.y += this.movementY;
    }

    public int getxPos() {
        return (int) circle.x;
    }

    public int getyPos() {
        return (int) circle.y;
    }

    public int getRadius(){
        return (int) circle.radius;
    }

    public Circle getCircle() {
        return circle;
    }
}
