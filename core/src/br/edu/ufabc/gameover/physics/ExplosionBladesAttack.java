package br.edu.ufabc.gameover.physics;

import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.models.GameObject;

public class ExplosionBladesAttack extends ProjetilAttack{
	
	public ExplosionBladesAttack(GameObject objEmitter, String creator) {
		super(new Texture("projeteis/bladeDamage.png"),objEmitter.getXpos()+10, 40, objEmitter.getYpos(), 50, 20, 200, creator);
		// TODO Auto-generated constructor stub
		System.out.println(this.orientationMovRight);
		if(objEmitter.isRightOrientation()) this.orientationMovRight = true;
		else								this.orientationMovRight = false;
	}

	@Override
	public void behavior() {
		
		//Lanca um ataca cortante na horizontal
		
		if(this.orientationMovRight) this.moveZone(-5, 0);
		else					     this.moveZone(5, 0);
		
		
	}
	

}
