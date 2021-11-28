package ru.gb.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.screen.BaseScreen;
import ru.gb.sprite.impl.Background;

public class MenuScreen extends BaseScreen {
    Texture imgSpaceShip;
    Texture imgBackgraund;
    private  Vector2 pos;
    private Background background;
    @Override
    public void show() {
        super.show();
        imgBackgraund = new Texture( "backgraund.jpg");
        imgSpaceShip= new Texture( "SpaceShip.png");
        pos=new Vector2();
        background = new Background(imgBackgraund);


    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        background.draw(batch);
        batch.draw(imgSpaceShip,pos.x, pos.y, 0.5f, 0.5f);
        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
        imgSpaceShip.dispose();
        imgBackgraund.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        pos.set(touch);
        return super.touchDown(touch, pointer, button);
    }
}
