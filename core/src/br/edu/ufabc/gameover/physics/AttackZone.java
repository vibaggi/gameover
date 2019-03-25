package br.edu.ufabc.gameover.physics;

import br.edu.ufabc.gameover.models.GameObject;

/**
 * UMA ZONA DE ATAQUE É USADA PARA O GAMEACTION AVALIAR SE ALGUM INIMIGO/HERO LEVOU DANO FISICO OU DE PROJÉTEIS
 * @author vitorbaggi
 *
 */


public abstract class AttackZone {
	
	private int x1;
	private int x2;
	private int y1;
	private int y2;
	private int damageZone;
	private int duration;
	private String creator;
	
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
		//TODO implementar
		if(obj.getXpos()> this.x1 && obj.getXpos() < this.x2) return true;
		return false;
	}
	
	public int getDamage() {
		return this.damageZone;
	}
	
	abstract public void behavior(); //define o comportamento do objeto

}
