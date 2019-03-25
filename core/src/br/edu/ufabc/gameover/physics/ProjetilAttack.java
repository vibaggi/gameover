package br.edu.ufabc.gameover.physics;

import com.badlogic.gdx.graphics.Texture;

public class ProjetilAttack extends AttackZone{

	private Texture tx;
	
	public ProjetilAttack(Texture tx, int x1, int x2, int y1, int y2, int damage, int duration, String creator) {
		super(x1, x2, y1, y2, damage, duration, creator);
		this.tx = tx;
	}

	@Override
	public void behavior() {
		// TODO Auto-generated method stub
		
	}
	
	

}
