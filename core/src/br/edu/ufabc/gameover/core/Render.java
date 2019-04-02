package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.PassiveEnemy;
import br.edu.ufabc.gameover.physics.attack.ProjetilAttack;

public class Render {
	
	private GameAction gameAction;
	private Matrix4 viewMatrix; //para resolucao
	
	private BitmapFont bitmapFont; //para interface
	
	public Render(GameAction action) {
		// TODO Auto-generated constructor stub
		this.gameAction = action;
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		viewMatrix = new Matrix4();
	}
	
	public void draw(float delta) {
		
		viewMatrix.setToOrtho2D(0, 0, 800, 600); // definindo resolução da tela
		gameAction.sprite.setProjectionMatrix(viewMatrix);
		
		//Desenhando na tela
		gameAction.sprite.begin();
		//primeiramente movimentando o plano de fundo
		gameAction.bg.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		
		//Os objetos (nuvens, luzes, etc) são renderizados na sprite em seguida
		for (GameObject o: gameAction.objects) {
			o.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		//Seguido de todos os inimigos
		for (PassiveEnemy e: gameAction.enemies) {
			e.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		//Heroi é renderizado
		gameAction.hero.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		
		//Todos os projeteis são renderizados
		for(ProjetilAttack zone: gameAction.projetilZones) {
			zone.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		
		//Atualizando interface
		bitmapFont.draw(gameAction.sprite, "Pontuacao: "+gameAction.record, 350, 460);
		bitmapFont.draw(gameAction.sprite, "Stamina: "+gameAction.hero.getStamina(), 0, 460);
		
		gameAction.sprite.end();
		
		
	}

}
