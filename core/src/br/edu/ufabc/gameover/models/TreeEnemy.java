package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class TreeEnemy extends PassiveEnemy{

	private int x = 50;
	private int y = 50;
	private int frameState = 1;
	
	public TreeEnemy() {
		super(new Texture("tree/sprite1.png"), 80, 80, 1);
		
		this.setPosition(this.x, this.y);
		
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void walkingWay() {
		//Inicialmente o walkingWay não está considerando barreiras.
		
		this.x++;
		
		this.setPosition(this.x, this.y);
		this.frameState++;
//		switch (this.frameState) {
//		case 1:
//			this.setTexture(new Texture("tree/sprite1.png"));
//			break;
//		case 2:
//			this.setTexture(new Texture("tree/sprite2.png"));
//			break;
//		case 3:
//			this.setTexture(new Texture("tree/sprite3.png"));
//			break;
//		case 4:
//			this.setTexture(new Texture("tree/sprite4.png"));
//			break;
//		case 5:
//			this.frameState = 1;
//			break;
//		}
		
		
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pursue() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
