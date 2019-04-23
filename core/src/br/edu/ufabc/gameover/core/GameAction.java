package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.gameover.models.AggressiveEnemy;
import br.edu.ufabc.gameover.models.BgWorld1;
import br.edu.ufabc.gameover.models.CloudObject;
import br.edu.ufabc.gameover.models.CogsEnemy;
import br.edu.ufabc.gameover.models.Enemy;
import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.GrandTreeBossEnemy;
import br.edu.ufabc.gameover.models.Hero;
import br.edu.ufabc.gameover.models.PassiveEnemy;
import br.edu.ufabc.gameover.models.ScenarioObject;
import br.edu.ufabc.gameover.models.SheHero;
import br.edu.ufabc.gameover.models.SwordHero;
import br.edu.ufabc.gameover.models.TreeEnemy;
import br.edu.ufabc.gameover.models.ZombieEnemy;
import br.edu.ufabc.gameover.physics.attack.AttackZone;
import br.edu.ufabc.gameover.physics.attack.ExplosionBladesAttack;
import br.edu.ufabc.gameover.physics.attack.PhysicAttack;
import br.edu.ufabc.gameover.physics.attack.ProjetilAttack;

public class GameAction {

	protected Hero hero;
	protected BgWorld1 bg;
	protected Array<ScenarioObject> objects;
	protected Array<Enemy> enemies;
	protected Array<PhysicAttack> attackZones;
	protected Array<ProjetilAttack> projetilZones;
	protected GrandTreeBossEnemy boss;
	
	protected SpriteBatch sprite;
	
	int totalPotionHP;
	int totalPotionS;

	// Sistemas de orientação
	private int xGeneralCoordenate = 0; // posicao da tela
	private int[][] groundCoordenates; // coordenadas de orientação de chão. Usada pelo sistema de gravidade para puxar
										// os objetos, inimigos e heroi para o chão.

	// Interface
	int record; // sistema de pontos
										

	public GameAction() {


		objects = new Array<ScenarioObject>(); // iniciando arrays de objetos na tela
		enemies = new Array<Enemy>();

		attackZones = new Array<PhysicAttack>();
		projetilZones = new Array<ProjetilAttack>();
		bg = new BgWorld1(); // iniciando plano de fundo

		// geracao de objetos de cenario
		objects.add(new CloudObject());

		// geracao de inimigos
		int y = 90;

		for (int i = 0; i < 5; i++) {
			enemies.add(new ZombieEnemy((int) (Math.random() * 1200) + 1000, y, this));
		}


		sprite = new SpriteBatch();
		hero = new SwordHero(bg.getWorldGravity(), bg.getWorldMap());
//		hero = new SheHero(bg.getWorldGravity(), bg.getWorldMap());

		boss = new GrandTreeBossEnemy(800, 90, this);
		record = 0;
		
		totalPotionHP = 5;
		totalPotionS = 5;
	}

	public void update(float delta) {
		
		//Se o heroi morreu é necessário resetar o jogo
		if (this.hero.getStatus() == "dead") {
			
			
		}else if (this.hero.getStatus() == "dying") {
			hero.update(bg.getWorldMap());
			
		}else {
			//Caso o heroi esteja em dying (onde rola uma animacao de morte) o jogo espera até que ele passe para dead.
			//Caso esteja vivo executa normalmente
			
			
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if(xGeneralCoordenate > - 6300) {
					this.hero.moveHorizontal(-5);
					xGeneralCoordenate -= 5;
				}
				
			}
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

				if (xGeneralCoordenate < -5) {
					xGeneralCoordenate += 5;
					this.hero.moveHorizontal(5);
				}
			}

			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {

				this.hero.statusChange("defensing");
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
				this.hero.jump();
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
				int damage = this.hero.executeAttack(1);
				if (damage > 0) { // se o ataque for zero, quer dizer que não pode ser realizado.
					PhysicAttack atk = new PhysicAttack(this.hero, damage, 50, "hero");
					attackZones.add(atk);
				}
				// Criar zona de ataque para verificar se algum inimigo foi atingido
			}

