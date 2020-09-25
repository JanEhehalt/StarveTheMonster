package com.trs.game.model;

import com.badlogic.gdx.math.Polygon;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Projectile {

    private final int SPEED = 3;

    private double movementX;
    private double movementY;

    private Polygon polygon;

    public Projectile(int xPos, int yPos, int xPosMonster, int yPosMonster){
        float[] positions = new float[8];
        int length = 5;
        positions[0] = xPos;
        positions[1] = yPos;
        positions[2] = xPos + length;
        positions[3] = yPos;
        positions[4] = xPos + length;
        positions[5] = yPos + length;
        positions[6] = xPos;
        positions[7] = yPos + length;

        this.polygon = new Polygon(positions);

        // calculating values for movementX and movementY
        double angle = StaticMath.calculateAngle((int) polygon.getVertices()[0], (int) polygon.getVertices()[1], xPosMonster, yPosMonster);

        movementX = Math.cos(angle) * SPEED;
        movementY = Math.sin(angle) * SPEED;
    }



    public void move(ArrayList<Wall> walls){
        checkCollision(walls);

        setxPos((int) (getxPos() + this.movementX));
        setyPos((int) (getyPos() + this.movementY));
    }

    private void checkCollision(ArrayList<Wall> walls){
        for(Wall wall : walls){
            if(/*Intersector.overlaps(circle, wall.getPolygon())*/ false){

                setxPos((int) (getxPos() - this.movementX));
                setyPos((int) (getyPos() - this.movementY));

                collision(wall);
                break;
            }
        }
    }

    private void collision(Wall wall){
        System.out.println("Collision");

        double alpha = Math.atan(movementY/movementX);
        double delta = (Math.PI / 2) - Math.toRadians(wall.getRotation());
        double beta = +((Math.PI / 2) - alpha - delta);
        double phi = beta + Math.toRadians(wall.getRotation());

        movementX = Math.cos(phi) * SPEED;
        movementY = Math.sin(phi) * SPEED;
    }

    public int getxPos() {
        return (int) polygon.getVertices()[0];
    }

    public int getyPos() {
        return (int) polygon.getVertices()[1];
    }

    public void setxPos(int xPos){
        polygon.getVertices()[0] = xPos;
        polygon.getVertices()[2] = xPos + 5;
        polygon.getVertices()[4] = xPos + 5;
        polygon.getVertices()[6] = xPos;
    }

    public void setyPos(int yPos){
        polygon.getVertices()[1] = yPos;
        polygon.getVertices()[3] = yPos;
        polygon.getVertices()[5] = yPos + 5;
        polygon.getVertices()[7] = yPos + 5;
    }

    public int getRadius(){
        return 5;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
