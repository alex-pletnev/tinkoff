package edu.hw3.task3;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3<T> {

    public Map<T, Integer> freqDict(@NotNull List<T> stringList) {
        Map<T, Integer> freqDict = new HashMap<>();
        for (T key : stringList) {
            if (freqDict.containsKey(key)) {
                var value = freqDict.get(key);
                freqDict.put(key, ++value);
            } else {
                freqDict.put(key, 1);
            }
        }
        return freqDict;
    }
}
