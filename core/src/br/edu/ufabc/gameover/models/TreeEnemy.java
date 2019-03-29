package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class TreeEnemy extends PassiveEnemy{

	private int frameState = 1;	//Variavel usada para exibir animacao de textura se o estado tiver. Exemplo: animação ao andar.
	private int walkingTarget = 0; //Passos que faltam para chegar ao destino. Anda para direita se positivo, esquerda se negativo.
	
	public TreeEnemy(int xPosInitial, int yPosInitial) {
		super(new Texture("tree/sprite1L.png"), 1, "Tronco Maldito", xPosInitial, yPosInitial, 80, 80);
	}
	
	

	@Override
	public void walkingWay() {
		//Inicialmente o walkingWay não está considerando barreiras.
		
		//Se estiver andando, continua até acabar os passo (walkingTarget) ou encontrar uma barreira (ainda não implementado)
		if(this.status == "walking") {
			if(walkingTarget > 0) { this.moveHorizontal(1); walkingTarget--;}
			else { this.moveHorizontal(-1); walkingTarget++;}
			
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

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		this.statusChange("attacking");
	}

	@Override
	public void pursue(int targetX, int targetY) {
		this.statusChange("pursuing");
		if( (targetX-this.getXpos()) > 100 ) {
			
			this.moveHorizontal(-1);
		}else if( (targetX-this.getXpos()) < - 100 ) {
			this.moveHorizontal(1);
		} else {
			this.setClosedToHero(true);
		}
		
	}



	/**
	 * Esse método retorna a devida textura de acordo com as variaveis de estado.
	 */
	@Override
	public Texture getTextureByState() {
		
		Texture tx = new Texture("tree/sprite1L.png"); //inicialização padrão
		
		this.frameState++; //contador que auxilia a animação
		
		if(status == "takingHit") {
			if(rightOrientation) 	tx = new Texture("tree/spriteHitR.png");
			else					tx = new Texture("tree/spriteHitL.png");
		}else if(status == "walking" || status == "pursuing") {
			if(rightOrientation) {
				if(frameState < 25) tx = new Texture("tree/sprite1R.png");
				else if(frameState < 50) tx = new Texture("tree/sprite2R.png");
				else if(frameState < 75) tx = new Texture("tree/sprite3R.png");
				else if(frameState < 100) tx = new Texture("tree/sprite4R.png");
				else this.frameState = 1;
			}else {
				if(frameState < 25) tx = new Texture("tree/sprite1L.png");
				else if(frameState < 50) tx = new Texture("tree/sprite2L.png");
				else if(frameState < 75) tx = new Texture("tree/sprite3L.png");
				else if(frameState < 100) tx = new Texture("tree/sprite4L.png");
				else this.frameState = 1;
			}
		}else {
			if(rightOrientation) {
				tx = new Texture("tree/sprite1R.png");
			}else {
				tx = new Texture("tree/sprite1L.png");
			}
		}
		
		return tx;
	}
	
	
	

}
