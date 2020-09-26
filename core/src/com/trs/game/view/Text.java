package com.trs.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {

    int xPos;
    int yPos;
    String text;
    Color color;
    double scale;
    int y1;
    int y2;
    boolean movement = false;
    boolean moving = true;

    public Text(int xPos, int yPos, String text, Color color, double scale, int y1, int y2){
        this.xPos = xPos;
        this.yPos = yPos;
        this.text = text;
        this.color = color;
        this.scale = scale;
        this.y1 = y1;
        this.y2 = y2;
        if(y1 == 0 && y2 == 0)moving = false;
    }

    public void render(SpriteBatch batch, BitmapFont font){
        if(batch.isDrawing()) batch.end();
        batch.begin();
        font.setColor(color);
        font.getData().setScale((float)scale);
        font.draw(batch, text, ((xPos) - (getTextWidth(font,text)/2)), ((yPos) + (getTextHeight(font,text)/2)));
        font.getData().setScale(1);
        batch.end();
    }

    public void timer(){
        if(moving){
            if(movement){
                setyPos(getyPos()+3);
                if(getyPos() >= y2){
                    movement = false;
                }
            }
            else if(!movement){
                setyPos(getyPos()-3);
                if(getyPos() <= y1){
                    movement = true;
                }
            }
        }
    }

    public float getTextWidth(BitmapFont font, String text){
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,text);
        return glyphLayout.width;
    }
    public float getTextHeight(BitmapFont font, String text){
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,text);
        return glyphLayout.height;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
