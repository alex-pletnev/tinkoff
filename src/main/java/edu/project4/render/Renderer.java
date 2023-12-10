package edu.project4.render;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Rect;
import edu.project4.transfom.Transformation;
import java.util.List;


public interface Renderer {

    FractalImage render(
        FractalImage canvas,
        Rect rect,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        int symmetry
    ) throws InterruptedException;
}
