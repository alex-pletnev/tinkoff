package edu.project4.transfom.fractal;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;

public class Spherical implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.getX();
        double y = point.getY();
        double cof = Math.pow(x, 2) + Math.pow(y, 2);
        return new Point(x / cof, y / cof);
    }
}
