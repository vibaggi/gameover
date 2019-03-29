package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

abstract public class ScenarioObject extends GameObject{

	ScenarioObject(Texture texture) {
		super(texture, 200, 80);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void behavior() {
		// Apenas se movimenta na tela
		this.moving();
	}

	public void update() {
		this.behavior();
		
	}
	
	public abstract void moving();
	

}
