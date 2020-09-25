package com.trs.game.model;


import java.util.ArrayList;

public class Model {
    private Monster monster;
    private ArrayList<Wall> walls;

    public Model(){
        monster = new Monster();
        walls = new ArrayList<>();
    }

    public void timerStep(){

    }
}
