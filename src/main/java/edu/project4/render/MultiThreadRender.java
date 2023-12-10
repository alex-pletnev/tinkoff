package edu.project4.render;

import edu.project4.entity.Color;
import edu.project4.entity.FractalImage;
import edu.project4.entity.Rect;
import edu.project4.transfom.Transformation;
import edu.project4.transfom.other.AffineTransformation;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MultiThreadRender implements Renderer {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public FractalImage render(
        FractalImage canvas,
        Rect rect,
        List<Transformation> transformations,
        int samples,
        int iterPerSample,
        int symmetry
    ) throws InterruptedException {
        var affineTransformations = AffineTransformation.createAffineTransformations(samples);
        var colors = Color.createColorArray(samples);
        try (ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            var startTime = System.currentTimeMillis();
            CountDownLatch countDownLatch = new CountDownLatch(samples);
            IterationParams iterationParams = new IterationParams(
                canvas,
                rect,
                transformations,
                iterPerSample,
                symmetry,
                affineTransformations,
                colors,
                countDownLatch
            );
            for (int i = 0; i < samples; i++) {
                executor.execute(() -> RenderUtil.iteration(iterationParams));
            }
            countDownLatch.await();
            var finishTime = System.currentTimeMillis();
            LOGGER.info("MultiThreadRender finished. Time: {} ms", finishTime - startTime);
            executor.shutdownNow();
        }

        return canvas;

    }
}
