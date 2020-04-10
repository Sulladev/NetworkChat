package ru.gb.jtwo.lesson_2.engines.common;

import java.awt.*;

// двумерные объекты в графике называются Sprite. Создаём абстракцию которая где-то находится и имеет какие-то размеры
public abstract class Sprite implements GameObject {
    protected float x;
    protected float y;
    protected float halfWidth;
    protected float halfHeight;

    protected float getLeft() {

        return x - halfWidth;
    }
    protected void setLeft(float left) {

        x = left + halfWidth;
    }
    protected float getRight() {
        return x + halfWidth;
    }
    protected void setRight(float right) {
        x = right - halfWidth;
    }
    protected float getTop() {

        return y - halfHeight;
    }
    protected void setTop(float top) {
        y = top + halfHeight;
    }
    protected float getBottom() {
        return y + halfHeight;
    }
    protected void setBottom(float bottom) {
        y = bottom - halfHeight;
    }
    protected float getWidth() {
        return 2f * halfWidth;
    }
    protected float getHeight() {
        return 2f * halfHeight;
    }

    public void update (GameCanvas canvas, float deltaTime) {}
    public void render(GameCanvas canvas, Graphics g){}
}
