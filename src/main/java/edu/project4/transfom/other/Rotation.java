package edu.project4.transfom.other;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;

public class Rotation implements Transformation {

    private final double theta;

    public Rotation(double theta) {
        this.theta = theta;
    }

    @Override public Point apply(Point point) {
        double x = point.getX();
        double y = point.getY();
        double cos = Math.cos(theta);
        double sin = Math.sin(theta);
        return new Point(x * cos - y * sin, x * sin + y * cos);
    }
}
