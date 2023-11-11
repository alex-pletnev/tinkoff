package edu.hw5.task5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class Task5 {

    private static final String VALID_LETTERS = "АВЕКМНОРСТУХ";

    public boolean isThisARussianLicensePlate(String licensePlate) {
        if (Objects.isNull(licensePlate)) {
            return false;
        }
        StringBuilder regex
            = new StringBuilder("^[")
            .append(VALID_LETTERS)
            .append("]\\d{3}[")
            .append(VALID_LETTERS)
            .append("]{2}(");

        File file = new File("src/main/resources/AutomobileCodesOfRegionsOfRussia");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                regex.append(scanner.nextLine());
                if (scanner.hasNext()) {
                    regex.append('|');
                }
            }
            regex.append(")$");
        } catch (FileNotFoundException ex) {
            throw new CodesException();
        }
        return licensePlate.matches(regex.toString());
    }

}
