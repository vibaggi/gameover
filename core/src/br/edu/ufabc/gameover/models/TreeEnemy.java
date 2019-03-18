package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class TreeEnemy extends PassiveEnemy{

	private int frameState = 1;	//Variavel usada para exibir animacao de textura se o estado tiver. Exemplo: animação ao andar.
	private int walkingTarget = 0; //Passos que faltam para chegar ao destino. Anda para direita se positivo, esquerda se negativo.
	
	public TreeEnemy() {
		super(new Texture("tree/sprite1.png"), 1, "Tronco Maldito");
	}
	
	

	@Override
	public void walkingWay() {
		//Inicialmente o walkingWay não está considerando barreiras.
		
		//Se estiver andando, continua até acabar os passo (walkingTarget) ou encontrar uma barreira (ainda não implementado)
		if(this.status == "walking") {
			if(walkingTarget > 0) { this.moveHorizontal(1); walkingTarget--;}
			else { this.moveHorizontal(-1); walkingTarget++;}
			
			//caso acabe os passos mudar estado
			if(walkingTarget == 0) this.status = "awaiting";
			
		}else {
			this.status = "walking";								//inicia estado de andar
			if(Math.random() < 0.1) {								//Aqui sorteamos o tempo que ele ficará parado 
				if(Math.random() > 0.5) this.walkingTarget = 50; 	//define que vai andar para direita
				else this.walkingTarget = -50;						//define que vai andar para a esquerda
			}
		}
		
		
		
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pursue() {
		// TODO Auto-generated method stub
		
	}



	/**
	 * Esse método retorna a devida textura de acordo com as variaveis de estado.
	 */
	@Override
	public Texture getTextureByState() {
		
		Texture tx = new Texture("tree/sprite1.png"); //inicialização padrão
		
		this.frameState++; //contador que auxilia a animação
		
		if(status == "walking") {
			if(rightOrientation) {
				if(frameState < 25) tx = new Texture("tree/sprite1.png");
				else if(frameState < 50) tx = new Texture("tree/sprite2.png");
				else if(frameState < 75) tx = new Texture("tree/sprite3.png");
				else if(frameState < 100) tx = new Texture("tree/sprite4.png");
				else this.frameState = 1;
			}else {
				
			}
		}else {
			if(rightOrientation) {
				tx = new Texture("tree/sprite1.png");
			}else {
				tx = new Texture("tree/sprite1.png");
			}
		}
		
		return tx;
	}
	
	
	

}
