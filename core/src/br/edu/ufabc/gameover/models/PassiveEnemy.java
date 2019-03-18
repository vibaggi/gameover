package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public abstract class PassiveEnemy extends GameObject{

	//propriedades de estado
	protected String status = "awaiting"; //Estados: awaiting, attacking, defending, dying, takingHit, walking
	private boolean detectedHero = false; //Diz se o inimigo está em perseguicao ao heroi 	
	private boolean closedToHero = false; //Diz se está perto o suficiente do inimigo
	protected boolean rightOrientation = true; //Diz para qual lado o personagem está olhando
	
	//propriedades do inimigo
	private int hitPoint; //Valor do dano do inimigo
	private int hp;
	private String name;
	private int x = 200;
	private int y = 90;
	
	PassiveEnemy(Texture texture, int hitPoint, String name) {
		super(texture);
		this.hitPoint	= hitPoint;
		this.name 		= name;
		this.setPosition(x, y);
	}
	
	public void update() {
		this.behavior();
		this.setTexture(this.getTextureByState());
	}

	@Override
	public void behavior() {
		// Apenas ataca o heroi, quando atacado
		
		//TODO criar um modo de detectar o Hero
		
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
	

}
