package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.gameover.models.BgWorld1;
import br.edu.ufabc.gameover.models.CloudObject;
import br.edu.ufabc.gameover.models.CogsEnemy;
import br.edu.ufabc.gameover.models.Enemy;
import br.edu.ufabc.gameover.models.GrandTreeBossEnemy;
import br.edu.ufabc.gameover.models.Hero;
import br.edu.ufabc.gameover.models.Portal;
import br.edu.ufabc.gameover.models.ScenarioObject;
import br.edu.ufabc.gameover.models.SwordHero;
import br.edu.ufabc.gameover.models.TreeEnemy;
import br.edu.ufabc.gameover.models.ZombieEnemy;
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
	protected Portal portal; //Portal que indica fim de jogo. Ele será aberto apos derrotar o chefe.
	
	int totalPotionHP;
	int totalPotionS;
	int totalLife;
	boolean objectiveComplete 	= false; //Ao eliminar o boss o objetivo fica true e o jogo pode ser finalizado!
	boolean gameOver			= false; //Quando a flag trocar para true é pq o jogo terminou e precisa voltar para a tela de entrada
	
	// Sistemas de orientação
	private int xGeneralCoordenate = 0; // posicao da tela
	private int[][] groundCoordenates; // coordenadas de orientação de chão. Usada pelo sistema de gravidade para puxar
										// os objetos, inimigos e heroi para o chão.

	// Interface
	int record; // sistema de pontos
	private Music restartSound;
	private Music potionSound;

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
			enemies.add(new ZombieEnemy((int) (Math.random() * 1200) + 3500, y, this));
			enemies.add(new CogsEnemy((int) (Math.random() * 800) + 1400, y, this));
			enemies.add(new TreeEnemy((int) (Math.random() * 800) + 600, y, this));
		}


		sprite = new SpriteBatch();
		hero = new SwordHero(bg.getWorldGravity(), bg.getWorldMap());
//		hero = new SheHero(bg.getWorldGravity(), bg.getWorldMap());

		boss = new GrandTreeBossEnemy(5600, 90, this);
		record = 0;
		
		totalPotionHP = 5;
		totalPotionS = 5;
		totalLife	= 3; //comeca com 3 vidas. Quando chegar em 0 é game over.
		
		restartSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/comeBackWhenDie.mp3"));
		potionSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/potion.mp3"));
		
		
		portal = new Portal(6200, 60);
	}

	public boolean update(float delta) {
		
		
		if(gameOver && Gdx.input.isKeyJustPressed(Input.Keys.Z)) return false; //False significa que o jogo terminou
		//Se o heroi morreu é necessário resetar o jogo
		if (this.hero.getStatus() == "dead") {
			
			if(--totalLife < 0) 	this.gameOver();
			else					this.restart();
			
		}else if (this.hero.getStatus() == "dying") {
			hero.update(bg.getWorldMap());
			
		}else {
			//Caso o heroi esteja em dying (onde rola uma animacao de morte) o jogo espera até que ele passe para dead.
			//Caso esteja vivo executa normalmente
			
			
			
			//Captura de TECLADO
			if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) moveRight();
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) moveLeft();
			if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) this.hero.statusChange("defensing");
			if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) this.hero.jump();
			if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) comandAttack();
			if (Gdx.input.isKeyJustPressed(Input.Keys.X)) comandProjetilAttack();
			if (Gdx.input.isKeyJustPressed(Input.Keys.C)) healing();
			if (Gdx.input.isKeyJustPressed(Input.Keys.V)) healStamina();
			
			
			//Captura de MOUSE
			
			Gdx.input.setInputProcessor(new InputMouse() {
				 @Override
	            public boolean touchDown(int x, int y, int pointer, int button) {
	                if (button == Input.Buttons.LEFT) {
	                	//verificando posicoes de icones
	                	if(x > 320 && x < 360 && y > 420 && y < 460) comandAttack();
	                	if(x > 370 && x < 420 && y > 420 && y < 460) comandProjetilAttack();
	                	if(x > 430 && x < 470 && y > 420 && y < 460) healing();
	                	if(x > 480 && x < 520 && y > 420 && y < 460) healStamina();
	                	if(x > 45 && x < 70 && y > 430 && y < 460) moveLeft();
	                	if(x > 71 && x < 100 && y > 430 && y < 460) hero.statusChange("defensing");
	                	if(x > 101 && x < 135 && y > 430 && y < 460) moveRight();
	                	if(x > 71 && x < 100 && y > 400 && y < 429) hero.jump();
	                    return true;
	                }
	                return false;
	            }
			});
			
			
			
			

			
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
						
						if (o.receiveDamage(atk.getDamage())) enemies.removeValue(o, true);
						hitAnyone = true;
						this.record += atk.getDamage();
					}
				}
				
				if(atk.isObjReceiveAtk(boss)) {
					if(boss.receiveDamage(atk.getDamage())) {
						objectiveComplete = true;
						boss.statusChange("dying");
						portal.openPortal();
					}
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
					if (boss.receiveDamage(atk.getDamage())){
						objectiveComplete = true;
						boss.statusChange("dying");
						portal.openPortal();
					}
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
			
			if(objectiveComplete && portal.isEnterInPortal(hero.getXpos(), hero.getYpos())) return false;

		}
		
		return true; //True significa que o jogo ainda está rodando.

	}


	private void healStamina() {
		//USAR POÇÃO STAMINA
		potionSound.play();
		hero.restoreStamina(500);
		totalPotionS--;
	}

	private void healing() {
		//USAR POÇÃO HP
		potionSound.play();
		hero.restoreHP(50);
		totalPotionHP--;
	}

	private void restart() {
		// Envia de volta para o inicio da tela, porem mantem 
		System.out.println("restart");
		this.hero.statusChange("awaiting");
		this.hero.reset();
		this.xGeneralCoordenate = 0;
		restartSound.play();
	}

	private void gameOver() {
		// Envia de volta para a tela de inicio
		gameOver = true;
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
	
	
	private void comandProjetilAttack() {
		int damage = this.hero.executeAttack(2);
		if (damage > 0) { // se o ataque for zero, quer dizer que não pode ser realizado.
			ExplosionBladesAttack atk = new ExplosionBladesAttack(this.hero, "hero");
			projetilZones.add(atk);
		}
		// Criar zona de ataque para verificar se algum inimigo foi atingido
	}
	
	private void comandAttack() {
		int damage = this.hero.executeAttack(1);
		if (damage > 0) { // se o ataque for zero, quer dizer que não pode ser realizado.
			PhysicAttack atk = new PhysicAttack(this.hero, damage, 50, "hero");
			attackZones.add(atk);
		}
		// Criar zona de ataque para verificar se algum inimigo foi atingido
	}
	
	private void moveLeft() {
		if (xGeneralCoordenate < -5) {
			xGeneralCoordenate += 5;
			this.hero.moveHorizontal(5);
		}
	}
	
	private void moveRight() {
		if(xGeneralCoordenate > - 6300) {
			this.hero.moveHorizontal(-5);
			xGeneralCoordenate -= 5;
		}
	}

}
