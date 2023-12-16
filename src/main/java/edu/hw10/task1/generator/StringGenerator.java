package edu.hw10.task1.generator;

import edu.hw10.task1.anotation.Max;
import edu.hw10.task1.anotation.Min;
import edu.hw10.task1.anotation.NotNull;
import edu.hw10.task1.exception.InvalidMaxException;
import edu.hw10.task1.exception.InvalidMinException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class StringGenerator implements Generator<String> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int DEFAULT_STRING_LEN = 10;
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final int ALPHABET_LEN = 26;
    private static final char[] ALPHABET = new char[ALPHABET_LEN * 2];

    static {
        for (int i = 0; i < ALPHABET_LEN; i++) {
            ALPHABET[i] = (char) ('A' + i);
            ALPHABET[i + ALPHABET_LEN] = (char) ('a' + i);
        }
    }

    @Override
    public String generate(Annotation[] annotations) {
        Integer min = null;
        Integer max = null;
        boolean isNotNull = false;
        for (var annotation : annotations) {
            if (annotation instanceof Min minAnnotation) {
                min = minAnnotation.value();
            } else if (annotation instanceof Max maxAnnotation) {
                max = maxAnnotation.value();
            } else if (annotation instanceof NotNull) {
                isNotNull = true;
            } else {
                LOGGER.info("{} is not supported", annotation.getClass().getName());
            }
        }



        if (!isNotNull && RANDOM.nextBoolean()) {
            return null;
        }
        int len;

        if (!Objects.isNull(min) && !Objects.isNull(max)) {

            if (min < 0) {
                throw new InvalidMinException();
            }
            if (max < 1) {
                throw new InvalidMaxException();
            }
            len = RANDOM.nextInt(min, max);
        } else if (!Objects.isNull(min)) {
            if (min < 0) {
                throw new InvalidMinException();
            }
            len = RANDOM.nextInt(min, DEFAULT_STRING_LEN + min);
        } else if (!Objects.isNull(max)) {
            if (max < 1) {
                throw new InvalidMaxException();
            }
            len = RANDOM.nextInt(max);
        } else {
            len = DEFAULT_STRING_LEN;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(ALPHABET[RANDOM.nextInt(ALPHABET_LEN * 2)]);
        }
        return sb.toString();
    }

}
