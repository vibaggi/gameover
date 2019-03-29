package br.edu.ufabc.gameover.physics.attack;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ProjetilAttack extends AttackZone{

	private Texture tx;
	
	public ProjetilAttack(Texture tx, int x1, int width, int y1, int height, int damage, int duration, String creator) {
		super(x1, x1+width, y1, y1+height, damage, duration, creator);
		this.tx = tx;
	}
	
	public void draw(SpriteBatch sprite, int xGeneralCoordenate) {
		sprite.draw(tx, this.getX1()+xGeneralCoordenate, this.getY1());
	}
	

}
