package br.edu.ufabc.gameover.frames;


import com.badlogic.gdx.Screen;

public abstract class MyScreen implements Screen {


	private String idScreen;
	private boolean done;

	public MyScreen( String idScreen){
		this.idScreen = idScreen;
	}

	public abstract void update(float delta); // métodos obrigatórios par redefinição

	public abstract void draw(float delta);

	public String getIdScreen() {
		return this.idScreen;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public boolean isDone() {
		return this.done;
	}

	@Override
	public void render(float delta) { 
		update(delta);
		draw(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

}
