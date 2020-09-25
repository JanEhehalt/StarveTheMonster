package com.trs.game.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.trs.game.StaticMath;

import java.lang.reflect.Array;
import java.util.ArrayList;

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



    public void move(ArrayList<Wall> walls){
        checkCollision(walls);

        circle.x += this.movementX;
        circle.y += this.movementY;
    }

    private void checkCollision(ArrayList<Wall> walls){
        for(Wall wall : walls){
            if(/*Intersector.overlaps(circle, wall.getPolygon())*/ false){

                circle.x -= movementX;
                circle.y -= movementY;

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
