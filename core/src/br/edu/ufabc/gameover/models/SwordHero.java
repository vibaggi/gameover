package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class SwordHero extends Hero{
	
	static int hpSH = 100;
	static int staminaSH = 1000;
	static int hitPointSH = 10;
	static int defensePoint = 5;
	public Music music;
	

	public SwordHero(float worldGravity, int[][] worldMap) {
		super(new Texture("swordHero/heroAwaitingR.png"), hpSH, hitPointSH, staminaSH, defensePoint, worldGravity, 80, 80, worldMap);
		// TODO Auto-generated constructor stub
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/shot.mp3"));
	}
	
	/**
	 * Deve verificar se é possivel atacar e retornar o valor do ataque
	 * @param numberAttack os herois tem 3 niveis de ataque
	 * @return
	 */
	public int executeAttack(int numberAttack) {
		music.play();
		if(this.status == "awaiting" || this.status == "walking" || this.status == "defensing" || this.status == "jumping") {
			//Ataque interrompe qualquer um dos status acima, menos o "receive hit"
			
			//numberAttack 1 não tem gasto de stamina 
			if(numberAttack == 2) {
				//Se não for possivel gastar a stamina, não realiza o ataque
				if(!this.spendStamina(100)) return 0;
			}
			else if(numberAttack == 3) {
				//Se não for possivel gastar a stamina, não realiza o ataque
				if(this.spendStamina(300)) return 0;
			}
			this.statusChange("attacking");
			return this.getHitPoint();
		}
		return 0;
	}
	
	/**
	 * Verefica o estado e retorna a textura correta a ser exibida.
	 * @return
	 */
	public Texture getTextureByState() {
		Texture tx;
		if(this.status == "attacking") {
			if(this.rightOrientation) 	tx = new Texture("swordHero/heroAttackingR.png");
			else 						tx = new Texture("swordHero/heroAttackingL.png");
		}
		else if(this.status == "defensing") {
			if(this.rightOrientation) 	tx = new Texture("swordHero/defenseR.png");
			else 						tx = new Texture("swordHero/defenseL.png");
		}
		
		else if(this.status == "jumping") {
			if(this.rightOrientation) 	tx = new Texture("swordHero/jumpingR.png");
			else 						tx = new Texture("swordHero/jumpingL.png");
		}
		
		else if(this.status == "dying") {
			if(this.rightOrientation) 	tx = new Texture("swordHero/deathR.png");
			else 						tx = new Texture("swordHero/deathL.png");
		}
		
		else if(this.status == "takingHit") {
			if(this.rightOrientation) 	tx = new Texture("swordHero/getHitR.png");
			else 						tx = new Texture("swordHero/getHitL.png");
		}
		
		else if(this.status == "walking") {
			if(this.rightOrientation) {
				if(this.xPos%100 > 50)  tx = new Texture("swordHero/herowalk1R.png");
				else					tx = new Texture("swordHero/herowalk2R.png");
			}
			else {
				if(this.xPos%100 > 50)  tx = new Texture("swordHero/herowalk1L.png");
				else					tx = new Texture("swordHero/herowalk2L.png");
			}
		}
		else {
			if(this.rightOrientation) 	tx = new Texture("swordHero/heroAwaitingR.png");
			else 						tx = new Texture("swordHero/heroAwaitingL.png");
		}
		
		return tx;
	}
	
	

}

