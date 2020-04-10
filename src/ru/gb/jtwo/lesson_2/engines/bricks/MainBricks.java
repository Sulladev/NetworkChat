package ru.gb.jtwo.lesson_2.engines.bricks;

import ru.gb.jtwo.lesson_2.engines.circles.Background;
import ru.gb.jtwo.lesson_2.engines.common.GameCanvas;
import ru.gb.jtwo.lesson_2.engines.common.GameCanvasListener;
import ru.gb.jtwo.lesson_2.engines.common.GameObject;
import ru.gb.jtwo.lesson_2.engines.common.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainBricks extends JFrame implements GameCanvasListener {
    private static final int POZ_X = 400;
    private static final int POZ_Y = 200;
    private static final  int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private GameObject[] gameObjects = new GameObject[1];
    //заводим счётчик который говорит сколько действительно объектов в нашем массиве
    private int gameObjectsCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainBricks();
            }
        });
    }

    private MainBricks(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POZ_X,POZ_Y,WINDOW_WIDTH,WINDOW_HEIGHT);
        GameCanvas canvas = new GameCanvas(this);
        add(canvas,BorderLayout.CENTER);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1)
                    addGamObject(new Bricks(e.getX(), e.getY()));
                else if (e.getButton() == MouseEvent.BUTTON3)
                    removeSprite();
            }
        });
        setTitle("Bricks");
        initApplication();
        setVisible(true);
    }

    private void addGamObject(Sprite s){
        if (gameObjectsCount == gameObjects.length){
            GameObject[] temp = new GameObject[gameObjects.length * 2];
            System.arraycopy(gameObjects,0,temp,0,gameObjects.length);
            gameObjects = temp;
        }
        gameObjects[gameObjectsCount++] = s;
    }

    private void removeSprite(){
    // когда убираем элемени из массива - мы не удаляем спрайт, а отвязваем его от ссылок.
        // он начинает висеть в воздухе и попадает под сборку мусора
        // в ДАННОМ случае спрайты перестают апдейтиться и рендериться.
        if (gameObjectsCount>1) gameObjectsCount --;
    }

    private void initApplication(){
        gameObjects[0] = new Background();
        gameObjectsCount ++;
    }



    //готовы принимать инфу от канвы. метод выполняется когда канва перерисовалась
    public void onCanvasRepainted(GameCanvas canvas, Graphics g, float deltaTime){
        //необходимо обновление и отрисовка
        update(canvas,deltaTime);
        render(canvas,g);
    }


    //апдейтим, рендерим шарики
    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i <gameObjectsCount ; i++) {
            gameObjects[i].update(canvas,deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g){
        for (int i = 0; i <gameObjectsCount ; i++) {
            gameObjects[i].render(canvas,g);
        }
    }




}
