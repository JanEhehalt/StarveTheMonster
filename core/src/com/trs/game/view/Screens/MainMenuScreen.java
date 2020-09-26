package com.trs.game.view.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.trs.game.view.Button;
import com.trs.game.view.Screen;
import com.trs.game.view.Text;

public class MainMenuScreen extends Screen {

    public MainMenuScreen(int GAME_WORLD_WIDTH, int GAME_WORLD_HEIGHT){
        super(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, 0);

        texts.add(new Text(GAME_WORLD_WIDTH/2, (int)((float)GAME_WORLD_HEIGHT * 0.85f), "Starve the Monster", Color.BLACK, 5, 0,0));
        texts.add(new Text(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, "click to start...", Color.BLACK, 2, GAME_WORLD_HEIGHT/2 - 30,GAME_WORLD_HEIGHT/2 + 30));

        buttons.add(new Button(-1,250,100,240,50,"Difficulty",Color.BLACK,Color.WHITE));
        buttons.add(new Button(3,500,100,210,50,"Easy",Color.DARK_GRAY,Color.WHITE));
        buttons.add(new Button(4,710,100,210,50,"Medium",Color.GRAY,Color.BLUE));
        buttons.add(new Button(5,920,100,210,50,"Hard",Color.DARK_GRAY,Color.WHITE));
        buttons.add(new Button(6,1130,100,210,50,"Impossible",Color.GRAY,Color.WHITE));
    }

    @Override
    public void timer() {
        for(Text text : texts){
            text.timer();
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer renderer, BitmapFont font) {
        for(Button button : buttons){
            button.render(batch,renderer,font);
        }
        for(Text text : texts){
            text.render(batch,font);
        }
    }


    @Override
    public int touchDown(int x, int y) {
        Rectangle r = new Rectangle(x,y,1,1);
        for(Button button : buttons){
            if(Intersector.overlaps(r, button.getRect())){
                if(button.getId() == 3 || button.getId() == 4 || button.getId() == 5 || button.getId() == 6 ){
                    for(Button button2 : buttons){
                        button2.setTextColor(Color.WHITE);
                    }
                    button.setTextColor(Color.BLUE);
                }
                return button.getId();
            }
        }
        return 1;
    }

    @Override
    public void dispose() {

    }
}
