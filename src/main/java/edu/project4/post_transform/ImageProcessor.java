package edu.project4.post_transform;

import edu.project4.entity.FractalImage;

@FunctionalInterface
public interface ImageProcessor {

    void process(FractalImage image);
}
