package com.trs.game.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class Button {
    int id;
    int xPos;
    int yPos;
    int width;
    int height;
    String text;
    Color buttonColor;
    Color textColor;


    public Button(int id, int xPos, int yPos, int width, int height, String text, Color buttonColor, Color textColor){
        this.id = id;
        this.xPos = xPos;
        this.yPos = yPos;
        this.width = width;
        this.height = height;
        this.text = text;
        this.buttonColor = buttonColor;
        this.textColor = textColor;
    }

    public void render(SpriteBatch batch, ShapeRenderer renderer, BitmapFont font){
        if(batch.isDrawing())batch.end();
        if(renderer.isDrawing()) renderer.end();
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(buttonColor);
        renderer.rect(xPos, yPos, width, height);
        renderer.end();
        batch.begin();
        font.setColor(textColor);
        font.draw(batch, text, ((xPos + width/2) - (getTextWidth(font,text)/2)), ((yPos + height/2) + (getTextHeight(font,text)/2)));
        batch.end();
    }

    public void dispose(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Rectangle getRect(){
        return new Rectangle(xPos,yPos,width,height);
    }

    public void setButtonColor(Color buttonColor) {
        this.buttonColor = buttonColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
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

}