package br.edu.ufabc.gameover.physics;

import br.edu.ufabc.gameover.models.GameObject;

public class PhysicAttack extends AttackZone{

	public PhysicAttack(GameObject objEmitter, int damage, int duration, String creator) {
		super(objEmitter.getXpos(), objEmitter.getXpos()+50, objEmitter.getYpos(), objEmitter.getYpos()+10, damage, duration, creator);
	}

}
