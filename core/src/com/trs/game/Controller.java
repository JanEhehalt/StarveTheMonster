package com.trs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trs.game.view.Button;
import com.trs.game.view.Screen;
import com.trs.game.view.Screens.EndScreen;
import com.trs.game.view.Screens.GameScreen;
import com.trs.game.view.Screens.MainMenuScreen;

public class Controller extends ApplicationAdapter implements InputProcessor {

	final int GAME_WORLD_WIDTH = 1600;
	final int GAME_WORLD_HEIGHT = 900;

	SpriteBatch batch;
	ShapeRenderer renderer;
	BitmapFont font;

	Screen screen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();

		// BITMAP FONT
		font = new BitmapFont();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 21;
		font = generator.generateFont(parameter);
		generator.dispose();
		font.setColor(Color.BLACK);
		//

		screen = new MainMenuScreen(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		screen.render(batch,renderer,font);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(screen.getId() == 0) screen = new GameScreen(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
		else if(screen.getId() == 1) screen = new EndScreen(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
		else if(screen.getId() == 2) screen = new MainMenuScreen(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
