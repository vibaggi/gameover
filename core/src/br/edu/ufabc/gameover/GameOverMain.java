package br.edu.ufabc.gameover;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import br.edu.ufabc.gameover.frames.CreditScreen;
import br.edu.ufabc.gameover.frames.MapScreen;
import br.edu.ufabc.gameover.frames.MyScreen;
import br.edu.ufabc.gameover.frames.StartScreen;

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
		inProgress = new StartScreen("START");
		setScreen(inProgress);
		
	}

	@Override
	public void render () {
		//renderizando mapa
		inProgress.render(Gdx.graphics.getDeltaTime());
		if(inProgress.isDone()) {
			System.out.println("IS DONE");
			if(inProgress.getIdScreen() == "START") {
				inProgress = new MapScreen("GAME");
			}else if(inProgress.getIdScreen() == "GAME") {
				inProgress = new CreditScreen("CREDITS"); 
			}else {
				inProgress = new StartScreen("START");
			}
			
		}
		
		
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
