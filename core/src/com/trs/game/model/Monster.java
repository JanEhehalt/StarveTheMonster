package com.trs.game.model;

import com.trs.game.StaticMath;

public class Monster {

    private final int SPEED = 3;

    private int xPos;
    private int yPos;
    private double movementX;
    private double movementY;
    private int xPosTarget;
    private int yPosTarget;

    public Monster(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void move(){
        checkTarget();

        this.xPos += this.movementX;
        this.yPos += this.movementY;
    }

    private void checkTarget(){
        if(this.xPos == this.xPosTarget && this.yPos == this.yPosTarget){
            this.generateNewTarget();
        }
    }

    private void generateNewTarget(){
        xPosTarget = (int) (Math.random() * 1400) + 100;
        yPosTarget = (int) (Math.random() * 700) + 100;

        calculateMovement();
    }

    private void calculateMovement(){
        double angle = StaticMath.calculateAngle(this.xPos, this.yPos, this.xPosTarget, this.yPosTarget);

        movementX = Math.cos(angle) * SPEED;
        movementY = Math.sin(angle) * SPEED;
    }

}
