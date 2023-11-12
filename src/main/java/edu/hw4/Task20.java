package edu.hw4;

import edu.hw4.validate.ValidationError;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Task20 {

    public Map<String, String> run(Map<String, Set<ValidationError>> map) {
        Map<String, String> stringMap = new HashMap<>();
        for (var key : map.keySet()) {
            String descr = map.get(key).stream()
                .map(ValidationError::getFieldName)
                .toList().toString();
            stringMap.put(key, descr);
        }
        return stringMap;
    }
}
