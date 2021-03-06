package br.edu.ufabc.gameover.physics.attack;

import br.edu.ufabc.gameover.models.Enemy;
import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.Hero;
import br.edu.ufabc.gameover.models.PassiveEnemy;

/**
 * UMA ZONA DE ATAQUE É USADA PARA O GAMEACTION AVALIAR SE ALGUM INIMIGO/HERO LEVOU DANO FISICO OU DE PROJÉTEIS
 * @author vitorbaggi
 *
 */


public abstract class AttackZone {
	
	protected int x1;
	protected int x2;
	protected int y1;
	protected int y2;
	private int damageZone;
	private int duration;
	private String creator;
	protected boolean orientationMovRight = true; 
	
	public AttackZone(int x1, int x2, int y1, int y2, int damage, int duration, String creator ) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
		this.damageZone = damage;
		this.duration = duration;
		this.creator = creator;
	}
	
	/**
	 * Usado para movimentar a zona de ataca. Exemplo projeteis devem atualizar a zona de ataque ao se movimentar.
	 * @param deltaX
	 * @param deltaY
	 */
	public void moveZone(int deltaX, int deltaY) {
		this.x1 -= deltaX; this.x2 -= deltaX;
		this.y1 -= deltaY; this.y2 -= deltaY;
	}
	
	//Decrementa tempo que falta para expirar zona de ataque. E retorna tempo.
	public int update() {
		this.behavior();
		return --duration;
	}
	
	/**
	 * Verifica se inimigo/hero sofreu ataque. Hero não sofre proprio ataque e inimigos não sofrem ataque entre sí.
	 * @param obj
	 * @return
	 */
	public boolean isObjReceiveAtk(GameObject obj){
		
		if(this.getCreator() == "hero" && obj instanceof Hero) return false; //verificando se o criador do ataque não é o proprio heroi
		if(this.getCreator() == "enemy" && obj instanceof Enemy) return false; //verificando se o criador do ataque não é o proprio monstro
		//Só receberá dano o obj que não for o proprio criador e que esteja dentro da zona de ataque.
		if( !(this.x2 < obj.getXpos() || this.x1 > obj.getX2pos()) && !( this.y2 <obj.getYpos() || this.y1 > obj.getY2pos()) ) return true;
		return false;
	}
	
	public int getDamage() {
		return this.damageZone;
	}
	
	abstract public void behavior(); //define o comportamento do objeto

	public int getX1() {
		return x1;
	}

	public int getX2() {
		return x2;
	}

	public int getY1() {
		return y1;
	}

	public int getY2() {
		return y2;
	}

	public int getDamageZone() {
		return damageZone;
	}

	public String getCreator() {
		return creator;
	}
	
	public int getDuration() {
		return duration;
	}

	
	
	
}
