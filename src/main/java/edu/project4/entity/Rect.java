package edu.project4.entity;

import java.util.concurrent.ThreadLocalRandom;

public record Rect(double x, double y, int width, int height) {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    public synchronized boolean contains(Point p) {
        return p.getX() >= this.x && p.getX() <= this.x + this.width
            && p.getY() >= this.y && p.getY() <= this.y + this.height;
    }

    public synchronized Point createRandomPointInRect() {
        return new Point(x + RANDOM.nextDouble() * width, y + RANDOM.nextDouble() * height);
    }
}
