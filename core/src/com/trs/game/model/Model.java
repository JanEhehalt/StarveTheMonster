package com.trs.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Model {

    final int WALL_LIFETIME = 75;

    private Monster monster;
    private ArrayList<Wall> walls;
    private ArrayList<Projectile> projectiles;
    private Polygon tempPolygon;
    private Vector2 tempStart;
    private boolean drawing = false;
    private int currentLength;

    private boolean toReset;
    private boolean ending;

    private int difficulty;

    private int leftWallLength = 5000;

    public Model(int difficulty){
        this.difficulty = difficulty;

        monster = new Monster(250,150);
        walls = new ArrayList<>();
        projectiles = new ArrayList<>();
    }

    public void timerStep() {
        monster.move(walls, projectiles);

        for(int i = projectiles.size() - 1; i >= 0; i--){
            Projectile projectile = projectiles.get(i);
            projectile.move(walls);
            if(projectile.getxPos() < -110 || projectile.getxPos() > 1710 || projectile.getyPos() < -110 || projectile.getyPos() > 1010){
                projectiles.remove(i);
            }
        }
        for (int i = 0; i < walls.size(); i++) {
            walls.get(i).timerStep();
            if (walls.get(i).getLifetime() == 0) {
                walls.remove(i);
                i--;
            }
        }
        if (monster.getIsDead()) {
            toReset = true;
            ending = false; // Monster win - Player lost
        }
        if(monster.getHp() == monster.getMaxHp()){
            toReset = true;
            ending = true;
        }

        // Generation of new projectiles
        int value = (int) (Math.random() * 5) + difficulty;
        if(value > 0){
            projectiles.add(spawnProjectile());
        }
    }
    
    public void startWall(int x, int y){
        if(!drawing){
            tempPolygon = StaticMath.createPolygon(x,y,0,10,5);
            tempStart = new Vector2(x,y);
            drawing = true;
        }
    }
    public void adjustWall(int x, int y){
        if(drawing){
            double angle = StaticMath.calculateAngle(x,y,(int)tempStart.x,(int)tempStart.y);
            tempPolygon = StaticMath.createPolygon((int)tempStart.x, (int)tempStart.y, angle-Math.PI,10,Vector2.dst(tempStart.x,tempStart.y,x,y));
            currentLength = (int)Vector2.dst(tempStart.x,tempStart.y,x,y);
        }
    }
    public void finishWall(int x, int y){
        if(Vector2.dst(tempStart.x,tempStart.y,x,y) > 50) {
            double angle = StaticMath.calculateAngle(x,y,(int)tempStart.x,(int)tempStart.y);
            tempPolygon = StaticMath.createPolygon((int)tempStart.x, (int)tempStart.y, angle-Math.PI,10,Vector2.dst(tempStart.x,tempStart.y,x,y));

            boolean possible = true;
            if(Vector2.dst(tempStart.x,tempStart.y,x,y) > leftWallLength)
                possible = false;
            if(possible)
            for(Wall wall : walls){
                if(Intersector.overlapConvexPolygons(tempPolygon, wall.getPolygon())){
                    possible = false;
                    break;
                }
            }
            /*
            for(Projectile projectile : projectiles){
                if(Intersector.overlapConvexPolygons(tempPolygon,projectile.getPolygon())){
                    possible = false;
                    break;
                }
            }
            */

            if(possible){
                leftWallLength -= Vector2.dst(tempStart.x,tempStart.y,x,y);
                TempWall newWall = new TempWall(angle-Math.PI, tempPolygon, WALL_LIFETIME);
                for(int i = 0; i < projectiles.size(); i++){
                    if(Intersector.overlapConvexPolygons(projectiles.get(i).getPolygon(),newWall.getPolygon())){
                        projectiles.remove(i);
                        i--;
                    }
                }
                walls.add(newWall);
            }
        }
        tempPolygon = null;
        tempStart = null;
        drawing = false;
        currentLength = 0;
    }

    private Projectile spawnProjectile(){
        int xPos;
        int yPos;

        int direction = (int) (Math.random() * 4);

        // up
        if(direction == 0){
            xPos = (int) (Math.random() * 1600);
            yPos = 1000;
        }
        // right
        else if(direction == 1){
            xPos = 1700;
            yPos = (int) (Math.random() * 900);
        }
        // down
        else if(direction == 2){
            xPos = (int) (Math.random() * 1600);
            yPos = -100;
        }
        // left
        else{
            xPos = -100;
            yPos = (int) (Math.random() * 900);
        }

        return new Projectile(xPos, yPos, monster.getxPos(), monster.getyPos());
    }

    public ArrayList<Wall> getWalls(){
        return walls;
    }

    public Monster getMonster(){
        return monster;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public Polygon getTempPolygon() {
        return tempPolygon;
    }

    public int getLeftWallLength() {
        return leftWallLength;
    }

    public int getCurrentLength() {
        return currentLength;
    }

    public boolean isToReset() {
        return toReset;
    }

    public boolean getEnding() {
        return ending;
    }
}
