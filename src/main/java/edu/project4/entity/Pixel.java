package edu.project4.entity;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntUnaryOperator;

public class Pixel {
    private int red;
    private int green;
    private int blue;
    private final AtomicInteger hitCount;

    public Pixel(int red, int green, int blue, AtomicInteger hitCount) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.hitCount = hitCount;
    }

    public Pixel() {
        this(0, 0, 0, new AtomicInteger(0));
    }

    public synchronized void setColor(Color color) {
        if (hitCount.get() == 0) {
            red = color.getRed();
            green = color.getGreen();
            blue = color.getBlue();
        } else {
            red = (red + color.getRed()) / 2;
            green = (green + color.getGreen()) / 2;
            blue = (blue + color.getBlue()) / 2;
        }
    }

    public synchronized void transformColor(IntUnaryOperator transformFunction) {
        red = transformFunction.applyAsInt(red);
        green = transformFunction.applyAsInt(green);
        blue = transformFunction.applyAsInt(blue);
    }

    public synchronized int getRed() {
        return red;
    }

    public synchronized int getGreen() {
        return green;
    }

    public synchronized int getBlue() {
        return blue;
    }

    public AtomicInteger getHitCount() {
        return hitCount;
    }

    public void incrementHitCount() {
        hitCount.incrementAndGet();
    }
}
