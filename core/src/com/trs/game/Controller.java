package com.trs.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Timer;
import com.trs.game.model.Model;
import com.trs.game.model.Projectile;
import com.trs.game.model.Wall;
import com.trs.game.view.View;
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
	PolygonSpriteBatch polygonSpriteBatch;
	Timer wallTimer;
	Model model;
	View view;
	BitmapFont font;
	Texture textureSolid;
	Pixmap pix;

	Screen screen;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		renderer = new ShapeRenderer();
		polygonSpriteBatch = new PolygonSpriteBatch();
		model = new Model();
		view = new View();

		wallTimer = new Timer();
		wallTimer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				screen.timer();
				if(screen.getId() == 1) {
					model.timerStep();
				}
			}
		}, 0, 0.05f);
		font = new BitmapFont();

		// BITMAP FONT
		font = new BitmapFont();
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 21;
		font = generator.generateFont(parameter);
		generator.dispose();
		font.setColor(Color.BLACK);
		//

		//POLYGON STUFF
		pix = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
		pix.setColor(Color.BLACK); // DE is red, AD is green and BE is blue.
		pix.fill();
		textureSolid = new Texture(pix);

		screen = new MainMenuScreen(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// WORKAROUND, BECAUSE MOUSEMOVED NOT WORKING WHILE TOUCHDOWN
		if(screen.getId() == 1) model.adjustWall(Gdx.input.getX(), GAME_WORLD_HEIGHT - Gdx.input.getY());

		screen.render(batch,renderer,font);

		// MODEL DRAWING
		if(screen.getId() == 1){
			// DRAW WALLS
			for(Wall wall : model.getWalls()){
				PolygonSprite poly;
				PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
						wall.getPolygon().getVertices(), new short[] {
						0, 1, 2,         // Two triangles using vertex indices.
						0, 2, 3          // Take care of the counter-clockwise direction.
				});
				poly = new PolygonSprite(polyReg);
				polygonSpriteBatch.begin();
				poly.draw(polygonSpriteBatch);
				polygonSpriteBatch.end();
			}

			// DRAW PROJECTILES
			renderer.begin(ShapeRenderer.ShapeType.Filled);
			for(Projectile projectile : model.getProjectiles()){
				PolygonSprite poly;
				PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
						projectile.getPolygon().getVertices(), new short[] {
						0, 1, 2,         // Two triangles using vertex indices.
						0, 2, 3          // Take care of the counter-clockwise direction.
				});
				poly = new PolygonSprite(polyReg);
				polygonSpriteBatch.begin();
				poly.draw(polygonSpriteBatch);
				polygonSpriteBatch.end();
			}
			// TEMP POLYGON
			if(model.getTempPolygon() != null){
				PolygonSprite poly2;
				PolygonRegion polyReg = new PolygonRegion(new TextureRegion(textureSolid),
						model.getTempPolygon().getVertices(), new short[]{
						0, 1, 2,         // Two triangles using vertex indices.
						0, 2, 3          // Take care of the counter-clockwise direction.
				});
				poly2 = new PolygonSprite(polyReg);
				polygonSpriteBatch.begin();
				poly2.draw(polygonSpriteBatch);
				polygonSpriteBatch.end();

				renderer.end();
				batch.begin();
				font.setColor(Color.BLACK);
				if(model.getCurrentLength()!=0)font.draw(batch, ""+model.getCurrentLength(), Gdx.input.getX()+10, GAME_WORLD_HEIGHT - Gdx.input.getY()+10);
				batch.end();

				renderer.begin(ShapeRenderer.ShapeType.Filled);
			}
			//
			renderer.end();

			// write left Length
			batch.begin();
			font.setColor(Color.BLACK);
			font.draw(batch, ""+model.getLeftWallLength(), 1500f,800f);
			batch.end();
			// DRAW MONSTER
			//model.getMonster().drawMonster(renderer,polygonSpriteBatch);
		}
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		renderer.dispose();
	}

	public void timerStart(){
		wallTimer.start();
	}

	public void timerStop(){
		wallTimer.stop();
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
		ManageButtonEvent(screen.touchDown(screenX,GAME_WORLD_HEIGHT-screenY));
		if(screen.getId() == 1) model.startWall(screenX, GAME_WORLD_HEIGHT - screenY);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(screen.getId() == 1) model.finishWall(screenX, GAME_WORLD_HEIGHT - screenY);
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		//if(screen.getId() == 1) model.adjustWall(screenX, GAME_WORLD_HEIGHT - screenY);
		//return true;
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	public void ManageButtonEvent(int keycode){
		switch(keycode){
			case 0: //GOTO MAINMENU
				screen = new MainMenuScreen(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
				break;
			case 1: //GOTO GAMESCREEN
				model = new Model();
				screen = new GameScreen(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
				break;
			case 2: //GOTO ENDSCREEN
				screen = new EndScreen(GAME_WORLD_WIDTH,GAME_WORLD_HEIGHT);
				break;
		}
	}
}
