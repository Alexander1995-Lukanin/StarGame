package ru.gb.screen.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.gb.screen.BaseScreen;

public class MenuScreen extends BaseScreen {
    Texture imgSpaceShip;
    Texture imgBackgraund;
    private Vector2 touch;
    private  Vector2 v;
    private  Vector2 pos;
    private final float V_LEN =20f;
    @Override
    public void show() {
        super.show();
        imgBackgraund = new Texture( "backgraund.jpg");
        imgSpaceShip= new Texture( "SpaceShip.png");
        touch = new Vector2();
        pos=new Vector2();
        v=new Vector2();


    }

    @Override
    public void render(float delta) {
        super.render(delta);
        batch.begin();
        batch.draw(imgBackgraund, 0, 0, Gdx.graphics.getWidth(),  Gdx.graphics.getHeight());
        batch.draw(imgSpaceShip,pos.x, pos.y, 100, 100);
        batch.end();
        if (touch.dst(pos)>=V_LEN){
            pos.add(v);
        }
        else {
            pos.set (touch);
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        imgSpaceShip.dispose();
        imgBackgraund.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set (screenX,Gdx.graphics.getHeight()-screenY);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
