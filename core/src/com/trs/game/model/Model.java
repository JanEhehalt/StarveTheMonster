package com.trs.game.model;


import com.badlogic.gdx.math.Rectangle;
import com.trs.game.StaticMath;

import java.util.ArrayList;

public class Model {
    private Monster monster;
    private ArrayList<Wall> walls;
    private ArrayList<Projectile> projectiles;

    public Model(){
        monster = new Monster(250,150);
        walls = new ArrayList<>();
        walls.add(new PermWall(20, StaticMath.createPolygon(250,250,30,25, 100)));

        projectiles = new ArrayList<>();
        projectiles.add(new Projectile(200, 200, 1000, 600));
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
