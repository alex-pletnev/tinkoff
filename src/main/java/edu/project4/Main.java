package edu.project4;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Rect;
import edu.project4.file.ImageFormat;
import edu.project4.file.ImageUtils;
import edu.project4.post_transform.ImageProcessor;
import edu.project4.post_transform.LogGammaCorrection;
import edu.project4.render.MultiThreadRender;
import edu.project4.render.OneTreadRenderer;
import edu.project4.render.Renderer;
import edu.project4.transfom.fractal.Disk;
import edu.project4.transfom.fractal.Heart;
import edu.project4.transfom.fractal.Polar;
import edu.project4.transfom.fractal.Sinusoidal;
import edu.project4.transfom.fractal.Spherical;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final int WIDTH = 1920;
    private static final int HEIGHT = 1080;
    private static final int X = -4;
    private static final int Y = -3;
    private static final int RECT_WIDTH = 8;
    private static final int RECT_HEIGHT = 6;
    private static final int SAMPLES = 50;
    private static final int ITER_PER_SAMPLE = 100000;
    private static final int SYMMETRY = 1;
    private static final double GAMMA = 2.2;

    private Main() {
    }

    public static void main(String[] args) throws InterruptedException {
        ImageProcessor logGammaCorrection = new LogGammaCorrection(GAMMA);
        FractalImage fractalImageOT = FractalImage.create(WIDTH, HEIGHT);
        Rect rectOT = new Rect(X, Y, RECT_WIDTH, RECT_HEIGHT);
        Renderer oneTreadRenderer = new OneTreadRenderer();
        oneTreadRenderer.render(
            fractalImageOT,
            rectOT,
            List.of(
                new Sinusoidal(),
                new Disk(),
                new Spherical(),
                new Polar()
            ),
            SAMPLES,
            ITER_PER_SAMPLE,
            SYMMETRY
            );
        logGammaCorrection.process(fractalImageOT);
        try {
            ImageUtils.save(fractalImageOT, Path.of("mainOT.png"), ImageFormat.PNG);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        FractalImage fractalImageMT = FractalImage.create(WIDTH, HEIGHT);
        Rect rectMT = new Rect(X, Y, RECT_WIDTH, RECT_HEIGHT);
        Renderer multiTreadRenderer = new MultiThreadRender();
        multiTreadRenderer.render(
            fractalImageMT,
            rectMT,
            List.of(
                new Sinusoidal(),
                new Heart()
            ),
            SAMPLES,
            ITER_PER_SAMPLE,
            SYMMETRY
        );
        logGammaCorrection.process(fractalImageMT);
        try {
            ImageUtils.save(fractalImageMT, Path.of("mainMT.png"), ImageFormat.PNG);
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }
}
