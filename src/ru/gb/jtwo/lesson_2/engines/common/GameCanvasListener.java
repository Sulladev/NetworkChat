package ru.gb.jtwo.lesson_2.engines.common;

import java.awt.*;

public interface GameCanvasListener {
    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime);
}
