package edu.hw10.task1.generator;

import edu.hw10.task1.anotation.Max;
import edu.hw10.task1.anotation.Min;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntGenerator implements Generator<Integer> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    @Override
    public Integer generate(Annotation[] annotations) {
        Integer min = null;
        Integer max = null;
        for (var annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            } else {
                LOGGER.info("{} is not supported", annotation.getClass().getName());
            }
        }

        if (!Objects.isNull(min) && !Objects.isNull(max)) {
            return RANDOM.nextInt(min, max);
        } else if (!Objects.isNull(min)) {
            return RANDOM.nextInt(min, Integer.MAX_VALUE);
        } else if (!Objects.isNull(max)) {
            return RANDOM.nextInt(max);
        } else {
            return RANDOM.nextInt();
        }
    }
}
