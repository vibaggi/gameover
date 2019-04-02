package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

abstract public class AggressiveEnemy extends Enemy{

	AggressiveEnemy(Texture texture, int hitPoint, String name, int xPosInitial, int yPosInitial, int width, int height,
			int velWalk, int velPursue) {
		super(texture, hitPoint, name, xPosInitial, yPosInitial, width, height, velWalk, velPursue);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void behavior() {
		// TODO Auto-generated method stub
		
	}

}
