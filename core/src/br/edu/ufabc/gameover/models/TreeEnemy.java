package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class TreeEnemy extends PassiveEnemy{

	private int x = 200;
	private int y = 90;
	private int frameState = 1;
	private boolean walking = false; //Define se já esta andando para um destino.
	private int walkingTarget = 0; //Passos que faltam para chegar ao destino. Anda para direita se positivo, esquerda se negativo.
	
	public TreeEnemy() {
		super(new Texture("tree/sprite1.png"), 1);
		
		this.setPosition(this.x, this.y);
		
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void walkingWay() {
		//Inicialmente o walkingWay não está considerando barreiras.
		
		//Se estiver andando, continua até acabar os passo (walkingTarget) ou encontrar uma barreira (ainda não implementado)
		if(walking) {
			if(walkingTarget > 0) { x++; walkingTarget--;}
			else { x--; walkingTarget++;}
			
			//caso acabe os passos mudar estado
			if(walkingTarget == 0) walking = false;
			
		}else {
			this.walking = true;								//inicia estado de andar
			if(Math.random() < 0.1) {								//Aqui sorteamos o tempo que ele ficará parado 
				if(Math.random() > 0.5) this.walkingTarget = 50; 	//define que vai andar para direita
				else this.walkingTarget = -50;						//define que vai andar para a esquerda
			}
		}
		
		
		this.setPosition(this.x, this.y);
		
		//Esquema para trocar o sprite. 
		//TODO diminuir a velocidade de troca de sprites.
		this.frameState++;
		switch (this.frameState) {
		case 1:
			this.setTexture(new Texture("tree/sprite1.png"));
			break;
		case 2:
			this.setTexture(new Texture("tree/sprite2.png"));
			break;
		case 3:
			this.setTexture(new Texture("tree/sprite3.png"));
			break;
		case 4:
			this.setTexture(new Texture("tree/sprite4.png"));
			break;
		case 5:
			this.frameState = 1;
			break;
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
	
	
	

}
