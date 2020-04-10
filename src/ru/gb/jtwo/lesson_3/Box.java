package ru.gb.jtwo.lesson_3;

import java.util.Objects;

public class Box implements Comparable<Box>{
    int width;
    int height;

    public Box(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return String.format("Box(%d, %d)", width, height);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Box)) return false;
        Box b = (Box) obj;
        return this.width == b.width && this.height == b.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    private int square() {
        return width * height;
    }

    @Override
    public int compareTo(Box o) {
        return (o == this) ? 0 : square() - o.square();
    }
}
