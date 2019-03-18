package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public abstract class Hero extends GameObject{

	//Propriedades
	private int HP;			//HP atual
	private int hitPoint; 	//Pontos de dano que inflinge 
	private int stamina;	//Energia atual, usada para lançar golpes
	private int defensePoint;	//Pontos de defesa usada quando está defendendo
	
	//variaveis de estado
	protected String status = "awaiting"; //Estados: awaiting, attacking, defending, dying, takingHit
	protected boolean rightOrientation = true; //Diz para qual lado o personagem está olhando
	private int statusTime = 0;
	private int hitPointReceived = 0; 	//Quando 0 significa que não recebeu nenhum dano.
	private int x = 50;
	private int y = 90;
	
	
	Hero(Texture texture, int HP, int hitPoint, int stamina, int defensePoint) {
		super(texture);
		this.HP 			= HP;
		this.hitPoint 		= hitPoint;
		this.stamina 		= stamina;
		this.defensePoint 	= defensePoint;
		
		this.setPosition(x, y);
	}

	@Override
	public void behavior() {
		
		
		//Verificar se recebeu dano
		int damageReceive = this.hitPointReceived;
		
		//Aplicando consequencias de estados.
		//Enquanto o heroi estiver em um estado que não seja awaiting, ele não é capaz de atacar.
		if(status == "attacking") {
			
		}else if(status == "defending") {
			damageReceive -= defensePoint;
			
		}else {
			
		}
		
		if(statusTime <= 0) {
			this.status = "awaiting";
		}else { 
			this.statusTime--; 
		}
		
		//Executando consequencias
		
		if(damageReceive > 0) {
			this.status = "takingHit";
			this.HP -= damageReceive;
		}
		
		//Limpando variaveis
		this.hitPointReceived = 0;
		
		this.setPosition(x, y);
		
	}
	
	public void moveHorizontal(int xDistance) {
		if(xDistance > 0) 	this.rightOrientation = false;
		else				this.rightOrientation = true;
		this.x -= xDistance;
	}
	
	
	public void receivedDamage(int hit) {
		this.hitPointReceived = hit;
	}
	
	public void statusChange(String status) {
		this.status = status;
		this.statusTime = 25;
	}
	
	public abstract int executeAttack(int attackNum);
	public abstract Texture getTextureByState();
	
	@Override
	public void update() {
		//attackReceivePoint: quando 0 significa que não houve ataque recebido.
		this.behavior();
		this.setTexture(this.getTextureByState());
	}

	
	public int getHitPoint() {
		return this.hitPoint;
	}
	
}