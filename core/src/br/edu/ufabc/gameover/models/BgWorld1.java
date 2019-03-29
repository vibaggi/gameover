package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class BgWorld1 extends ScenarioObject{

	//variaveis auxiliares do background
	int xGeneralCoordenate;
	float worldGravity;
	
	
	public BgWorld1() {
		super(new Texture("backgroundv1.png"));
		// Iniciando posicoes
		xGeneralCoordenate = 0;
		worldGravity = (float)0.6;
	}

	@Override
	public void moving() {
		// O movimento do background é de acordo com a coordenada geral
		//É necessário informar as coordenadas em x do heroi no update
		this.setPosition(this.xGeneralCoordenate, 0 );
	}

	public float getWorldGravity() {
		return worldGravity;
	}

	
}
