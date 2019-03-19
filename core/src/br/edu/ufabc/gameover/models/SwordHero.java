package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class SwordHero extends Hero{
	
	static int hpSH = 100;
	static int staminaSH = 50;
	static int hitPointSH = 10;
	static int defensePoint = 5;
	

	public SwordHero() {
		super(new Texture("swordHero/heroAwaitingR.png"), hpSH, hitPointSH, staminaSH, defensePoint);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Deve verificar se é possivel atacar e retornar o valor do ataque
	 * @param numberAttack os herois tem 3 niveis de ataque
	 * @return
	 */
	public int executeAttack(int numberAttack) {
		if(this.status == "awaiting" || this.status == "walking") {
			//Ataque interrompe qualquer um dos status acima, menos o "receive hit"
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
