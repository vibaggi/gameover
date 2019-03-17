package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

public abstract class GameObject{
	
	//Atributos comuns do objeto
	private Texture texture; 	//imagem 
	int xPos = 0;				//posicao no X
	int yPos = 0;				//posicao no Y
	
	
	/*
	 * Classe abstratas de todos os objetos que são Sprites.
	 * */
	GameObject(Texture texture, int width, int height){
		this.texture 	= texture;
	}
	
	/**
	 * Define o comportamento do objeto.
	 */
	abstract public void behavior();
	
	
	abstract public void update();
	
	public void draw(SpriteBatch batch, int xGeneralCoordenate) {
		//Contem as informações do que exibir na tela
		batch.draw(texture, this.xPos+xGeneralCoordenate, this.yPos);
	}
	
	//Muda a posicao do objeto na tela
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	

}
