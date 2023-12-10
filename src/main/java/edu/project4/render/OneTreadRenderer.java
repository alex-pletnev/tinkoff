package edu.project4.render;

import edu.project4.entity.Color;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rect;
import edu.project4.transfom.Transformation;
import edu.project4.transfom.other.AffineTransformation;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OneTreadRenderer implements Renderer {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect rect,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        int symmetry
    ) {
        var affineTransformations = AffineTransformation.createAffineTransformations(samples);
        var colors = Color.createColorArray(samples);

        var startTime = System.currentTimeMillis();
        IterationParams iterationParams = new IterationParams(
            canvas,
            rect,
            transformations,
            iterPerSample,
            symmetry,
            affineTransformations,
            colors,
            null
        );
        for (int i = 0; i < samples; i++) {
            RenderUtil.iteration(iterationParams);
        }
        var finishTime = System.currentTimeMillis();
        LOGGER.info("OneTreadRenderer finished. Time: {} ms", finishTime - startTime);

        return canvas;
    }
}
