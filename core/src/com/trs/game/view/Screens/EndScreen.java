package com.trs.game.view.Screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trs.game.view.Button;
import com.trs.game.view.Screen;
import com.trs.game.view.Text;

public class EndScreen extends Screen {

    public EndScreen(int GAME_WORLD_WIDTH, int GAME_WORLD_HEIGHT){
        super(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, 2);
        buttons.add(new Button(0,0,0,200,200,"EndScreen", Color.BLACK,Color.WHITE));
        texts.add(new Text(500,500,"EHREEHREEHRE",Color.RED));
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
        return 0;
    }

    @Override
    public void dispose() {

    }
}
