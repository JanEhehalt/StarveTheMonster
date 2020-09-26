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

public class EndScreen extends Screen {

    public EndScreen(int GAME_WORLD_WIDTH, int GAME_WORLD_HEIGHT){
        super(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, 2);
        buttons.add(new Button(-1,0,0,200,80,"EndScreen", Color.BLACK,Color.WHITE));

        buttons.add(new Button(0,0,800,200,100, "MainMenu", Color.BLACK,Color.WHITE));
        buttons.add(new Button(1,200,800,200,100, "GameScreen", Color.DARK_GRAY,Color.WHITE));
        buttons.add(new Button(2,400,800,200,100, "EndScreen", Color.BLACK,Color.WHITE));
    }

    @Override
    public void timer() {

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
                System.out.println(button.getId());
                return button.getId();
            }
        }
        return -1;
    }

    @Override
    public void dispose() {

    }
}
