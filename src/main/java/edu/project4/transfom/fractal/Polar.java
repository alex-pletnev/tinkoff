package edu.project4.transfom.fractal;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;

public class Polar implements Transformation {

    @Override
    public Point apply(Point point) {
        var x = point.getX();
        var y = point.getY();
        return new Point(Math.atan(y / x) / Math.PI, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)) - 1);
    }
}
