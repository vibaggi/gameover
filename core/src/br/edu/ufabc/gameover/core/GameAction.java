package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.gameover.models.BgWorld1;
import br.edu.ufabc.gameover.models.CloudObject;
import br.edu.ufabc.gameover.models.CogsEnemy;
import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.PassiveEnemy;
import br.edu.ufabc.gameover.models.ScenarioObject;
import br.edu.ufabc.gameover.models.SwordHero;
import br.edu.ufabc.gameover.models.TreeEnemy;
import br.edu.ufabc.gameover.physics.attack.AttackZone;
import br.edu.ufabc.gameover.physics.attack.ExplosionBladesAttack;
import br.edu.ufabc.gameover.physics.attack.PhysicAttack;
import br.edu.ufabc.gameover.physics.attack.ProjetilAttack;

public class GameAction {
	
	protected SwordHero hero;
	protected BgWorld1 bg;
	protected Array<ScenarioObject> objects;
	protected Array<PassiveEnemy> enemies;
	protected Array<PhysicAttack> attackZones;
	protected Array<ProjetilAttack> projetilZones;
	
	protected SpriteBatch sprite;
	
	//Sistemas de orientação
	private int xGeneralCoordenate = 0; //posicao da tela 
	private int [][] groundCoordenates; //coordenadas de orientação de chão. Usada pelo sistema de gravidade para puxar os objetos, inimigos e heroi para o chão.
	
	
	//Interface
	int record; //sistema de pontos
	
	
	public GameAction() {
		
		//
		
		objects = new Array<ScenarioObject>(); //iniciando arrays de objetos na tela
		enemies = new Array<PassiveEnemy>();
		attackZones = new Array<PhysicAttack>();
		projetilZones = new Array<ProjetilAttack>();
		bg = new BgWorld1(); //iniciando plano de fundo
		
		//geracao de objetos de cenario
		objects.add(new CloudObject());
		
		
		//geracao de inimigos
		int y = 90;
		for (int i = 0; i < 5; i++) {
			enemies.add(new TreeEnemy((int)(Math.random()*1200)+100, y));
		}
		
		for (int i = 0; i < 5; i++) {
			enemies.add(new CogsEnemy((int)(Math.random()*1200)+1000, y));
		}
		
		
		sprite = new SpriteBatch();
		hero = new SwordHero(bg.getWorldGravity());
		
		record = 0;
	}
	
	
	public void update(float delta) {
		
		if (Gdx.input.isKeyPressed(Input.Keys.D)) {
			this.hero.moveHorizontal(-5);
			xGeneralCoordenate -= 5;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.A)) {
			
			if(xGeneralCoordenate < - 5) { xGeneralCoordenate += 5; this.hero.moveHorizontal(5);}
		}
		
		if (Gdx.input.isKeyPressed(Input.Keys.S)) {
					
			this.hero.statusChange("defensing");
		}
		
		if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
			this.hero.jump();
		}
//		if (Gdx.input.isKeyPressed(Input.Keys.W)) {
//			
//	
//		}
//		
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){  
			int damage = this.hero.executeAttack(1);
			if(damage > 0) { //se o ataque for zero, quer dizer que não pode ser realizado.
				PhysicAttack atk = new PhysicAttack(this.hero, damage, 50, "hero");
				attackZones.add(atk);
			}
			//Criar zona de ataque para verificar se algum inimigo foi atingido
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.X)){  
			int damage = this.hero.executeAttack(2);
			if(damage > 0) { //se o ataque for zero, quer dizer que não pode ser realizado.
				ExplosionBladesAttack atk = new ExplosionBladesAttack(this.hero, "hero");
				projetilZones.add(atk);
			}
			//Criar zona de ataque para verificar se algum inimigo foi atingido
		}
		
		//fazendo update dos objetos
		bg.update();
		for (ScenarioObject o: objects) {
			o.update();
		}
		for (PassiveEnemy e: enemies) {
			e.update(this.hero.getXpos(), this.hero.getYpos(), groundCoordenates);
		}
		
		//verificando se alguma zona de ataque acerta algum objeto.
		for(PhysicAttack atk: attackZones) {
			boolean hitAnyone = false;
			for (PassiveEnemy o: enemies) {
				//verificar se obj sofreu ataque
				if(atk.isObjReceiveAtk(o)) {
					System.out.println("Dano recebido!");
					if(o.receiveDamage(atk.getDamage())) enemies.removeValue(o, true);
					hitAnyone = true;
					this.record += atk.getDamage();
				}
			}
			
			//por fim verifica se o ataque acertou algum alvo ou acabou seu tempo. Em caso positivo será retirado da array.
			if(atk.update() <= 0 || hitAnyone) {
				//atk.update retorna o numero de frames que falta para o atkzone sumir.
				attackZones.removeValue(atk, true);
				System.out.println("Zona de ataque removida");
			}
		}
		
		//verificando projeteis
		for(ProjetilAttack atk: projetilZones) {
			boolean hitAnyone = false;
			for (PassiveEnemy o: enemies) {
				//verificar se obj sofreu ataque
				if(atk.isObjReceiveAtk(o)) {
					System.out.println("Dano recebido!");
					if(o.receiveDamage(atk.getDamage())) enemies.removeValue(o, true);
					hitAnyone = true;
					this.record += atk.getDamage();
				}
			}
			
			//por fim verifica se o ataque acertou algum alvo ou acabou seu tempo. Em caso positivo será retirado da array.
			if(atk.update() <= 0 || hitAnyone) {
				//atk.update retorna o numero de frames que falta para o atkzone sumir.
				projetilZones.removeValue(atk, true);
				System.out.println("Zona de ataque removida");
			}
		}
		
		hero.update(groundCoordenates);
		
		
	}
	
	public int getXGeneralCoordenate() {
		return this.xGeneralCoordenate;
	}
	
}

