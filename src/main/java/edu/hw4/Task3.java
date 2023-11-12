package edu.hw4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Task3 {

    public Map<Animal.Type, Integer> run(List<Animal> list) {
        Map<Animal.Type, Long> longMap = list.stream()
            .collect(groupingBy(Animal::type, counting()));
        Map<Animal.Type, Integer> ans = new HashMap<>();
        for (Map.Entry<Animal.Type, Long> entry : longMap.entrySet()) {
            ans.put(entry.getKey(), Math.toIntExact(entry.getValue()));
        }
        return ans;
    }
}