			if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
				int damage = this.hero.executeAttack(2);
				if (damage > 0) { // se o ataque for zero, quer dizer que não pode ser realizado.
					ExplosionBladesAttack atk = new ExplosionBladesAttack(this.hero, "hero");
					projetilZones.add(atk);
				}
				// Criar zona de ataque para verificar se algum inimigo foi atingido
			}
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.C)) {
				//USAR POÇÃO HP
			}
			
			if (Gdx.input.isKeyJustPressed(Input.Keys.V)) {
				//USAR POÇÃO STAMINA
			}
			

			
			// fazendo update dos objetos
			bg.update();
			boss.update(hero.getXpos(), hero.getYpos(), groundCoordenates);
			
			for (ScenarioObject o : objects) {
				o.update();
			}
			for (Enemy e : enemies) {
				e.update(this.hero.getXpos(), this.hero.getYpos(), groundCoordenates);
			}

			// verificando se alguma zona de ataque acerta algum objeto.
			for (PhysicAttack atk : attackZones) {
				boolean hitAnyone = false;
				for (Enemy o : enemies) {
					// verificar se obj sofreu ataque
					if (atk.isObjReceiveAtk(o)) {
						
						if (o.receiveDamage(atk.getDamage()))
							enemies.removeValue(o, true);
						hitAnyone = true;
						this.record += atk.getDamage();
					}
				}
				
				if(atk.isObjReceiveAtk(boss)) {
					if(boss.receiveDamage(atk.getDamage())) System.out.println("Venceu o jogo!");
					hitAnyone = true;
					this.record += atk.getDamage();
				}
				
				//verificando se algum dano inimigo atingiu o Hero
				if(atk.isObjReceiveAtk(hero)) {
					//if (hero.receiveDamage(atk.getDamage())) dead();
					hero.receivedDamage(atk.getDamage());
					hitAnyone = true;
				}

				// por fim verifica se o ataque acertou algum alvo ou acabou seu tempo. Em caso
				// positivo será retirado da array.
				if (atk.update() <= 0 || hitAnyone) {
					// atk.update retorna o numero de frames que falta para o atkzone sumir.
					attackZones.removeValue(atk, true);
					System.out.println("Zona de ataque removida");
				}
			}

			// verificando projeteis
			for (ProjetilAttack atk : projetilZones) {
				boolean hitAnyone = false;
				for (Enemy o : enemies) {
					// verificar se obj sofreu ataque
					if (atk.isObjReceiveAtk(o)) {
						System.out.println("Dano recebido!");
						if (o.receiveDamage(atk.getDamage())) 
							enemies.removeValue(o, true);
						hitAnyone = true;
						this.record += atk.getDamage();
					}
				}
				
				//verificando se algum dano inimigo atingiu o Hero
				if(atk.isObjReceiveAtk(hero)) {
					//if (hero.receiveDamage(atk.getDamage())) dead();
					hero.receivedDamage(atk.getDamage());
					hitAnyone = true;
				}
				
				if(atk.isObjReceiveAtk(boss)) {
					if (boss.receiveDamage(atk.getDamage())) System.out.println("Venceu o jogo!");
					hitAnyone = true;
					this.record += atk.getDamage();
				}
				

				// por fim verifica se o ataque acertou algum alvo ou acabou seu tempo. Em caso
				// positivo será retirado da array.
				if (atk.update() <= 0 || hitAnyone) {
					// atk.update retorna o numero de frames que falta para o atkzone sumir.
					projetilZones.removeValue(atk, true);
					System.out.println("Zona de ataque removida");
				}
			}

			hero.update(groundCoordenates);

		}

	}

	public int getXGeneralCoordenate() {
		return this.xGeneralCoordenate;
	}

	public void addPhysicAttack(PhysicAttack p) {
		this.attackZones.add(p);
	}

	public void addProjetilAttack(ProjetilAttack p) {
		this.projetilZones.add(p);
	}

}
