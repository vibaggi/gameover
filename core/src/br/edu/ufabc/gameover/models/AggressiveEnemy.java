package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

abstract public class AggressiveEnemy extends Enemy{

	AggressiveEnemy(Texture texture, int hitPoint, String name, int xPosInitial, int yPosInitial, int width, int height,
			int velWalk, int velPursue) {
		super(texture, hitPoint, name, xPosInitial, yPosInitial, width, height, velWalk, velPursue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void behavior() {
		this.statusTime--;
		if(this.statusTime <= 0) this.statusChange("awaiting");
		
		if(this.status != "takingHit" && this.status != "attacking") { //inimigos sofrem delay ao serem atacados
			
			
			
			if(!this.detectedHero) {
				
				//Enquanto não provocado, irá apenas andar sem rumo
				this.walkingWay();
				
				//O inimigo agressivo vai sempre procurar no campo de visão o heroi.
				//Caso detecte, irá persegui-lo
				this.searchHero();
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
	
	
	private void searchHero() {
		int deltaXToHero = this.getXpos() -this.targetX;
		if( (this.getYpos() <= this.targetY && this.getY2pos() >= this.targetY) 
				&& ( deltaXToHero < 400 || deltaXToHero > -400 )) {
			this.detectedHero = true;
		}
		
	}

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
