package br.edu.ufabc.gameover.frames;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;

import br.edu.ufabc.gameover.core.InputMouse;

public class CreditScreen extends MyScreen{

	private Texture background;  
	private SpriteBatch spriteBatch;
	private Matrix4 viewMatrix;
	private Matrix4 tranMatrix;
	private BitmapFont bitmapFont;
	private Music music;
	
	public CreditScreen(String idScreen) {
		super(idScreen);
		
		background = new Texture("background.jpg");
		spriteBatch = new SpriteBatch();
		viewMatrix = new Matrix4();
		tranMatrix = new Matrix4();
		bitmapFont = new BitmapFont(Gdx.files.internal("fonts/interface.fnt"));
		music	= Gdx.audio.newMusic(Gdx.files.internal("sounds/credits.mp3"));
		music.setLooping(true);
		music.play();
	}

	@Override
	public void update(float delta) {
		
		Gdx.input.setInputProcessor(new InputMouse() {
			 @Override
          public boolean touchDown(int x, int y, int pointer, int button) {
              setDone(true);
              music.stop();
              return true;
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
		bitmapFont.draw(spriteBatch, "CREDITOS", 100, 500);
		
		bitmapFont.draw(spriteBatch, "VITOR BAGGI - 21014714", 100, 400);
		bitmapFont.draw(spriteBatch, "DANIEL MONTENEGRO - 11086616", 100, 200);
		
		
		bitmapFont.draw(spriteBatch, "Clique na tecla para voltar ao menu.", 100, 50);
		spriteBatch.end();
	}

}
