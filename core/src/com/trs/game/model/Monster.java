package com.trs.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Monster {

    private final int SPEED = 5;

    private int xPos;
    private int yPos;
    private double movementX;
    private double movementY;
    private int xPosTarget;
    private int yPosTarget;

    public Monster(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        generateNewTarget();
    }

    public void drawMonster(ShapeRenderer renderer, PolygonSpriteBatch polygonSpriteBatch){
        if(renderer.isDrawing()) renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
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
        // TOP
        renderer.triangle(xPos+10,yPos+20,xPos+10,yPos+15,xPos+13,yPos+20);
        renderer.triangle(xPos+13,yPos+20,xPos+16,yPos+15,xPos+19,yPos+20);
        renderer.triangle(xPos+19,yPos+20,xPos+22,yPos+15,xPos+25,yPos+20);
        renderer.triangle(xPos+25,yPos+20,xPos+28,yPos+15,xPos+31,yPos+20);
        renderer.triangle(xPos+31,yPos+20,xPos+34,yPos+15,xPos+37,yPos+20);
        renderer.triangle(xPos+37,yPos+20,xPos+40,yPos+15,xPos+40,yPos+20);
        // BOTTOM
        renderer.triangle(xPos+10,yPos+10,xPos+13,yPos+15,xPos+16,yPos+10);
        renderer.triangle(xPos+16,yPos+10,xPos+19,yPos+15,xPos+22,yPos+10);
        renderer.triangle(xPos+22,yPos+10,xPos+25,yPos+15,xPos+28,yPos+10);
        renderer.triangle(xPos+28,yPos+10,xPos+31,yPos+15,xPos+34,yPos+10);
        renderer.triangle(xPos+34,yPos+10,xPos+37,yPos+15,xPos+40,yPos+10);

        renderer.end();


        /* POLYGON LOGIC (DRAWING)
        PolygonSprite poly;
        Texture textureSolid;
        // Creating the color filling (but textures would work the same way)
        Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pix.setColor(Color.BLACK); // DE is red, AD is green and BE is blue.
        pix.fill();
        textureSolid = new Texture(pix);
        PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
                new float[] {      // Four vertices
                        200, 200,               // Vertex 0         3--2
                        300, 200,               // Vertex 1         | /|
                        400, 500,               // Vertex 2         |/ |
                        300, 500                // Vertex 3         0--1
                }, new short[] {
                0, 1, 2,         // Two triangles using vertex indices.
                0, 2, 3          // Take care of the counter-clockwise direction.
        });
        poly = new PolygonSprite(polyReg);
        polygonSpriteBatch.begin();
        poly.draw(polygonSpriteBatch);
        polygonSpriteBatch.end();
        */

    }

    public void move(ArrayList<Wall> walls){
        checkTarget();

        //this.xPos += this.movementX;
        //this.yPos += this.movementY;
    }

    private void checkTarget(){
        //if(this.xPos == this.xPosTarget && this.yPos == this.yPosTarget){
        //    this.generateNewTarget();
        //}
        if(Math.random() >= 0.9){
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
