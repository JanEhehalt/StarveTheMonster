package com.trs.game.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public abstract class Screen {

    public final int SCREEN_WIDTH;
    public final int SCREEN_HEIGHT;

    private int id;
    public ArrayList<Button> buttons;
    public ArrayList<Text> texts;

    public Screen(int SCREEN_WIDTH, int SCREEN_HEIGHT, int id){
        buttons = new ArrayList();
        texts = new ArrayList();
        this.SCREEN_WIDTH = SCREEN_WIDTH;
        this.SCREEN_HEIGHT =SCREEN_HEIGHT;
        this.id = id;
    }

    abstract public void timer();
    abstract public void render(SpriteBatch batch, ShapeRenderer renderer, BitmapFont font);
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
