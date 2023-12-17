package edu.hw10.task2;

import edu.hw10.task2.annotation.Cache;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CacheProxy implements InvocationHandler {
    private static final String SEPARATOR = " = ";
    private static final String URL = "src/main/resources/cache.txt";
    private static final Logger LOGGER = LogManager.getLogger();
    private final Object target;
    private final Map<String, Object> cache;
    private final Path path;


    private CacheProxy(Object target) {
        this.target = target;
        this.cache = new HashMap<>();
        this.path = Path.of(URL);
    }

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, Class<?> targetClass) {
        return (T) Proxy.newProxyInstance(
            targetClass.getClassLoader(),
            new Class<?>[] {targetClass},
            new CacheProxy(target)
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        fillFromDisk();
        String key = generateCacheKey(method, args);
        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        Object result = method.invoke(target, args);

        if (method.isAnnotationPresent(Cache.class)
            && method.getAnnotation(Cache.class).persist()) {
            cache.put(key, result);
            addToDisk(key, result);
        }

        return result;
    }

    private String generateCacheKey(Method method, Object[] args) {
        StringBuilder keyBuilder = new StringBuilder();
        keyBuilder.append(method.getName());
        keyBuilder.append("(");
        for (int i = 0; i < args.length - 1; i++) {
            keyBuilder.append(args[i].toString()).append(", ");
        }
        keyBuilder.append(args[args.length - 1]).append(")");
        return keyBuilder.toString();
    }

    private void fillFromDisk() throws IOException {
        if (!path.toFile().exists()) {
            return;
        }
        try (Scanner scanner = new Scanner(path)) {
            while (scanner.hasNext()) {
                var newLine = scanner.nextLine();
                var splitLine = newLine.split(SEPARATOR);
                cache.put(splitLine[0], Integer.parseInt(splitLine[1]));
                LOGGER.info("'{}' read from {}", newLine, path);
            }
        }
    }

    private void addToDisk(String key, Object result) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(
            path,
            StandardOpenOption.APPEND,
            StandardOpenOption.CREATE
        )) {
            var newLine = key + SEPARATOR + result;
            writer.write(newLine + "\n");
            writer.flush();
            LOGGER.info("'{}' write to {}", newLine, path);

        }
    }


}
