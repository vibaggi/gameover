package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

abstract public class ScenarioObject extends GameObject{

	ScenarioObject(Texture texture, int width, int height) {
		super(texture, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void behavior() {
		// Apenas se movimenta na tela
		this.moving();
	}

	@Override
	public void update() {
		this.behavior();
		
	}
	
	public abstract void moving();
	

}
