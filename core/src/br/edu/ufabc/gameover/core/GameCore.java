package br.edu.ufabc.gameover.core;

import com.badlogic.gdx.utils.Array;

import br.edu.ufabc.gameover.models.GameObject;

public class GameCore {
	
	/*
	 * Classe responsavel por fazer o update de todos os personagens no jogo
	 * e também de projeteis e objetos do cenário. A renderização dos objetos ficam
	 * com o Render.
	 * */
	
	protected Array<GameObject> objects;
	
	public GameCore() {
		
	}

}
