package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.gameover.core.GameAction;

public abstract class GameObject{
	
	//Atributos comuns do objeto
	private Texture texture; 	//imagem 
	int xPos = 0, width;		//posicao no X
	int yPos = 0, height;		//posicao no Y
	protected boolean rightOrientation; //Diz para qual lado o personagem está olhando
	
	
	
	
	
	/*
	 * Classe abstratas de todos os objetos que são Sprites.
	 * */
	GameObject(Texture texture, int width, int height){
		this.texture 	= texture;
		this.width		= width;
		this.height		= height;
	}
	
	/**
	 * Define o comportamento do objeto.
	 */
	abstract public void behavior();
	
	
	public void draw(SpriteBatch batch, int xGeneralCoordenate) {
		//Contem as informações do que exibir na tela
		batch.draw(texture, this.xPos+xGeneralCoordenate, this.yPos);
	}
	
	//Muda a posicao do objeto na tela
	public void setPosition(int x, int y) {
		this.xPos = x;
		this.yPos = y;
	}
	
	public void setTexture(Texture tx) {
		this.texture = tx;
	}
	
	public int getXpos() {
		return this.xPos;
	}
	
	public int getYpos() {
		return this.yPos;
	}
	
	public int getX2pos() {
		return this.xPos+this.width;
	}
	
	public int getY2pos() {
		return this.yPos+this.height;
	}

	public boolean isRightOrientation() {
		return rightOrientation;
	}
	
	
	

}
