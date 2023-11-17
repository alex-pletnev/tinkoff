package edu.hw6.task1;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SerializationUtils {

    private SerializationUtils() {
    }

    public static String serializeMap(Map<String, String> map) {
        if (Objects.isNull(map)) {
            throw new NullPointerException();
        }
        StringBuilder stringBuilder = new StringBuilder();
        map.forEach((key, value) -> stringBuilder.append(key).append(":").append(value).append("\n"));
        return stringBuilder.toString();
    }

    public static Map<String, String> deserializeString(String strings) {
        var stringList = strings.split("\n");

        Map<String, String> map = new HashMap<>();
        for (String string : stringList) {
            var splitString = string.split(":");
            if (splitString.length != 2) {
                throw new LoadFromDiskException();
            }
            map.put(splitString[0], splitString[1]);
        }

        return map;
    }
}
