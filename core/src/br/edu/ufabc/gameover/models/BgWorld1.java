package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class BgWorld1 extends ScenarioObject{

	//variaveis auxiliares do background
	int xGeneralCoordenate;
	float worldGravity;
	public Music music;
	
	private int [][] groundCoordenates; //sistema de chao, respectivamente significa altura, posicao inicial e posicao final do chao
	
	public BgWorld1() {
		super(new Texture("backgroundv1.png"));
		// Iniciando posicoes
		xGeneralCoordenate = 0;
		worldGravity = (float)0.6;
		
		//coordenadas dos lugares mais altos primeiro
		groundCoordenates = new int[][]{
			{200, 250, 400},
			{90, 0, 2280}, //ex: na altura 90 há chão do px 0 ao 100

		};
		
		music = Gdx.audio.newMusic(Gdx.files.internal("sounds/scenario1/ambientation.mp3"));
		music.setLooping(true);
		music.play();
		
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
	
	public int[][] getWorldMap(){
		return groundCoordenates;
	}

	
}
