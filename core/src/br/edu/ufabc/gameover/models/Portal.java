package br.edu.ufabc.gameover.models;

import com.badlogic.gdx.graphics.Texture;

public class Portal extends ScenarioObject{

	private int x, width = 200;
	private int y, height = 260;
	private boolean isPortalOpen = false; 
	
	public Portal(int x, int y) {
		super(new Texture("portal/close.png"));
		this.x = x;
		this.y = y;
		this.setPosition(x, y);
	}

	@Override
	public void moving() {
		//Objeto Estático. Não se mexe.
		
	}
	
	/**
	 * Verifica se o personagem entrou no portal. Somente entra quando ele está aberto
	 * @param heroXPos
	 * @param heroYPos
	 * @return
	 */
	public boolean isEnterInPortal(int heroXPos, int heroYPos) {
		return isPortalOpen && (heroXPos > x && heroXPos < (x+width)) && (heroYPos > y && heroYPos < (y+height));
	}
	
	public void openPortal() {
		this.isPortalOpen = true;
		this.setTexture(new Texture("portal/open.png"));
	}

}
