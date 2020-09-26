package com.trs.game.model;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Model {
    private Monster monster;
    private ArrayList<Wall> walls;
    private ArrayList<Projectile> projectiles;
    private Polygon tempPolygon;
    private Vector2 tempStart;
    private boolean drawing = false;

    private int leftWallLength = 2500;

    public Model(){
        monster = new Monster(250,150);
        walls = new ArrayList<>();
        walls.add(new PermWall(60, StaticMath.createPolygon(250,250, 60,25, 100)));

        projectiles = new ArrayList<>();
        projectiles.add(new Projectile(315, 500, 315, 0));
    }

    public void timerStep(){
        monster.move(walls);

        for(Projectile projectile : projectiles){
            projectile.move(walls);
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
            tempPolygon = StaticMath.createPolygon((int)tempStart.x, (int)tempStart.y, Math.toDegrees(angle-Math.PI),10,Vector2.dst(tempStart.x,tempStart.y,x,y));
        }
    }
    public void finishWall(int x, int y){
        if(Vector2.dst(tempStart.x,tempStart.y,x,y) > 150) {
            double angle = StaticMath.calculateAngle(x,y,(int)tempStart.x,(int)tempStart.y);
            tempPolygon = StaticMath.createPolygon((int)tempStart.x, (int)tempStart.y, Math.toDegrees(angle-Math.PI),10,Vector2.dst(tempStart.x,tempStart.y,x,y));

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
                walls.add(new TempWall(Math.toDegrees(angle-Math.PI), tempPolygon, 500));
            }
        }
        tempPolygon = null;
        tempStart = null;
        drawing = false;
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
}
