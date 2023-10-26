package edu.hw2.task2;

public class Square extends Rectangle {
    public Square(int size) {
        super(size, size);
    }

    public Square() {
    }

    @Override
    public Rectangle setWidth(int width) {
        if (width != getHeight()) {
            return new Rectangle(width, getHeight());
        }
        return new Square(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        if (height != getWidth()) {
            return new Rectangle(getWidth(), height);
        }
        return new Square(height);
    }
}

