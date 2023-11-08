package edu.hw3;

import edu.hw3.task1.Task1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task1Test {
    @ParameterizedTest
    @CsvSource({"'Hello world!', 'Svool dliow!'"
        , "'Any fool can write code that a computer can understand. Good programmers write code that humans can understand. ― Martin Fowler',"
        + " 'Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi'"
        , "'это метод шифрования', 'это метод шифрования'"
        , "'это метод шифрования123 Hello world!', 'это метод шифрования123 Svool dliow!'"})
    void atbashTest(String string, String expected) {
        Task1 task1 = new Task1();
        var ans = task1.atbash(string);
        Assertions.assertEquals(expected, ans);
    }
}
