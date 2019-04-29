package br.edu.ufabc.gameover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.gameover.frames.CreditScreen;
import br.edu.ufabc.gameover.frames.MapScreen;
import br.edu.ufabc.gameover.frames.MyScreen;

public class GameOverMain extends Game {
	
	//screens
//	private MapScreen firstWorld;
	private MyScreen inProgress;
	
	//para o render
	private SpriteBatch batch;
	private Texture img;
	
	
	
	@Override
	public void create () {

		//iniciando Screen
		inProgress = new MapScreen("START");
//		inProgress = new CreditScreen("CREDIT"); 
		setScreen(inProgress);
		
	}

	@Override
	public void render () {
		//renderizando mapa
		inProgress.render(Gdx.graphics.getDeltaTime());
		if(inProgress.isDone()) {
			inProgress = new CreditScreen("CREDIT"); 
		}
		
		
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
