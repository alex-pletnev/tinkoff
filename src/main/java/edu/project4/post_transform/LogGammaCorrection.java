package edu.project4.post_transform;

import edu.project4.entity.FractalImage;
import edu.project4.entity.Pixel;
import java.util.HashMap;
import java.util.Map;

public class LogGammaCorrection implements ImageProcessor {

    private final double gamma;

    public LogGammaCorrection(double gamma) {
        this.gamma = gamma;
    }

    @Override
    public void process(FractalImage image) {
        double max = Double.MIN_VALUE;
        Map<Pixel, Double> pixelMap = new HashMap<>();

        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                var pixel = image.pixel(i, j);
                var hitCount = pixel.getHitCount().get();
                if (hitCount == 0) {
                    continue;
                }
                var logVal = Math.log10(hitCount);
                pixelMap.put(pixel, logVal);
                max = Math.max(max, logVal);
            }
        }
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                var pixel = image.pixel(i, j);
                var normalisedPixel = pixelMap.getOrDefault(pixel, 0.0) / max;
                pixel.transformColor(
                    color -> (int) (color * Math.pow(normalisedPixel, (1.0 / gamma)))
                );
            }
        }
    }
}
