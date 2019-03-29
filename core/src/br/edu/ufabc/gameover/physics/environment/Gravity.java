package br.edu.ufabc.gameover.physics.environment;

public class Gravity {
	
	//Gravidade Individual, adicionar nos objetos que devam respeitar determinada gravidade.
	
	private float verticalVector; //indica qual a direção vertical de o objeto se desloca (sobe ou cai).
	private float worldGravity; //Força que puxa pra baixo
	
	public Gravity(float worldGravity) {
		this.verticalVector = 0;
		this.worldGravity = worldGravity;
	}
	
	/**
	 * Chame esse método do update do objeto
	 * @return
	 */
	public int updateVector() {
		verticalVector = verticalVector - worldGravity; //- airResistence*(verticalVector*verticalVector);
		return (int)verticalVector;
		
	}
	
	/**
	 * Ao saltar incremente a velocidade inicial positiva do salto
	 * @param verticalVector velocidade do salto inicial
	 */
	public void jump(int verticalVector) {
		this.verticalVector = verticalVector;
	}
	
	

}
