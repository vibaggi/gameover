package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.gameover.models.BgWorld1;
import br.edu.ufabc.gameover.models.CloudObject;
import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.SwordHero;
import br.edu.ufabc.gameover.models.TreeEnemy;

public class GameAction {
	
	protected SwordHero hero;
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
		hero = new SwordHero();
	}
	
	
	public void update(float delta) {
		//fazendo update dos objetos
		bg.update();
		for (GameObject o: objects) {
			o.update();
		}
		hero.update();
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.hero.moveHorizontal(-5);
			xGeneralCoordenate -= 5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			
			if(xGeneralCoordenate < - 5) { xGeneralCoordenate += 5; this.hero.moveHorizontal(5);}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){  
			int damage = this.hero.executeAttack(1);
			System.out.println(damage);
		}
	}
	
	public int getXGeneralCoordenate() {
		return this.xGeneralCoordenate;
	}
	
}
