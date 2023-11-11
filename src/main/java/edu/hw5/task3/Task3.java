package edu.hw5.task3;

import java.time.LocalDate;
import java.util.Optional;

public class Task3 {

    public Optional<LocalDate> parseDate(String string) {
        DateParser dateParser1 = new DateParser1();
        DateParser dateParser2 = new DateParser2();
        DateParser dateParser3 = new DateParser3();
        DateParser dateParser4 = new DateParser4();
        DateParser dateParser5 = new DateParser5();
        DateParser dateParser6 = new DateParser6();
        DateParser dateParser7 = new DateParser7();
        DateParser dateParser8 = new DateParser8();
        dateParser1.setNextDateParser(dateParser2);
        dateParser2.setNextDateParser(dateParser3);
        dateParser3.setNextDateParser(dateParser4);
        dateParser4.setNextDateParser(dateParser5);
        dateParser5.setNextDateParser(dateParser6);
        dateParser6.setNextDateParser(dateParser7);
        dateParser7.setNextDateParser(dateParser8);

        return dateParser1.parseDate(string);

    }
}
