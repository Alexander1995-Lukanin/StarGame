package ru.gb.sprite.impl;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.gb.math.Rect;
import ru.gb.pool.impl.BulletPool;
import ru.gb.sprite.Sprite;

public class MainShip extends Sprite {
    private static final float HEIGHT = 0.15f;
    private static final float BOTTOM_MARGIN = 0.05f;
    private static final int INVALID_POINTER = -1;
    private static final float RELOUD_INTERVAL = 0.3f;
    private final Vector2 v;
    private final Vector2 v0;
    private Rect worldBounds;
    private boolean pressedLeft;
    private boolean pressedRight;
    private float reloadTimer;
    private final float  reloadInterval;
    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;
    private final BulletPool bulletPool;
    private final TextureRegion bulletRegion;
    private final Vector2 bulletV;
    private final float bulletHeight;
    private final int damage;
    private final Sound bulletSound;


    public MainShip(TextureAtlas atlas, BulletPool bulletPool, Sound bulletSound) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.v = new Vector2();
        this.v0 = new Vector2(0.5f, 0f);
        this.bulletSound =bulletSound;
        this.bulletPool=bulletPool;
        this.bulletRegion=atlas.findRegion("bulletMainShip");
        this.bulletV=new Vector2(0,0.5f);
        this.bulletHeight=0.01f;
        this.damage=1;

        this.reloadInterval =  RELOUD_INTERVAL;
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setHeightProportion(HEIGHT);
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        reloadTimer+=delta;
        if(reloadTimer>reloadInterval){
            reloadTimer=0f;
            shoot();
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer, int button) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {

            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer, int button) {
      if (pointer==leftPointer){
          leftPointer=INVALID_POINTER;
          if (rightPointer != INVALID_POINTER){
              moveRight();
          }
          else {
              stop();
          }
      } else if (pointer==rightPointer) {
          rightPointer=INVALID_POINTER;
          if (leftPointer!=INVALID_POINTER) {
              moveLeft();
          }
          else {
              stop();
          }
      }

        return false;
    }

    public boolean keyDown(int keycode) {
 switch (keycode){
     case Input.Keys.A:
     case Input.Keys.LEFT:
         pressedLeft = true;
         moveLeft();
         break;
     case Input.Keys.D:
     case Input.Keys.RIGHT:
         pressedRight = true;
         moveRight();
         break;
 }
        return false;
    }


    public boolean keyUp(int keycode) {
        switch (keycode){
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if(pressedRight){
                    moveRight();
                }
                else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if(pressedLeft){
                    moveLeft();
                }
                else {
                    stop();
                }
                break;
            case  Input.Keys.SPACE:
                shoot();
                break;
        }
        return false;
    }
    private void moveRight(){
        v.set(v0);
    }
    private void moveLeft(){
        v.set(v0).rotateDeg(180);
    }
    private void stop(){
        v.setZero();
    }
    private void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this,bulletRegion,pos,bulletV,bulletHeight,worldBounds,damage);
        bulletSound.play();
    }

}
