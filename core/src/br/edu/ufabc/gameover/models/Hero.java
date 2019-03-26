package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public abstract class Hero extends GameObject{

	//Propriedades
	private int maxHP;
	private int HP;			//HP atual
	private int hitPoint; 	//Pontos de dano que inflinge
	private int maxStamina;
	private int stamina;	//Energia atual, usada para lançar golpes
	private int defensePoint;	//Pontos de defesa usada quando está defendendo
	
	//variaveis de estado
	protected String status = "awaiting"; //Estados: awaiting, attacking, defending, dying, takingHit
	private int statusTime = 0;
	private int hitPointReceived = 0; 	//Quando 0 significa que não recebeu nenhum dano.
	private int x = 50;
	private int y = 90;
	
	
	Hero(Texture texture, int HP, int hitPoint, int stamina, int defensePoint) {
		super(texture);
		this.maxHP 			= HP;
		this.HP 			= HP;
		this.hitPoint 		= hitPoint;
		this.stamina 		= stamina;
		this.maxStamina		= stamina;
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
		if(this.stamina < this.maxStamina) this.stamina++; //recuperando stamina com o tempo
		
		this.setPosition(x, y);
		
	}
	
	public void moveHorizontal(int xDistance) {
		if(xDistance > 0) 	this.rightOrientation = false;
		else				this.rightOrientation = true;
		this.x -= xDistance;
		if(this.status != "attacking") this.statusChange("walking"); //Estado de andar não sobreescreve o de ataque
	}
	
	
	public void receivedDamage(int hit) {
		this.hitPointReceived = hit;
	}
	
	public void statusChange(String status) {
		this.status = status;
		this.statusTime = 20;
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
	
	
	
	public int getMaxHP() {
		return maxHP;
	}

	public int getHP() {
		return HP;
	}

	public int getMaxStamina() {
		return maxStamina;
	}

	public int getStamina() {
		return stamina;
	}

	/**
	 * Retorna se é possivel gastar stamina, returna false avisando que não é possivel gastar.
	 * @param quant
	 * @return
	 */
	public boolean spendStamina(int quant) {
		if(this.stamina > quant) {
			this.stamina -= quant;
			System.out.println(this.stamina);
			return true;
		}
		return false;
	}
}
