package edu.project1;

import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Reader implements AutoCloseable{
    private final Scanner scanner;

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public Reader(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        scanner = new Scanner(file);
    }

    public char readLetter(Logger logger) throws ExitException {
        while (true) {
            char[] input = scanner.nextLine().toCharArray();
            if (isExit(input)) {
                throw new ExitException();
            }

            if (input.length != 1 || !Character.isLetter(input[0])) {
                logger.info("Input must contain 1 letter from a to z");
                continue;
            }
            return input[0];
        }
    }

    public boolean isExit(char[] input) {
        String string = String.valueOf(input);
        return Objects.equals(string, "exit")
            || Objects.equals(string, "Exit");
    }

    @Override
    public void close() throws Exception {
        scanner.close();
    }
}
