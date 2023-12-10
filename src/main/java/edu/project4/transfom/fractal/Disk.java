package edu.project4.transfom.fractal;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;

public class Disk implements Transformation {

    @Override
    public Point apply(Point point) {
        double x = point.getX();
        double y = point.getY();
        double pi = Math.PI;
        double cof = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        double arctan = Math.atan(y / x);
        return new Point(1 / pi * arctan * Math.sin(pi * cof), 1 / pi * arctan * Math.cos(pi * cof));
    }
}
