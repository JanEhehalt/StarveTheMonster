package com.trs.game.model;


import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Model {
    private Monster monster;
    private ArrayList<Wall> walls;

    public Model(){
        monster = new Monster(250,150);
        walls = new ArrayList<>();
        walls.add(new PermWall(20,new Rectangle(250,250,50,25)));
    }

    public void timerStep(){

    }

    public ArrayList<Wall> getWalls(){
        return walls;
    }

    public Monster getMonster(){
        return monster;
    }
}
