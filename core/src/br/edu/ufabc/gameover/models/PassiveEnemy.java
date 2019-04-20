package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class PassiveEnemy extends Enemy{


	PassiveEnemy(Texture texture, int hitPoint, String name, int xPosInitial, int yPosInitial, int width, int height, int velWalk, int velPursue) {
		super(texture, hitPoint, name, xPosInitial, yPosInitial, width, height, velWalk, velPursue, 100);
	}
	

	@Override
	public void behavior() {
		// Apenas ataca o heroi, quando atacado
		
		this.statusTime--;
		if(this.statusTime <= 0) this.statusChange("awaiting");
		
		if(this.status != "takingHit" && this.status != "attacking") { //inimigos sofrem delay ao serem atacados
			
			if(!this.detectedHero) {
				//Enquanto não provocado, irá apenas andar sem rumo
				this.walkingWay();
			}else {
				//Quando provocado 
				if(!this.closedToHero) {
					//Se longe do Heroi irá buscar um caminho até ele
					this.statusChange("pursuing");
					this.pursue(targetX,targetY);
				}else {
					//Se perto o suficiente do heroi tentará ataque
					this.statusChange("attacking");
					this.attack();
					this.closedToHero = false; //após ataque vai obrigar inimigo a procurar heroi novamente.
				}
			}
		}
		
	}
	
	
	
	
	
	/**
	 * Método de perseguicao ao heroi. Disparado pelo behavior
	 * @param targetX
	 * @param targetY
	 */
	public void pursue(int targetX, int targetY) {
		if( (targetX-this.getXpos()) > 50 ) {
			
			this.moveHorizontal(-velPursue);
		}else if( (targetX-this.getXpos()) < - 50 ) {
			this.moveHorizontal(velPursue);
		} else {
			this.setClosedToHero(true);
		}
		
	}
	
	public void walkingWay() {
		//Inicialmente o walkingWay não está considerando barreiras.
		
		//Se estiver andando, continua até acabar os passo (walkingTarget) ou encontrar uma barreira (ainda não implementado)
		if(this.status == "walking") {
			if(walkingTarget > 0) { this.moveHorizontal(velWalkingAway); walkingTarget--;}
			else { this.moveHorizontal(-velWalkingAway); walkingTarget++;}
			
			//caso acabe os passos mudar estado
			if(walkingTarget == 0) this.statusChange("awaiting");
			
		}else {
			
			if(Math.random() < 0.1) {								//Aqui sorteamos o tempo que ele ficará parado 
				this.statusChange("walking");						//inicia estado de andar
				if(Math.random() > 0.5) this.walkingTarget = 50; 	//define que vai andar para direita
				else this.walkingTarget = -50;						//define que vai andar para a esquerda
			}
		}	
	}
	
	
	
	
	
	
	

}
