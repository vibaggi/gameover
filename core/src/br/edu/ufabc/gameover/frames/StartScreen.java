package br.edu.ufabc.gameover.frames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.gameover.core.InputMouse;

public class StartScreen extends MyScreen{

	private Texture background;  
	private SpriteBatch spriteBatch;
	private Matrix4 viewMatrix;
	private Matrix4 tranMatrix;
	private BitmapFont bitmapFont;
	private Music music;
	
	private boolean selectHe = true;
	
	public StartScreen(String idScreen) {
		super(idScreen);
		background = new Texture("startScreen.jpg");
		spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/interface.fnt"));
		music	= Gdx.audio.newMusic(Gdx.files.internal("sounds/introScreen.mp3"));
		music.setLooping(true);
		music.play();
	}

	@Override
	public void update(float delta) {
		
		//Adicionando clique no menu iniciar
		Gdx.input.setInputProcessor(new InputMouse() {
			 @Override
           public boolean touchDown(int x, int y, int pointer, int button) {
               if (button == Input.Buttons.LEFT) {
               	//verificando posicoes de icones
               	if(x > 150 && x < 300 && y > 200 && y < 350) selectHe = true;
               	else if(x > 350 && x < 500 && y > 200 && y < 350) selectHe = false;
               	else if(x > 300 && x < 500 && y > 400 && y < 450) setDone(true);
                return true;
               }
               return false;
           }
		});
		
	}

	@Override
	public void draw(float delta) {
		viewMatrix.setToOrtho2D(0, 0, 800, 600); // defino a "configuracao da resolucao"
		spriteBatch.setProjectionMatrix(viewMatrix); // buffer irao seguir essa configuracao
		spriteBatch.setTransformMatrix(tranMatrix); // toda vez q redimensionar a tela, armazene as distor�oes na matriz

		spriteBatch.begin();
		spriteBatch.draw(background, 0, 0, 800, 600, 0, 0, 800, 600, false, false);
		bitmapFont.draw(spriteBatch, "SELECIONE UM PERSONAGEM", 100, 500);
		
		if(selectHe) {
			spriteBatch.draw(new Texture("icons/selectHE.png"), 200, 200);
			spriteBatch.draw(new Texture("icons/unselectSHE.png"), 450, 200);
		}else {
			spriteBatch.draw(new Texture("icons/unselectHE.png"), 200, 200);
			spriteBatch.draw(new Texture("icons/selectSHE.png"), 450, 200);
		}
		
		bitmapFont.draw(spriteBatch, "COMECAR", 350, 100);
		
		spriteBatch.end();
		
	}
	
	public boolean getSelectHe() {
		return selectHe;
	}

}
