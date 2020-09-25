package com.trs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Timer;
import com.trs.game.model.Model;
import com.trs.game.view.View;
import com.trs.game.view.Button;

public class Controller extends ApplicationAdapter {
	SpriteBatch batch;
	ShapeRenderer renderer;
	Timer wallTimer;
	Model model;
	View view;
	BitmapFont font;

	Button button;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		model = new Model();
		view = new View();

		wallTimer = new Timer();
		wallTimer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				// TODO: Implement timertask
			}
		}, 0, 1f);
		button = new Button(0, 200, 150, 200, 80, "EHRE", Color.YELLOW, Color.BLACK);
		font = new BitmapFont();

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 21;
		font = generator.generateFont(parameter);
		generator.dispose();
		font.setColor(Color.BLACK);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.end();
		renderer.begin(ShapeRenderer.ShapeType.Filled);
		renderer.setColor(Color.WHITE);
		renderer.rect(50,50,0,0,50,50,1,1,45);
		renderer.setColor(Color.BLACK);
		renderer.circle(50,50,5);
		renderer.end();

		button.render(batch,renderer,font);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose();
	}
}
