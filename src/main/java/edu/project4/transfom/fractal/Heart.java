package edu.project4.transfom.fractal;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;

public class Heart implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.getX();
        double y = point.getY();
        double cof = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double arctan = Math.atan(y / x);
        return new Point(cof * Math.sin(cof * arctan), -cof * Math.cos(cof * arctan));
    }
}
