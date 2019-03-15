package br.edu.ufabc.gameover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.gameover.frames.MapScreen;

public class GameOverMain extends Game {
	
	//screens
	private MapScreen firstWorld;
	
	//para o render
	private SpriteBatch batch;
	private Texture img;
	
	
	
	@Override
	public void create () {

		//iniciando Screen
		firstWorld = new MapScreen("START");
		setScreen(firstWorld);
		
	}

	@Override
	public void render () {
		//renderizando mapa
		firstWorld.render(Gdx.graphics.getDeltaTime());
//		if(firstWorld.isDone()) {
//			
//		}
		
		
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
