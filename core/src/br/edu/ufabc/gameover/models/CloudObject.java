package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class CloudObject extends ScenarioObject{

	private int x = 50;
	private int y = 400;
	
	public CloudObject() {
		super(new Texture("nuvem.png"), 170, 80);
		// TODO Auto-generated constructor stub
		this.setPosition(this.x, this.y);
	}

	@Override
	public void moving() {
		
		// TODO Auto-generated method stub
		this.x++;
		this.setPosition(this.x, this.y);
	}

}
