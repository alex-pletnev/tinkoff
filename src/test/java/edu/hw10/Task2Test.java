package edu.hw10;

import edu.hw10.task2.CacheProxy;
import edu.hw10.task2.entity.BasicCalculator;
import edu.hw10.task2.entity.BasicCalculatorImpl;
import edu.hw10.task2.entity.FibCalculator;
import edu.hw10.task2.entity.FibCalculatorImpl;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task2Test {
    private static final String URL = "src/main/resources/cache.txt";

    @Test
    void cacheProxyWithFibCalculatorTest() throws IOException {
        var path = Path.of(URL);
        FibCalculator fib = new FibCalculatorImpl();
        FibCalculator proxy = CacheProxy.create(fib, FibCalculator.class);
        proxy.fib(5);
        proxy.fib(5);
        proxy.fib(6);
        proxy.fib(7);

        var map = fillFromDisk(path);
        Assertions.assertEquals("5", map.get("fib(5)"));
        Assertions.assertEquals("8", map.get("fib(6)"));
        Assertions.assertEquals("13", map.get("fib(7)"));
    }

    @Test
    void cacheProxyWithBasicCalculatorTest() throws IOException {
        var path = Path.of(URL);
        BasicCalculatorImpl basic = new BasicCalculatorImpl();
        BasicCalculator proxy = CacheProxy.create(basic, BasicCalculator.class);
        proxy.add(2, 2);
        proxy.subtract(2, 2);
        proxy.multiply(2, 2);
        proxy.divide(2, 2);

        var map = fillFromDisk(path);
        Assertions.assertEquals("4", map.get("add(2, 2)"));
        Assertions.assertEquals("0", map.get("subtract(2, 2)"));
        Assertions.assertEquals("4", map.get("multiply(2, 2)"));
        Assertions.assertEquals("1", map.get("divide(2, 2)"));
    }





    private static Map<String, String> fillFromDisk(Path path) throws IOException {
        Map<String, String> cacheMap = new HashMap<>();
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                var newLine = scanner.nextLine();
                var splitLine = newLine.split(" = ");
                cacheMap.put(splitLine[0], splitLine[1]);
            }
        }
        return cacheMap;
    }
}
