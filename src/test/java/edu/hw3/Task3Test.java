package edu.hw3;

import edu.hw3.task3.Task3;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class Task3Test {
    @Test
    public void testFreqDict() {
        Task3<String> task3 = new Task3<>();

        List<String> stringList1 = Arrays.asList("a", "bb", "a", "bb");
        Map<String, Integer> result1 = task3.freqDict(stringList1);
        assertEquals(2, result1.get("bb").intValue());
        assertEquals(2, result1.get("a").intValue());

        List<String> stringList2 = Arrays.asList("this", "and", "that", "and");
        Map<String, Integer> result2 = task3.freqDict(stringList2);
        assertEquals(1, result2.get("that").intValue());
        assertEquals(2, result2.get("and").intValue());
        assertEquals(1, result2.get("this").intValue());

        List<String> stringList3 = Arrays.asList("код", "код", "код", "bug");
        Map<String, Integer> result3 = task3.freqDict(stringList3);
        assertEquals(3, result3.get("код").intValue());
        assertEquals(1, result3.get("bug").intValue());

        Task3<Integer> integerTask3 = new Task3<>();
        List<Integer> stringList4 = Arrays.asList(1, 1, 2, 2);
        Map<Integer, Integer> result4 = integerTask3.freqDict(stringList4);
        assertEquals(2, result4.get(1).intValue());
        assertEquals(2, result4.get(2).intValue());
    }
}
