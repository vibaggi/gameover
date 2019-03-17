package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class BgWorld1 extends ScenarioObject{

	//variaveis auxiliares do background
	int xGeneralCoordenate;
	
	public BgWorld1() {
		super(new Texture("backgroundv1.png"));
		// Iniciando posicoes
		this.xGeneralCoordenate = 0;
	}

	@Override
	public void moving() {
		// O movimento do background é de acordo com a coordenada geral
		//É necessário informar as coordenadas em x do heroi no update
		System.out.println(this.xGeneralCoordenate);
		this.setPosition(this.xGeneralCoordenate, 0 );
	}
	

}
