package edu.hw10;

import edu.hw10.task1.RandomObjectGenerator;
import edu.hw10.task1.entity.MyClass;
import edu.hw10.task1.entity.MyRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;

class Task1Test {
    private static final Logger LOGGER = LogManager.getLogger();

    @RepeatedTest(value = 100)
    void createMyClassTest() {
        Assertions.assertDoesNotThrow(
            () -> {
                RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
                MyClass myClass = randomObjectGenerator.nextObject(MyClass.class, "create");

                LOGGER.info(myClass.toString());
            }
        );
    }

    @RepeatedTest(value = 100)
    void createMyRecordTest() {
        Assertions.assertDoesNotThrow(
            () -> {
                RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
                MyRecord myRecord = randomObjectGenerator.nextObject(MyRecord.class);

                LOGGER.info(myRecord.toString());
            }
        );
    }
}
