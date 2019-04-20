package br.edu.ufabc.gameover.physics.attack;

import com.badlogic.gdx.graphics.Texture;

import br.edu.ufabc.gameover.models.GameObject;
import br.edu.ufabc.gameover.models.Hero;

public class RootsBossAttack extends ProjetilAttack{

	public RootsBossAttack(int targerX, int targetY, String creator) {
		super(new Texture("projeteis/roots1.png"),targerX-100, 200, 89, 0, 20, 100, creator);
		//Inicialmente é criado uma area embaixo do heroi que não o atinge. Ela dura metade da duration
		//Em sequencia, apos 100 ciclos as raizes saem do chao em causam dano
	}

	@Override
	public void behavior() {
		
		//Metade do tempo de duração as raizes aparecem baixas
		//Assim que passa metade do tempo elas saem com tudo e causam dano.
		if(this.getDuration() < 20) {
			this.y2 = 120;
			this.setTexture(new Texture("projeteis/roots2.png"));
		}
		
	}

}
