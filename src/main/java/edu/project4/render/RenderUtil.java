package edu.project4.render;

import edu.project4.entity.Color;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import edu.project4.entity.Point;
import edu.project4.entity.Rect;
import edu.project4.transfom.Transformation;
import edu.project4.transfom.other.AffineTransformation;
import edu.project4.transfom.other.Rotation;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

public class RenderUtil {
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final int START = -20;

    private RenderUtil() {
    }

    public static void iteration(IterationParams iterationParams) {
        FractalImage canvas = iterationParams.canvas();
        Rect rect = iterationParams.rect();
        List<Transformation> transformations = iterationParams.transformations();
        int iter = iterationParams.iter();
        int symmetry = iterationParams.symmetry();
        List<AffineTransformation> affineTransformations = iterationParams.affineTransformations();
        List<Color> colors = iterationParams.colors();
        CountDownLatch countDownLatch = iterationParams.countDownLatch();

        Point pw = rect.createRandomPointInRect();
        for (int i = START; i < iter; i++) {

            var transformation = transformations.get(RANDOM.nextInt(transformations.size()));
            int tmp = RANDOM.nextInt(Math.min(affineTransformations.size(), colors.size()));
            var affineTransformation = affineTransformations.get(tmp);
            var color = colors.get(tmp);

            pw = affineTransformation.apply(pw);
            pw = transformation.apply(pw);
            if (i >= 0) {

                subIteration(canvas, rect, symmetry, pw, color);
                if (!Objects.isNull(countDownLatch)) {
                    countDownLatch.countDown();
                }
            }

        }
    }

    private static void subIteration(
        FractalImage canvas,
        Rect rect,
        int symmetry,
        Point pw,
        Color color
    ) {
        double theta2 = 0.0;
        for (int s = 0; s < symmetry; theta2 += Math.PI * 2 / symmetry, ++s) {
            Rotation rotation = new Rotation(theta2);
            var pwr = rotation.apply(pw);
            if (!rect.contains(pwr)) {
                continue;
            }
            Pixel pixel = canvas.pixel(
                (int) ((pwr.getX() - rect.x()) * canvas.getWidth() / rect.width()),
                (int) ((pwr.getY() - rect.y()) * canvas.getHeight() / rect.height())
            );
            if (!Objects.isNull(pixel)) {
                pixel.setColor(color);
                pixel.incrementHitCount();
            }
        }
    }

}
