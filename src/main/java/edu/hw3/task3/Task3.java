package edu.hw3.task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public class Task3<T> {

    public Map<T, Integer> freqDict(@NotNull List<T> list) {
        Map<T, Integer> freqDict = new HashMap<>();
        list.forEach(
            el -> freqDict.merge(el, 1, Integer::sum)
        );
        return freqDict;
    }
}
