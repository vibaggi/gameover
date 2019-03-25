package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.PassiveEnemy;

public class Render {
	
	private GameAction gameAction;
	
	private BitmapFont bitmapFont; //para interface
	
	public Render(GameAction action) {
		// TODO Auto-generated constructor stub
		this.gameAction = action;
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		
	}
	
	public void draw(float delta) {
		//Desenhando na tela
		gameAction.sprite.begin();
		gameAction.bg.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		for (GameObject o: gameAction.objects) {
			o.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		for (PassiveEnemy e: gameAction.enemies) {
			e.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		gameAction.hero.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		
		//Atualizando interface
		bitmapFont.draw(gameAction.sprite, "Pontuacao: "+gameAction.record, 350, 460);
		
		gameAction.sprite.end();
		
		
	}

}
