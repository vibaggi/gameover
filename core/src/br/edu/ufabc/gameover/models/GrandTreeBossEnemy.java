package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.core.GameAction;
import br.edu.ufabc.gameover.physics.attack.RootsBossAttack;

 public class GrandTreeBossEnemy extends Enemy{

	private int frameState = 1;	//Variavel usada para exibir animacao de textura se o estado tiver. Exemplo: animação ao andar.
	 
	
	//Sounds
	public Music getHitSound; //som ao receber um ataque
	public Music walkingSound; //som ao andar
	private GameAction game;
		
		
	public GrandTreeBossEnemy(int xPosInitial, int yPosInitial, GameAction game) {
		super(new Texture("grandTree/grandTreeS1L.png"), 20, "Árvore Ancião", xPosInitial, yPosInitial, 600, 600, 0, 0, 500);
		getHitSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/tree/getHit.mp3"));
		walkingSound = Gdx.audio.newMusic(Gdx.files.internal("sounds/tree/walking.mp3"));
		this.game = game;
		
	}

	@Override
	public void behavior() {
		
		
		//Comportamento parecido com o agressivo. Entretanto o boss sempre sabe onde está o heroi na tela.
		if(this.status == "dying" && this.statusTime <= 0) {
			this.statusChange("dead");
		} else if(this.status != "dead" && this.status != "dying") {
			
			if(this.statusTime <= 0) this.statusChange("awaiting");
			
			if(this.status != "takingHit" && this.status != "attacking") { //Boss sofre delay ao ser atacado
				
				//GrandTree é um inimigo imovel
				
				
				this.attack();
				
				
			}
		}
		this.statusTime--;

		
	}
	
	
//	private void searchHero() {
//		int deltaXToHero = Math.abs((this.getXpos()+(this.width/2)) -this.targetX);
//		System.out.println(deltaXToHero);
//		if( (this.getYpos() <= this.targetY && this.getY2pos() >= this.targetY) 
//				&& ( deltaXToHero < 300)) {
//			this.detectedHero = true;
//		}
//		
//	}

	public void pursue(int targetX, int targetY) {
		if( (targetX-this.getXpos()) > 50 ) {
			
			this.moveHorizontal(-velPursue);
		}else if( (targetX-this.getXpos()) < - 50 ) {
			this.moveHorizontal(velPursue);
		} else {
			this.setClosedToHero(true);
		}
		
	}

	@Override
	public void attack() {
		//Cria uma área embaixo do inimigo em que raizes saem por debaixo e causam dando.
		//Há um delay para as raizes sairem debaixo do heroi.
		
		game.addProjetilAttack(new RootsBossAttack(this.targetX, this.targetY, "enemy"));
		this.statusChange("attacking");
		this.statusTime += 200; //Este ataque em particular é mais demorado.
		
	}

	@Override
	public Texture getTextureByState() {
		
		Texture tx = new Texture("grandTree/grandTreeS1L.png"); //inicialização padrão
		
		this.frameState++; //contador que auxilia a animação
		
		System.out.println(status);
		if(status == "dead") {
			tx = new Texture("grandTree/grandTreeDying6.png");
		}else if(status == "dying") {
			if(statusTime > 16) tx = new Texture("grandTree/grandTreeDying1.png");
			else if(statusTime > 12) tx = new Texture("grandTree/grandTreeDying2.png");
			else if(statusTime > 8) tx = new Texture("grandTree/grandTreeDying3.png");
			else if(statusTime > 4) tx = new Texture("grandTree/grandTreeDying4.png");
			else if(statusTime > 0) tx = new Texture("grandTree/grandTreeDying5.png");
		}else if(status == "takingHit") {
			getHitSound.play(); //Som ao receber um ataque
			tx = new Texture("grandTree/grandTreeGetHitL.png");
		}else if(status == "attacking" && this.statusTime < 140 && this.statusTime > 100){
			tx = new Texture("grandTree/grandTreeAttackingL.png");
		}else {
			tx = new Texture("grandTree/grandTreeS1L.png");
		}
		
		return tx;
	}
	


	

}
