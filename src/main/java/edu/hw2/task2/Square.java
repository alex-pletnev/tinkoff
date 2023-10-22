package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int width, int height) {
        super(width, height);
    }

    public Square() {
    }

    @Override
    public Rectangle setWidth(int width) {
        if (width != getHeight()) {
            return new Rectangle(width, getHeight());
        }
        return new Square(width, getHeight());
    }

    @Override
    public Rectangle setHeight(int height) {
        if (height != getWidth()) {
            return new Rectangle(getWidth(), height);
        }
        return new Square(getWidth(), height);
    }
}

