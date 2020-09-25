package com.trs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Controller extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer renderer;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.WHITE);
		renderer.rect(50,50,0,0,50,50,1,1,45);
		renderer.setColor(Color.BLACK);
		renderer.circle(50,50,5);
		renderer.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose();
	}
}
