package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.core.GameAction;
import br.edu.ufabc.gameover.physics.attack.PhysicAttack;

public class TreeEnemy extends PassiveEnemy{

	private int frameState = 1;	//Variavel usada para exibir animacao de textura se o estado tiver. Exemplo: animação ao andar.
	

	//Sounds
	public Music getHitSound; //som ao receber um ataque
	public Music walkingSound; //som ao andar
	private GameAction game;
	
	public TreeEnemy(int xPosInitial, int yPosInitial, GameAction game) {
		super(new Texture("tree/sprite1L.png"), 1, "Tronco Maldito", xPosInitial, yPosInitial, 80, 80, 1, 2);
		getHitSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/tree/getHit.mp3"));
		walkingSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/tree/walking.mp3"));
		this.game = game; //Usado para inserir uma zona de ataque no game action
	}
	
	
	

	@Override
	public void attack() {
		game.addPhysicAttack(new PhysicAttack(this, 5, 10, "enemy"));
		
	}




	/**
	 * Esse método retorna a devida textura de acordo com as variaveis de estado.
	 */
	@Override
	public Texture getTextureByState() {
		
		Texture tx = new Texture("tree/sprite1L.png"); //inicialização padrão
		
		this.frameState++; //contador que auxilia a animação
		
		if(status == "takingHit") {
			getHitSound.play(); //Som ao receber um ataque
			if(rightOrientation) 	tx = new Texture("tree/spriteHitR.png");
			else					tx = new Texture("tree/spriteHitL.png");
		}else if(status == "walking" || status == "pursuing") {
			walkingSound.play(); //Faz som enquanto anda.
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
		}else if(status == "attacking"){
			if(rightOrientation) 	tx = new Texture("tree/attackR.png");
			else					tx = new Texture("tree/attackL.png");
			
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
