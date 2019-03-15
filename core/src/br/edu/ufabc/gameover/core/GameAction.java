package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.gameover.models.BgWorld1;
import br.edu.ufabc.gameover.models.CloudObject;
import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.TreeEnemy;

public class GameAction {
	
	protected BgWorld1 bg;
	protected Array<GameObject> objects;
	private int xGeneralCoordenate = 0; //posicao da tela 
	protected SpriteBatch sprite;
	
	public GameAction() {
		
		objects = new Array<GameObject>(); //iniciando arrays de objetos na tela
		bg = new BgWorld1(); //iniciando plano de fundo
		
		objects.add(new CloudObject());
		objects.add(new TreeEnemy());
		
		sprite = new SpriteBatch();
	}
	
	
	public void update(float delta) {
		//fazendo update dos objetos
		bg.update(this.xGeneralCoordenate);
		for (GameObject o: objects) {
			o.update();
		}
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			System.out.println("Andou direita");
			xGeneralCoordenate -= 5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			System.out.println("Andou esquerda");
			if(xGeneralCoordenate < - 5) xGeneralCoordenate += 5;
		}
	}
	
}
