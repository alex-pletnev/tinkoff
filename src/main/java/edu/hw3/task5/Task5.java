package edu.hw3.task5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Task5 {

    public List<Person> parseContacts(List<Person> personList, String mode) {
        String asc = "ASC";
        String desc = "DESC";
        if (!(mode.equals(asc) || mode.equals(desc))) {
            throw new InvalidModeException("Mode must be equals ASC or DESC");
        }
        if (Objects.isNull(personList)) {
            return new ArrayList<>();
        }
        if (mode.equals(asc)) {
            Collections.sort(personList);
        } else {
            personList.sort(Collections.reverseOrder());
        }
        return personList;
    }
}
