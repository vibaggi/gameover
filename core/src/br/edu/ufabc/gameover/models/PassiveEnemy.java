package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class PassiveEnemy extends GameObject{

	//propriedades de estado
	protected String status = "awaiting"; //Estados: awaiting, attacking, defending, dying, takingHit, walking
	private int statusTime = 0;
	private boolean detectedHero = false; //Diz se o inimigo está em perseguicao ao heroi 	
	private boolean closedToHero = false; //Diz se está perto o suficiente do inimigo
	protected boolean rightOrientation = true; //Diz para qual lado o personagem está olhando
	private BitmapFont bitmapFont;
	
	
	//propriedades do inimigo
	private int hitPoint; //Valor do dano do inimigo
	private int hp;
	private int hpTotal;
	private String name;
	private int x = 200;
	private int y = 90;
	
	PassiveEnemy(Texture texture, int hitPoint, String name, int xPosInitial, int yPosInitial) {
		super(texture);
		this.x = xPosInitial;
		this.y = yPosInitial;
		this.hitPoint	= hitPoint;
		this.name 		= name;
		this.setPosition(x, y);
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/myfont.fnt"));
		hpTotal = 100;
		hp = 100;
	}
	
	public void update() {
		this.behavior();
		this.setTexture(this.getTextureByState());
	}

	@Override
	public void behavior() {
		// Apenas ataca o heroi, quando atacado
		
		this.statusTime--;
		if(this.statusTime <= 0) this.statusChange("awaiting");
		
		if(this.status != "takingHit") { //inimigos sofrem delay ao serem atacados
			
			if(!this.detectedHero) {
				//Enquanto não provocado, irá apenas andar sem rumo
				this.walkingWay();
			}else {
				//Quando provocado 
				if(!this.closedToHero) {
					//Se longe do Heroi irá buscar um caminho até ele
					this.pursue();
				}else {
					//Se perto o suficiente do heroi tentará ataque
					this.attack();
				}
				
			}
			
		}
		this.setPosition(this.x, this.y);
		
		
	}
	
	public void moveHorizontal(int xDistance) {
		if(xDistance > 0) 	this.rightOrientation = false;
		else				this.rightOrientation = true;
		this.x -= xDistance;
	}
	
	abstract public void walkingWay(); 	//Andar sem rumo
	abstract public void attack();		//Atacar o heroi
	abstract public void pursue();		//Perseguir o heroi 
	abstract public Texture getTextureByState();
	
	//Adicionado barra de HP ao inimigo
	public void draw(SpriteBatch batch, int xGeneralCoordenate) {
		bitmapFont.draw(batch, String.valueOf(hp)+"/"+String.valueOf(hpTotal), this.getXpos()+xGeneralCoordenate, this.getYpos());
		bitmapFont.draw(batch, name, this.getXpos()+xGeneralCoordenate, this.getYpos()-50);
		super.draw(batch, xGeneralCoordenate);
	}
	
	/**
	 * Retorna true quando inimigo zerou a vida
	 * @param damage passar dano a ser recebido pelo inimigo
	 * @return
	 */
	public boolean receiveDamage(int damage) {
		this.statusChange("takingHit");
//		this.detectedHero = true;
		this.hp -= damage;
		return this.hp <= 0;
	}
	
	public void statusChange(String status) {
		this.status = status;
		this.statusTime = 20;
	}
	
	

}
