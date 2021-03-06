package com.trs.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Projectile {

    private final double SPEED = 3;

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
            if(Intersector.overlapConvexPolygons(polygon, wall.getPolygon())){
                collision(wall);
                break;
            }
        }
    }

    private void collision(Wall wall){
        System.out.println("Collision");

        double collisionAngle = 0;
        for(int i = 0; i < 4; i++){

            Polygon collision = wall.getCollisionPolygons()[i];
            if(Intersector.overlapConvexPolygons(polygon, collision)){
                float[] vertices = collision.getVertices();
                switch (i){
                    case 0: collisionAngle = StaticMath.calculateAngle((int) vertices[0], (int) vertices[1], (int) vertices[2], (int) vertices[3]);
                            break;
                    case 1: collisionAngle = StaticMath.calculateAngle((int) vertices[2], (int) vertices[3], (int) vertices[4], (int) vertices[5]);
                            break;
                    case 2: collisionAngle = StaticMath.calculateAngle((int) vertices[0], (int) vertices[1], (int) vertices[2], (int) vertices[3]);
                            break;
                    case 3: collisionAngle = StaticMath.calculateAngle((int) vertices[2], (int) vertices[3], (int) vertices[4], (int) vertices[5]);
                }
                break;
            }
        }

        setxPos((int) (getxPos() - this.movementX));
        setyPos((int) (getyPos() - this.movementY));

        double alpha = StaticMath.calculateAngle(0,0, (int) movementX, (int) movementY);
        double delta = (Math.PI / 2) - collisionAngle;
        double beta = +((Math.PI / 2) - alpha - delta);
        double phi = beta + collisionAngle;

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
        float[] positions = polygon.getVertices();

        positions[0] = xPos;
        positions[2] = xPos + 5;
        positions[4] = xPos + 5;
        positions[6] = xPos;

        polygon.setVertices(positions);
    }

    public void setyPos(int yPos){
        float[] positions = polygon.getVertices();

        positions[1] = yPos;
        positions[3] = yPos;
        positions[5] = yPos + 5;
        positions[7] = yPos + 5;

        polygon.setVertices(positions);
    }

    public int getRadius(){
        return 5;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}
