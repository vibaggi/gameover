package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.core.GameAction;
import br.edu.ufabc.gameover.physics.attack.PhysicAttack;

public class ZombieEnemy extends AggressiveEnemy{

	private int frameState = 1;	//Variavel usada para exibir animacao de textura se o estado tiver. Exemplo: animação ao andar.
	

	//Sounds
	public Music getHitSound; //som ao receber um ataque
	public Music walkingSound; //som ao andar
	private GameAction game;
	
	
	public ZombieEnemy(int xPosInitial, int yPosInitial, GameAction game) {
		super(new Texture("zombie/sprite1L.png"), 1, "Zumbi", xPosInitial, yPosInitial, 80, 80, 1, 1);
		getHitSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/zombie/getHit.mp3"));
		walkingSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/zombie/walking.mp3"));
		this.game = game; //Usado para inserir uma zona de ataque no game action
	}

	@Override
	public void attack() {
		game.addPhysicAttack(new PhysicAttack(this, 5, 10, "enemy"));	
	}

	@Override
	public Texture getTextureByState() {
Texture tx = new Texture("zombie/sprite1L.png"); //inicialização padrão
		
		this.frameState++; //contador que auxilia a animação
		
		if(status == "takingHit") {
			getHitSound.play(); //Som ao receber um ataque
			if(rightOrientation) 	tx = new Texture("zombie/takeHitR.png");
			else					tx = new Texture("zombie/takeHitL.png");
		}else if(status == "walking" || status == "pursuing") {
			walkingSound.play(); //Faz som enquanto anda.
			if(rightOrientation) {
				if(frameState < 25) tx = new Texture("zombie/sprite1R.png");
				else if(frameState < 50) tx = new Texture("zombie/sprite2R.png");
				else this.frameState = 1;
			}else {
				if(frameState < 25) tx = new Texture("zombie/sprite1L.png");
				else if(frameState < 50) tx = new Texture("zombie/sprite2L.png");
				else this.frameState = 1;
			}
		}else if(status == "attacking"){
			if(rightOrientation) 	tx = new Texture("zombie/attackR.png");
			else					tx = new Texture("zombie/attackL.png");
			
		}else {
			if(rightOrientation) {
				tx = new Texture("zombie/sprite1R.png");
			}else {
				tx = new Texture("zombie/sprite1L.png");
			}
		}
		
		return tx;
	}

}
