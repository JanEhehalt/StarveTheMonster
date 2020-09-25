package com.trs.game.model;

import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Model {
    private Monster monster;
    private ArrayList<Wall> walls;
    private ArrayList<Projectile> projectiles;

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

    public ArrayList<Wall> getWalls(){
        return walls;
    }

    public Monster getMonster(){
        return monster;
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }
}
