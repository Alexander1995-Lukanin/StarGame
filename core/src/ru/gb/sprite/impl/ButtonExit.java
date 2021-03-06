package ru.gb.sprite.impl;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.gb.math.Rect;
import ru.gb.sprite.BaseButton;

public class ButtonExit extends BaseButton {

    private static final float HEIGHT = 0.08f;
    private static final float MARGIN = 0.08f;

    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("Exit Button"));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(HEIGHT);
        setRight(worldBounds.getRight() - MARGIN);
        setBottom(worldBounds.getBottom() + MARGIN);
    }

    @Override
    public void action() {
        Gdx.app.exit();
    }
}
