package edu.project4.transfom.fractal;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;

public class Sinusoidal implements Transformation {

    @Override
    public Point apply(Point point) {
        return new Point(Math.sin(point.getX()), Math.sin(point.getY()));
    }
}
