package edu.project4.file;

import edu.project4.entity.FractalImage;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import javax.imageio.ImageIO;

public class ImageUtils {

    private ImageUtils() {
    }

    public static void save(FractalImage image, Path filename, ImageFormat format) throws IOException {
        BufferedImage bufferedImage =
            new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                var pixel = image.pixel(i, j);
                Color color = new Color(
                    pixel.getRed(),
                    pixel.getGreen(),
                    pixel.getBlue()
                );
                bufferedImage.setRGB(i, j, color.getRGB());
            }
        }
        ImageIO.write(bufferedImage, format.name(), filename.toFile());
    }
}
