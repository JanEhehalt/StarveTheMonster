package com.trs.game.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Monster {

    private final int SPEED = 5;
    private final int WIDTH = 50;
    private final int HEIGHT = 50;

    private int xPos;
    private int yPos;
    private double movementX;
    private double movementY;
    private int xPosTarget;
    private int yPosTarget;
    private int hp;
    private int maxHp;
    private boolean isDead;

    public Monster(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.maxHp = 500;
        this.hp = 350;
        isDead = false;

        generateNewTarget();
    }

    public void drawMonster(ShapeRenderer renderer, PolygonSpriteBatch polygonSpriteBatch, SpriteBatch batch, BitmapFont font){
        if(renderer.isDrawing()) renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        //BODY
        renderer.setColor(Color.BLACK);
        renderer.rect(xPos, yPos, WIDTH,HEIGHT);
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

        // HUNGER BAR
        renderer.setColor(Color.BLACK);
        renderer.rect(xPos - 26, yPos + 55, 102,22);
        renderer.setColor(Color.BROWN);
        renderer.rect(xPos - 25, yPos + 56, 100f * (float)hp/(float)maxHp,20);
        renderer.setColor(Color.DARK_GRAY);
        renderer.rect(xPos - 25 + 100f * (float)hp/(float)maxHp, yPos + 56, 100 - 100f * (float)hp/(float)maxHp,20);

        renderer.end();

        batch.begin();
        font.getData().setScale(0.8f);
        font.setColor(Color.WHITE);
        font.draw(batch, "Saturation",xPos - 22, yPos + 71);
        font.getData().setScale(1f);
        font.setColor(Color.BLACK);
        batch.end();


    }

    public void move(ArrayList<Wall> walls, ArrayList<Projectile> projectiles){
        checkTarget();

        this.xPos += this.movementX;
        this.yPos += this.movementY;

        // Collisions
        Polygon monsterPolygon = getMonsterPolygon();

        hp--;

        for (int i = projectiles.size() - 1; i >= 0; i--) {
            Projectile projectile = projectiles.get(i);
            if (Intersector.overlapConvexPolygons(monsterPolygon, projectile.getPolygon())) {
                projectiles.remove(i);
                hit();
            }
        }
        if(hp <= 0){
            die();
        }

        for(int i = walls.size() - 1; i >= 0; i--){
            Polygon wall = walls.get(i).getPolygon();
            if(Intersector.overlapConvexPolygons(monsterPolygon, wall)){
                setxPos(getxPos() - (int) movementX);
                setyPos(getyPos() - (int) movementY);

                generateNewTarget();
            }
        }
    }

    private void hit(){
        hp += 25;
        if(hp > maxHp){
            hp = maxHp;
        }
    }

    public Polygon getMonsterPolygon(){
        float[] verticesMonster = new float[8];
        verticesMonster[0] = xPos;
        verticesMonster[1] = yPos;
        verticesMonster[2] = xPos + WIDTH;
        verticesMonster[3] = yPos;
        verticesMonster[4] = xPos + WIDTH;
        verticesMonster[5] = yPos + HEIGHT;
        verticesMonster[6] = xPos;
        verticesMonster[7] = yPos + HEIGHT;
        return new Polygon(verticesMonster);
    }

    private void checkTarget(){
        if(this.xPos == this.xPosTarget && this.yPos == this.yPosTarget){
            this.generateNewTarget();
        }
        if(Math.random() >= 0.95){
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

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    private void die(){
        this.isDead = true;
    }

    public boolean getIsDead(){
        return this.isDead;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
