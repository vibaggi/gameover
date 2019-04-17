package br.edu.ufabc.gameover.physics.environment;

public class Gravity {
	
	//Gravidade Individual, adicionar nos objetos que devam respeitar determinada gravidade.
	
	private float verticalVector; //indica qual a direção vertical de o objeto se desloca (sobe ou cai).
	private float worldGravity; //Força que puxa pra baixo
	private int [][] ground;
	
	public Gravity(float worldGravity, int[][] ground) {
		this.verticalVector = 0;
		this.worldGravity = worldGravity;
		this.ground = ground;
	}
	
	/**
	 * Chame esse método do update do objeto
	 * @params yPos em que 
	 * @return
	 */
	public int updateVector(int xPos, int yPos) {
		
		//Procura se o objeto irá se espaficar no chão logo em seguida.
		//Caso positivo retorna o espaco que falta para o chão
		//Caso já esteja encostado no chão o vetor será 0 (O objeto não sai do lugar)
		
		for (int i = 0; i < ground.length; i++) {
//			System.out.println(ground[i][0]+" "+yPos+" "+verticalVector);
			if(verticalVector <= 0 && (ground[i][0] <= yPos) && (ground[i][0] >= (yPos + verticalVector)) && (xPos > ground[i][1] && xPos < ground[i][2]) ) {
				System.out.println(ground[i][0]-yPos);
				verticalVector = 0; //Encotrou o chão. O vetor gravidade é anulado.
				return (ground[i][0]-yPos);
			}
		}
		verticalVector = verticalVector - worldGravity;
		//caso negativo retorna o vetor vertical com a gravidade implementada
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
