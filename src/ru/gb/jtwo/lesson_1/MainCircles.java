package ru.gb.jtwo.lesson_1;

import javax.swing.*;
import java.awt.*;

public class MainCircles extends JFrame {
    private static final int POZ_X = 400;
    private static final int POZ_Y = 200;
    private static final  int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    Sprite [] sprites = new Sprite[10];
//    Background canvasBackground  = new Background();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POZ_X,POZ_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas,BorderLayout.CENTER);
        Background background = new Background(this);
        add(background,BorderLayout.CENTER);
        setTitle("Circles");
        initApplication();
//        initCanvasBackground();
        setVisible(true);
    }

    private void initApplication(){
        for (int i = 0; i <sprites.length ; i++) {
            sprites[i] = new Ball();
        }
    }

//    private void initCanvasBackground(){
//            canvasBackground = new Background();
//    }

    //готовы принимать инфу от канвы. метод выполняется когда канва перерисовалась
    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime){
        //необходимо обновление и отрисовка
        update(canvas,deltaTime);
        render(canvas,g);
//        updateBackground(canvas,deltaTime);
//        renderBackground(canvas,g);
    }


    //апдейтим, рендерим шарики
    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i <sprites.length ; i++) {
            sprites[i].update(canvas,deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g){
        for (int i = 0; i <sprites.length ; i++) {
            sprites[i].render(canvas,g);
        }
    }


//    private void updateBackground(GameCanvas canvas, float deltaTime) {
//            canvasBackground.update(canvas,deltaTime);
//    }
//
//    private void renderBackground(GameCanvas canvas, Graphics g){
//            canvasBackground.render(canvas,g);
//    }

}
