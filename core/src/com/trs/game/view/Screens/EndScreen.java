package com.trs.game.view.Screens;

import com.badlogic.gdx.Gdx;
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

    boolean lost;

    public EndScreen(int GAME_WORLD_WIDTH, int GAME_WORLD_HEIGHT, boolean lost, float volume){
        super(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT, 2, volume);
        this.lost = lost;

        if(lost){
            music = Gdx.audio.newMusic(Gdx.files.internal("lose.ogg"));
        }
        else{
            music = Gdx.audio.newMusic(Gdx.files.internal("victory.ogg"));
        }

        music.setVolume(volume);
        music.setLooping(true);
        music.play();

        if(lost)
            texts.add(new Text(GAME_WORLD_WIDTH/2, (int)((float)GAME_WORLD_HEIGHT * 0.85f), "The monster ate too many people!", Color.BLACK, 3, 0,0));
        else
            texts.add(new Text(GAME_WORLD_WIDTH/2, (int)((float)GAME_WORLD_HEIGHT * 0.85f), "You successfully starved the Monster!", Color.BLACK, 3, 0,0));

        texts.add(new Text(GAME_WORLD_WIDTH/2, GAME_WORLD_HEIGHT/2, "click to restart...", Color.BLACK, 2, GAME_WORLD_HEIGHT/2 - 30,GAME_WORLD_HEIGHT/2 + 30));

        buttons.add(new Button(-1,50,100,240,50,"Difficulty",Color.BLACK,Color.WHITE));
        buttons.add(new Button(3,300,100,210,50,"Easy",Color.DARK_GRAY,Color.WHITE));
        buttons.add(new Button(4,510,100,210,50,"Medium",Color.GRAY,Color.BLUE));
        buttons.add(new Button(5,300,50,210,50,"Hard",Color.GRAY,Color.WHITE));
        buttons.add(new Button(6,510,50,210,50,"Impossible",Color.DARK_GRAY,Color.WHITE));

        texts.add(new Text(50,845, "Volume:", Color.BLACK,1,0,0));

        buttons.add(new Button(7,100,830,35,35,"-",Color.DARK_GRAY,Color.WHITE));
        buttons.add(new Button(8,140,830,35,35,"+",Color.DARK_GRAY,Color.WHITE));


        texts.add(new Text(1300, 220, "Credits:", Color.BLACK, 1.5, 0,0));
        texts.add(new Text(1300, 180, "MainMenu music by pbondoer@opengameart.org", Color.BLACK, 1, 0,0));
        texts.add(new Text(1300, 150, "Ingame music by Jan125@opengameart.org", Color.BLACK, 1, 0,0));
        texts.add(new Text(1300, 120, "Victory tune by Macto@opengameart.org", Color.BLACK, 1, 0,0));
        texts.add(new Text(1300, 90, "Lose music by Jan125@opengameart.org", Color.BLACK, 1, 0,0));
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
                System.out.println(button.getId());
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
        music.dispose();
    }
}
