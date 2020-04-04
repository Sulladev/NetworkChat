package ru.gb.jtwo.lesson_1;

import javax.swing.*;
import java.awt.*;

public class GameCanvas extends JPanel {
    // при создании панели указываем класс, который будет ее слушать, реагировать на её события
    MainCircles listener;
    long lastFrameTime;

    public GameCanvas(MainCircles listener) {
        this.listener = listener;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 60 frames per second
        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        listener.onCanvasRepainted(this, g, deltaTime);
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    // простые геттеры котоорые вернут нам границы панели. Расскажут какого размера панель
    public int getLeft() { return 0;}
    public int getRight() {return  getWidth()-1;}
    public int getTop() { return  0;}
    public int getBottom() {return  getHeight()-1;}
}
