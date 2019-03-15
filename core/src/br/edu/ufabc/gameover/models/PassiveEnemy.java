package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public abstract class PassiveEnemy extends GameObject{

	private boolean detectedHero = false; //Diz se o inimigo está em perseguicao ao heroi 	
	private boolean closedToHero = false; //Diz se está perto o suficiente do inimigo
	private int hitPoint; //Valor do dano do inimigo
	
	
	PassiveEnemy(Texture texture, int width, int height, int hitPoint) {
		super(texture, width, height);
		this.hitPoint = hitPoint;
		// TODO Auto-generated constructor stub
	}
	
	public void update() {
		this.behavior();
	}

	@Override
	public void behavior() {
		// Apenas ataca o heroi, quando atacado
		
		//TODO criar um modo de detectar o Hero
		
		if(!this.detectedHero) {
			//Enquanto não provocado, irá apenas andar sem rumo
			this.walkingWay();
		}else {
			//Quando provocado 
			if(!this.closedToHero) {
				//Se longe do Heroi irá buscar um caminho até ele
				this.pursue();
			}else {
				//Se perto o suficiente do heroi tentará ataque
				this.attack();
			}
			
		}
		
		
	}
	
	abstract public void walkingWay(); 	//Andar sem rumo
	abstract public void attack();		//Atacar o heroi
	abstract public void pursue();		//Perseguir o heroi 
	
	

}
