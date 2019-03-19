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
import br.edu.ufabc.gameover.physics.AttackZone;
import br.edu.ufabc.gameover.physics.PhysicAttack;

public class GameAction {
	
	protected SwordHero hero;
	protected BgWorld1 bg;
	protected Array<GameObject> objects;
	protected Array<AttackZone> attackZones;
	private int xGeneralCoordenate = 0; //posicao da tela 
	protected SpriteBatch sprite;
	
	
	public GameAction() {
		
		objects = new Array<GameObject>(); //iniciando arrays de objetos na tela
		attackZones = new Array<AttackZone>();
		bg = new BgWorld1(); //iniciando plano de fundo
		
		objects.add(new CloudObject());
		objects.add(new TreeEnemy());
		
		sprite = new SpriteBatch();
		hero = new SwordHero();
	}
	
	
	public void update(float delta) {
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.hero.moveHorizontal(-5);
			xGeneralCoordenate -= 5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			
			if(xGeneralCoordenate < - 5) { xGeneralCoordenate += 5; this.hero.moveHorizontal(5);}
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){  
			int damage = this.hero.executeAttack(1);
			if(damage > 0) { //se o ataque for zero, quer dizer que não pode ser realizado.
				PhysicAttack atk = new PhysicAttack(this.hero, damage, 50, "hero");
				attackZones.add(atk);
			}
			//Criar zona de ataque para verificar se algum inimigo foi atingido
		}
		
		//fazendo update dos objetos
		bg.update();
		for (GameObject o: objects) {
			o.update();
		}
		
		//verificando se alguma zona de ataque acerta algum objeto.
		for(AttackZone atk: attackZones) {
			boolean hitAnyone = false;
			for (GameObject o: objects) {
				//verificar se obj sofreu ataque
				if(atk.isObjReceiveAtk(o)) {
					System.out.println("Dano recebido!");
					hitAnyone = true;
				}
			}
			
			//por fim verifica se o ataque acertou algum alvo ou acabou seu tempo. Em caso positivo será retirado da array.
			if(atk.update() <= 0 || hitAnyone) {
				//atk.update retorna o numero de frames que falta para o atkzone sumir.
				attackZones.removeValue(atk, true);
				System.out.println("Zona de ataque removida");
			}
		}
		
		hero.update();
		
		
	}
	
	public int getXGeneralCoordenate() {
		return this.xGeneralCoordenate;
	}
	
}
