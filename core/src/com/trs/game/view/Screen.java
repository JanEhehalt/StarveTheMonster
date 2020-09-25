package com.trs.game.view;

import java.util.ArrayList;

public abstract class Screen {

    public final int SCREEN_WIDTH;
    public final int SCREEN_HEIGHT;

    int id;
    ArrayList<Button> buttons;

    public Screen(int SCREEN_WIDTH, int SCREEN_HEIGHT){
        buttons = new ArrayList();
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.SCREEN_HEIGHT =SCREEN_HEIGHT;
    }

    abstract public void timer();
    abstract public void render();
    abstract public int touchDown(int x, int y);
    abstract public void dispose();

    public int getId(){
        return id;
    }

    public int getWidth() {
        return SCREEN_WIDTH;
    }

    public int getHeight() {
        return SCREEN_HEIGHT;
    }
}
