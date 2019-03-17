package br.edu.ufabc.gameover.core;

import br.edu.ufabc.gameover.models.GameObject;

public class Render {
	
	private GameAction gameAction;
	
	public Render(GameAction action) {
		// TODO Auto-generated constructor stub
		this.gameAction = action;
		
		
	}
	
	public void draw(float delta) {
		//Desenhando na tela
		gameAction.sprite.begin();
		gameAction.bg.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		for (GameObject o: gameAction.objects) {
			o.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		gameAction.sprite.end();
		
		
	}

}
