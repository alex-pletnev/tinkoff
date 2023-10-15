package edu.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class Task3Test {
    @ParameterizedTest
    @CsvSource({"'1,2,3,4', '0,6,5', true",
        "'3,1', '4,0', true", "'-1,-3,0', '-5,1', true", "'1,2,3', '6', false"})
    void isNestableTest(String inputA1, String inputA2, boolean expected) {
        Task3 task3 = new Task3();
        int[] a1 = fromStrToIntArr(inputA1);
        int[] a2 = fromStrToIntArr(inputA2);
        boolean ans = task3.isNestable(a1, a2);
        Assertions.assertEquals(expected, ans);
    }

    private int[] fromStrToIntArr(String input) {
        String[] strA = input.split(",");
        int[] a = new int[strA.length];
        int j = 0;
        for (String i : strA) {
            a[j] = Integer.parseInt(i);
            j++;
        }
        return a;
    }
}
