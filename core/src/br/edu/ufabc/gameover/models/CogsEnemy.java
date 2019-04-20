package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.core.GameAction;
import br.edu.ufabc.gameover.physics.attack.PhysicAttack;

public class CogsEnemy extends PassiveEnemy {

	private int frameState = 1;	//Variavel usada para exibir animacao de textura se o estado tiver. Exemplo: animação ao andar.
	//Sounds
	public Music getHitSound; //som ao receber um ataque
	public Music walkingSound; //som ao andar
	private GameAction game;
	
	public CogsEnemy(int xPosInitial, int yPosInitial, GameAction game) {
		super(new Texture("cogumelus/sprite1R.png"), 1, "Cogumelus", xPosInitial, yPosInitial, 100, 100, 1, 2);
		
		//definindo sons
//		getHitSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/cogs/getHit.mp3"));
		walkingSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/cogs/walking.mp3"));
		
		this.game = game;
	}

	@Override
	public void attack() {
		game.addPhysicAttack(new PhysicAttack(this, 7, 10, "enemy"));
		
	}

	@Override
	public Texture getTextureByState() {
		Texture tx = new Texture("cogumelus/sprite1L.png"); //inicialização padrão
		
		this.frameState++; //contador que auxilia a animação
		
		if(status == "takingHit") {
			if(rightOrientation) 	tx = new Texture("cogumelus/spriteHitR.png");
			else					tx = new Texture("cogumelus/spriteHitL.png");
		}else if(status == "walking" || status == "pursuing") {
			walkingSound.play();
			if(rightOrientation) {
				if(frameState < 50) tx = new Texture("cogumelus/sprite1R.png");
				else if(frameState < 100) tx = new Texture("cogumelus/sprite2R.png");
//				else if(frameState < 75) tx = new Texture("cogumelus/sprite3R.png");
//				else if(frameState < 100) tx = new Texture("cogumelus/sprite4R.png");
				else this.frameState = 1;
			}else {
				if(frameState < 50) tx = new Texture("cogumelus/sprite1L.png");
				else if(frameState < 100) tx = new Texture("cogumelus/sprite2L.png");
//				else if(frameState < 75) tx = new Texture("cogumelus/sprite3L.png");
//				else if(frameState < 100) tx = new Texture("cogumelus/sprite4L.png");
				else this.frameState = 1;
			}
		}else if(status == "attacking"){
			if(rightOrientation) 	tx = new Texture("cogumelus/attackR.png");
			else					tx = new Texture("cogumelus/attackL.png");
			
		}else {
			if(rightOrientation) {
				tx = new Texture("cogumelus/sprite1R.png");
			}else {
				tx = new Texture("cogumelus/sprite1L.png");
			}
		}
		
		return tx;
	}

}
