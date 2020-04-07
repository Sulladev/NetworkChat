package ru.gb.jtwo.lesson_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POZ_X = 400;
    private static final int POZ_Y = 200;
    private static final  int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    Sprite [] sprites = new Sprite[1];
    //заводим счётчик который говорит сколько действительно объектов в нашем массиве
    private int spritesCount;

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
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addGamObject(new Ball(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3)
                    removeSprite();
            }
        });
        setTitle("Circles");
        initApplication();
        setVisible(true);
    }

    private void addGamObject(Sprite s){
        if (spritesCount == sprites.length){
            Sprite[] temp = new Sprite[sprites.length * 2];
            System.arraycopy(sprites,0,temp,0,sprites.length);
            sprites = temp;
        }
        sprites[spritesCount++] = s;
    }

    private void removeSprite(){
    // когда убираем элемени из массива - мы не удаляем спрайт, а отвязваем его от ссылок.
        // он начинает висеть в воздухе и попадает под сборку мусора
        // в ДАННОМ случае спрайты перестают апдейтиться и рендериться.
        if (spritesCount>1) spritesCount --;
    }

    private void initApplication(){
        sprites[0] = new Background();
        spritesCount ++;
    }



    //готовы принимать инфу от канвы. метод выполняется когда канва перерисовалась
    void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime){
        //необходимо обновление и отрисовка
        update(canvas,deltaTime);
        render(canvas,g);
    }


    //апдейтим, рендерим шарики
    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i <spritesCount ; i++) {
            sprites[i].update(canvas,deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g){
        for (int i = 0; i <spritesCount ; i++) {
            sprites[i].render(canvas,g);
        }
    }




}
