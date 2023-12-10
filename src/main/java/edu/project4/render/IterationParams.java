package edu.project4.render;

import edu.project4.entity.Color;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rect;
import edu.project4.transfom.Transformation;
import edu.project4.transfom.other.AffineTransformation;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public record IterationParams(FractalImage canvas, Rect rect, List<Transformation> transformations, int iter,
                              int symmetry, List<AffineTransformation> affineTransformations, List<Color> colors,
                              CountDownLatch countDownLatch) {

}

