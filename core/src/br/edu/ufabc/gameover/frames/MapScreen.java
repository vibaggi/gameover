package br.edu.ufabc.gameover.frames;

import br.edu.ufabc.gameover.core.GameAction;
import br.edu.ufabc.gameover.core.Render;

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
