package edu.project1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Reader {
    private final Scanner scanner;

    public Reader() {
        scanner = new Scanner(System.in);
    }

    public Reader(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        scanner = new Scanner(file);
    }

    public char readLetter() throws ExitException {
        while (true) {
            char[] input = scanner.nextLine().toCharArray();
            if (isExit(input)) {
                throw new ExitException();
            }

            if (input.length != 1 || !Character.isLetter(input[0])) {
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

}
