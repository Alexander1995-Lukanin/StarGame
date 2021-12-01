package ru.gb.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.screen.BaseScreen;
import ru.gb.sprite.impl.Background;
import ru.gb.sprite.impl.Spaceship;

public class MenuScreen extends BaseScreen {
    Texture imgSpaceShip;
    Texture imgBackgraund;
    private  Vector2 pos;
    private Background background;
    private Spaceship spaceship;
    @Override
    public void show() {
        super.show();
        imgBackgraund = new Texture( "backgraund.jpg");
        imgSpaceShip= new Texture( "SpaceShip.png");
        background = new Background(imgBackgraund);
        spaceship=new Spaceship(imgSpaceShip);


    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        spaceship.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        drow();


    }

    @Override
    public void dispose() {
        super.dispose();
        imgSpaceShip.dispose();
        imgBackgraund.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        spaceship.touchDown(touch,pointer,button);
        return false;
    }
    private void update( float delta) {
     spaceship.update(delta);
    }
    private void drow (){
        batch.begin();
        background.draw(batch);
        spaceship.draw(batch);
        batch.end();

    }

}
