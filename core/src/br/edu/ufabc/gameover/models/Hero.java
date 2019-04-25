package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.physics.environment.Gravity;

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
	private int x = 200;
	private int y = 90;
	private Gravity gravity;
	
	
	Hero(Texture texture, int HP, int hitPoint, int stamina, int defensePoint, float worldGravity, int width, int height, int[][] worldMap) {
		super(texture, width, height);
		this.maxHP 			= HP;
		this.HP 			= HP;
		this.hitPoint 		= hitPoint;
		this.stamina 		= stamina;
		this.maxStamina		= stamina;
		this.defensePoint 	= defensePoint;
		this.gravity		= new Gravity(worldGravity, worldMap);
		this.setPosition(x, y);
	}
	
	public void jump() {
		this.gravity.jump(15);
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
	
	public void moveVertical(int yDistance) {
		this.y += yDistance;
	}
	
	
	public void receivedDamage(int hit) {
		this.hitPointReceived = hit;
		this.statusChange("takingHit");
	}
	
	public void statusChange(String status) {
		this.statusTime = 20;
		this.status = status;
		
	}
	
	public abstract int executeAttack(int attackNum);
	public abstract Texture getTextureByState();
	

	public void update(int[][]map) {
		
		
		if(this.status == "dying") {
			this.dying();
			
		}else {
			
			if(this.HP <= 0) {
				this.statusChange("dying");
			}else {
				
				this.behavior();
				
				int movY = this.gravity.updateVector(this.x, this.y);

				this.moveVertical(movY);
				
				//verificando se o heroi caiu em poco e morreu.
				if(this.y < -50) {
					this.statusChange("dying"); //Entra em morte
				}
				
			}
			
			
		}
		
		
		this.setTexture(this.getTextureByState());
	}

	
	private void dying() {
		// processo de morte
		if(this.statusTime-- < 0) {
			this.statusChange("dead");
			
		}
		this.moveVertical(5); //fantasmo subindo aos poucos
		
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
	
	public String getStatus(){
		return status;
	}

	/**
	 * Retorna se é possivel gastar stamina, returna false avisando que não é possivel gastar.
	 * @param quant
	 * @return
	 */
	public boolean spendStamina(int quant) {
		if(this.stamina > quant) {
			this.stamina -= quant;
//			System.out.println(this.stamina);
			return true;
		}
		return false;
	}
	
	public void reset() {
		this.HP = this.maxHP;
		this.stamina = this.maxStamina;
		this.x = 200;
		this.y = 90;
	}
	
	public void restoreHP(int quant) {
		this.HP += quant;
	}
	
	public void restoreStamina(int quant) {
		this.stamina += quant;
	}
}
