package edu.project4.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Color {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final int COLOR_MAX_VALUE = 256;
    private final int red;
    private final int green;
    private final int blue;

    public Color() {
        this.red = RANDOM.nextInt(COLOR_MAX_VALUE);
        this.green = RANDOM.nextInt(COLOR_MAX_VALUE);
        this.blue = RANDOM.nextInt(COLOR_MAX_VALUE);
    }

    public static List<Color> createColorArray(int samples) {
        List<Color> colorArray = new ArrayList<>();
        for (int i = 0; i < samples; i++) {
            colorArray.add(new Color());
        }
        return colorArray;
    }

    public int getRed() {
        return red;
    }

    public int getGreen() {
        return green;
    }

    public int getBlue() {
        return blue;
    }
}
