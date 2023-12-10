package edu.project4.transfom.other;

import edu.project4.entity.Point;
import edu.project4.transfom.Transformation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class AffineTransformation implements Transformation {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private final double a;
    private final double b;
    private final double c;
    private final double d;
    private final double e;
    private final double f;

    public static AffineTransformation create() {
        AffineTransformation affineTransformation;
        do {
            affineTransformation = getRandomAffineTransformation();
        } while (!validate(affineTransformation));
        return affineTransformation;
    }

    public static List<AffineTransformation> createAffineTransformations(int samples) {
        List<AffineTransformation> affineTransformations = new ArrayList<>();
        for (int i = 0; i < samples; i++) {
            affineTransformations.add(create());
        }
        return affineTransformations;
    }

    public AffineTransformation(double a, double b, double c, double d, double e, double f) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
    }

    private static AffineTransformation getRandomAffineTransformation() {
        double a = RANDOM.nextDouble(-1, 1);
        double b = RANDOM.nextDouble(-1, 1);
        double c = RANDOM.nextDouble(-1, 1);
        double d = RANDOM.nextDouble(-1, 1);
        double e = RANDOM.nextDouble(-1, 1);
        double f = RANDOM.nextDouble(-1, 1);
        return new AffineTransformation(a, b, c, d, e, f);
    }

    @Override
    public Point apply(Point point) {
        return translation(linearTransformation(point));
    }

    public Point linearTransformation(Point point) {
        Point p1 = new Point(a, b);
        Point p2 = new Point(c, d);
        return new Point(scalarProduct(point, p1), scalarProduct(point, p2));
    }

    public double scalarProduct(Point point1, Point point2) {
        return point1.getX() * point2.getX() + point1.getY() * point2.getY();
    }

    public Point translation(Point point) {
        Point p1 = new Point(e, f);
        return new Point(point.getX() + p1.getX(), point.getY() + p1.getY());
    }

    public static boolean validate(AffineTransformation t) {
        return (Math.pow(t.a, 2) + Math.pow(t.d, 2) < 1)
            && (Math.pow(t.b, 2) + Math.pow(t.e, 2) < 1)
            && (Math.pow(t.a, 2) + Math.pow(t.b, 2)
            + Math.pow(t.d, 2) + Math.pow(t.e, 2)
            < 1 + Math.pow(t.a * t.e - t.b * t.d, 2));
    }

}
