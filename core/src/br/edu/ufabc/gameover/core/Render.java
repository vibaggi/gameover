package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.gameover.models.AggressiveEnemy;
import br.edu.ufabc.gameover.models.Enemy;
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
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/interface.fnt"));
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
		for (Enemy e: gameAction.enemies) {
			e.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		
		gameAction.boss.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		//Heroi é renderizado
		gameAction.hero.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		
		
		//Todos os projeteis são renderizados
		for(ProjetilAttack zone: gameAction.projetilZones) {
			zone.draw(gameAction.sprite, this.gameAction.getXGeneralCoordenate());
		}
		
		this.refreshInterface();
		
		gameAction.sprite.end();
		
		
	}
	
	/**
	 * Atualiza a interface, incluindo HP, stamina e icones
	 * @param sprite
	 */
	private void refreshInterface() {
		//Atualizando interface
		
		//Mostrando corações
		for(int i = 0; i < gameAction.totalLife; i ++) {
			gameAction.sprite.draw(new Texture("icons/heart.png"), i*70 +50, 550);
		}
		
		
		bitmapFont.draw(gameAction.sprite, "Pontuacao: "+gameAction.record, 400, 540); //exibir pontuacao
		
		bitmapFont.draw(gameAction.sprite, "Stamina: "+gameAction.hero.getStamina(), 50, 540); //exibir HP
		bitmapFont.draw(gameAction.sprite, "HP: "+gameAction.hero.getHP(), 50, 500); //exibir Stamina
		
		
		gameAction.sprite.draw(new Texture("icons/potionS.png"), 610, 10);
		bitmapFont.draw(gameAction.sprite, String.valueOf(gameAction.totalPotionS), 610, 25);
		
		gameAction.sprite.draw(new Texture("icons/potionHP.png"), 540, 10);
		bitmapFont.draw(gameAction.sprite, String.valueOf(gameAction.totalPotionHP), 540, 25);
		gameAction.sprite.draw(new Texture("icons/magic.png"), 470, 10);
		gameAction.sprite.draw(new Texture("icons/attack.png"), 400, 10);
		
		
	}

}
