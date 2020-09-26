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

    private int leftWallLength = 5000;

    public Model(){
        monster = new Monster(250,150);
        walls = new ArrayList<>();
        walls.add(new PermWall(60, StaticMath.createPolygon(250,250, 60,25, 100)));

        projectiles = new ArrayList<>();
        projectiles.add(new Projectile(270, 500, 270, 0));
    }

    public void timerStep() {
        monster.move(walls, projectiles);

        for (Projectile projectile : projectiles) {
            projectile.move(walls);
        }
        for (int i = 0; i < walls.size(); i++) {
            walls.get(i).timerStep();
            if (walls.get(i).getLifetime() == 0) {
                walls.remove(i);
                i--;
            }

            if (monster.getIsDead()) {
                // TODO: Tod implementieren
            }
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
        if(Vector2.dst(tempStart.x,tempStart.y,x,y) > 150) {
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
            for(Projectile projectile : projectiles){
                if(Intersector.overlapConvexPolygons(tempPolygon,projectile.getPolygon())){
                    possible = false;
                    break;
                }
            }

            if(possible){
                leftWallLength -= Vector2.dst(tempStart.x,tempStart.y,x,y);
                walls.add(new TempWall(angle-Math.PI, tempPolygon, WALL_LIFETIME));
            }
        }
        tempPolygon = null;
        tempStart = null;
        drawing = false;
        currentLength = 0;
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
}
