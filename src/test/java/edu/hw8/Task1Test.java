package edu.hw8;

import edu.hw8.task1.Client;
import edu.hw8.task1.Server;
import edu.hw8.task1.excepions.ServerException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task1Test {

    private static final List<String> NAMES = new ArrayList<>();
    private static final List<String> KEY_WORDS = new ArrayList<>();

    static {
        NAMES.add("Ваня");
        NAMES.add("Алексей");
        NAMES.add("Елена");
        NAMES.add("Мария");
        NAMES.add("Андрей");
        NAMES.add("Ольга");
        NAMES.add("Дмитрий");
        NAMES.add("Наталья");
        NAMES.add("Сергей");
        NAMES.add("Анна");

        KEY_WORDS.add("личности");
        KEY_WORDS.add("оскорбления");
        KEY_WORDS.add("глупый");
        KEY_WORDS.add("интеллект");

    }

    @Test
    void multiThreadTest() {
        Assertions.assertDoesNotThrow(
            () -> {
                new Thread(() -> {
                    try {
                        Server.run();
                    } catch (InterruptedException e) {
                        throw new ServerException(e);
                    }
                }).start();
                for (int i = 0; i < 20; i++) {
                    new Client(
                        NAMES.get(i % NAMES.size()),
                        KEY_WORDS.get(i % KEY_WORDS.size()),
                        "localhost",
                        Server.PORT
                    ).start();
                }
            }
        );
    }

}
