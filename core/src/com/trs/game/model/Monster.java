package com.trs.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    public void drawMonster(ShapeRenderer renderer){
        if(renderer.isDrawing()) renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        //BODY
        renderer.setColor(Color.BLACK);
        renderer.rect(xPos, yPos, 50,50);
        //EYES
        renderer.setColor(Color.RED);
        renderer.rect(xPos + 10, yPos + 30, 5, 10);
        renderer.rect(xPos + 35, yPos + 30, 5, 10);
        //MOUTH
        renderer.setColor(Color.WHITE);
        renderer.rect(xPos + 10, yPos + 10, 30, 10);
        //TEETH
        renderer.setColor(Color.RED);
        renderer.triangle(xPos+15,yPos+20,xPos+23,yPos+20,xPos+19,yPos+13);
        renderer.triangle(xPos+27,yPos+20,xPos+35,yPos+20,xPos+31,yPos+13);

        renderer.end();
    }

    public void move(){
        this.xPos += this.movementX;
        this.yPos += this.movementY;
    }

    private void checkTarget(){
        if(this.xPos == this.xPosTarget && this.yPos == this.yPosTarget){
            this.generateNewTarget();
        }
    }

    private void generateNewTarget(){
        // TODO: generate new target coordinates

        calculateMovement();
    }

    private void calculateMovement(){
        double angle = StaticMath.calculateAngle(this.xPos, this.yPos, this.xPosTarget, this.yPosTarget);

        movementX = Math.cos(angle) * SPEED;
        movementY = Math.sin(angle) * SPEED;
    }

}
