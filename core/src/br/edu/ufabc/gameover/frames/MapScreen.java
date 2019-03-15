package br.edu.ufabc.gameover.frames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.gameover.core.GameAction;
import br.edu.ufabc.gameover.core.Render;
import br.edu.ufabc.gameover.models.CloudObject;
import br.edu.ufabc.gameover.models.TreeEnemy;

public class MapScreen extends MyScreen{

	private GameAction gameAction;
	private Render render;

	
	public MapScreen(String idScreen) {
		super(idScreen);
		
		
		gameAction= new GameAction();
		render = new Render(gameAction);
		
	}	

	@Override
	public void update(float delta) {
		//Faz o update de todas as informações no mapa
		gameAction.update(delta);
		
		
	}

	@Override
	public void draw(float delta) {
		//desenha todos os sprites no mapa
		render.draw(delta);

		
	}
	

}
