package ru.gb.jtwo.lesson_2.engines.common;

import java.awt.*;

public interface GameObject {

    void update (GameCanvas canvas, float deltaTime);

    void render(GameCanvas canvas, Graphics g);
}
