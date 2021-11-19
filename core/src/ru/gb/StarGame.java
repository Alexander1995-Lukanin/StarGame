package ru.gb;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region;

	int x, y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		region = new TextureRegion(img, 30, 40, 100, 150);
	}

	@Override
	public void render () {
		x+=1; y+=1;
		ScreenUtils.clear(Color.BROWN);
		batch.begin();
		batch.draw(img, x, y);
		batch.setColor(0.45f, 0.21f, 0.67f, 0.9f);
		batch.draw(img, 120, 300, 100, 100);
		batch.setColor(1f, 1f, 1f, 1);
		batch.draw(region, 250, 400, 100, 150);

//      Текущие размеры экрана
		Gdx.graphics.getHeight();
		Gdx.graphics.getWidth();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
