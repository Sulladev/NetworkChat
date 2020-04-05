package ru.gb.jtwo.lesson_1;


import java.awt.*;
import java.util.Random;

public class Background extends GameCanvas {

    public Background(MainCircles listener) {
        super(listener);
    }

    Random random = new Random();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
        repaint();
    }

//  (new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)))


    //    private final Color color = new Color (
//            (int)(Math.random() * 255),
//            (int)(Math.random() * 255),
//            (int)(Math.random() * 255)
//    );
//
//
//    public Background() {
//
//        halfWidth = 800;
//        halfHeight = 600;
//    }
//
//    @Override
//    void update(GameCanvas canvas, float deltaTime) {
//
//
//
//
//
//    }
//
//    @Override
//    void render(GameCanvas canvas, Graphics g) {
//
//        g.setColor(color);
//        g.fillRect((int) getLeft(), (int) getTop(),
//                (int) getWidth(), (int) getHeight());
//
//
//    }
}


