package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract public class Enemy extends GameObject{

	//propriedades de estado
	protected String status = "awaiting"; 	//Estados: awaiting, attacking, defending, dying, takingHit, walking
	protected int statusTime = 0;
	protected boolean detectedHero = false; 	//Diz se o inimigo está em perseguicao ao heroi
	protected int targetX, targetY; 			//Target diz respeito ao destino que o inimigo tem para o alvo (Hero)
	protected boolean closedToHero = false; 	//Diz se está perto o suficiente do inimigo
	
	protected int walkingTarget = 0; //Passos que faltam para chegar ao destino. Anda para direita se positivo, esquerda se negativo.
	protected int velWalkingAway;
	protected int velPursue;
	
	private BitmapFont bitmapFont;
	
	
	//propriedades do inimigo
	private int hitPoint; //Valor do dano do inimigo
	private int hp;
	private int hpTotal;
	private String name;
	private int x = 200;
	private int y = 90;
	
	
	
	
	Enemy(Texture texture, int hitPoint, String name, int xPosInitial, int yPosInitial, int width, int height, int velWalk, int velPursue) {
		super(texture, width, height);
		this.x = xPosInitial;
		this.y = yPosInitial;
		this.hitPoint	= hitPoint;
		this.name 		= name;
		this.velWalkingAway = velWalk;
		this.velPursue		= velPursue;
		this.setPosition(x, y);
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/monstersInterface.fnt"));
		hpTotal = 100;
		hp = 100;
	}
	
	public void update(int heroXpos, int heroYpos, int[][]map) {
		
		//atualizando localidade do heroi
		this.targetX = heroXpos;
		this.targetY = heroYpos;
		
		//executando comportamento
		if(this.isVisibleInScreen()) {
			this.behavior();
			this.setPosition(this.x, this.y);
			this.setTexture(this.getTextureByState());
		}
	
	}
	

	public void moveHorizontal(int xDistance) {
		if(xDistance > 0) 	this.rightOrientation = false;
		else				this.rightOrientation = true;
		this.x -= xDistance;
	}
	public void moveVertical(int yDistance) {
		this.y -= yDistance;
	}
	
	
	
	abstract public void attack();		//Atacar o heroi
	abstract public Texture getTextureByState();
	
	//Adicionado barra de HP ao inimigo
	public void draw(SpriteBatch batch, int xGeneralCoordenate) {
		bitmapFont.draw(batch, String.valueOf(hp)+"/"+String.valueOf(hpTotal), this.getXpos()+xGeneralCoordenate, this.getYpos());
		bitmapFont.draw(batch, name, this.getXpos()+xGeneralCoordenate, this.getYpos()-30);
		super.draw(batch, xGeneralCoordenate);
	}
	
	/**
	 * Retorna true quando inimigo zerou a vida
	 * @param damage passar dano a ser recebido pelo inimigo
	 * @return
	 */
	public boolean receiveDamage(int damage) {
		this.statusChange("takingHit");
		this.detectedHero = true;
		this.hp -= damage;
		return this.hp <= 0;
	}
	
	public void statusChange(String status) {
		this.status = status;
		this.statusTime = 20;
	}
	
	protected void setClosedToHero(boolean close) {
		this.closedToHero = close;
	}
	
	protected boolean getClosedToHero() {
		return this.closedToHero;
	}
	
	/**
	 * Verifica se o monstro  está dentro da visão de tela.
	 * Pode ser usado para manter o monstro inativo enquanto fora de visão do usuario.
	 * @return
	 */
	public boolean isVisibleInScreen() {
		int deltaXToHero = this.x - this.targetX;
		return deltaXToHero < 700 && deltaXToHero > -300;
	}
	


}
