package com.trs.game.model;

import com.trs.game.StaticMath;

public class Projectile {

    private final int SPEED = 3;

    private int xPos;
    private int yPos;
    private double movementX;
    private double movementY;

    public Projectile(int xPos, int yPos, int xPosMonster, int yPosMonster){
        this.xPos = xPos;
        this.yPos = yPos;

        // calculating values for movementX and movementY
        double angle = StaticMath.calculateAngle(this.xPos, this.yPos, xPosMonster, yPosMonster);

        movementX = Math.cos(angle) * SPEED;
        movementY = Math.sin(angle) * SPEED;
    }

    public void collision(){

    }

    public void move(){
        this.xPos += this.movementX;
        this.yPos += this.movementY;
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
