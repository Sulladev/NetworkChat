package ru.gb.jtwo.lesson_2;

public class Minotaur implements Human, Bull {
    @Override
    public void voice() {
        System.out.println("aaaarrrrghhhh");
    }

    @Override
    public void walk() {
        Bull.super.walk();
        System.out.println("and sing a song");
    }

    @Override
    public void think() {
        System.out.println("invent new riddles");
    }

    @Override
    public void breathe() {

    }

    @Override
    public void look() {

    }
}
