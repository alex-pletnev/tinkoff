package edu.hw10.task1;

import edu.hw10.task1.generator.Generator;
import edu.hw10.task1.generator.IntGenerator;
import edu.hw10.task1.generator.StringGenerator;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class RandomObjectGenerator {
    private static final Map<Class<?>, Generator<?>> GENERATORS = new HashMap<>();

    static {
        GENERATORS.put(Integer.class, new IntGenerator());
        GENERATORS.put(String.class, new StringGenerator());
    }

    public <T> T nextObject(Class<T> c)
        throws InvocationTargetException, IllegalAccessException, InstantiationException {
        return nextObject(c, null);
    }

    public <T> T nextObject(Class<T> c, String fabricMethodName)
        throws InvocationTargetException, IllegalAccessException, InstantiationException {

        var annotationsMap = parseAnnotations(c);

        var fabricMethod = findFabricMethod(c, fabricMethodName);
        if (!Objects.isNull(fabricMethod)) {
            var parameterCount = fabricMethod.getParameterCount();
            Object[] args = new Object[parameterCount];
            for (int i = 0; i < parameterCount; i++) {
                var parameter = fabricMethod.getParameters()[i];
                args[i] = GENERATORS
                    .get(parameter.getType())
                    .generate(annotationsMap.get(parameter.getName()));
            }
            return c.cast(fabricMethod.invoke(null, args));
        } else {
            var constructor = findConstructor(c);
            var parameterCount = constructor.getParameterCount();
            Object[] args = new Object[parameterCount];
            for (int i = 0; i < parameterCount; i++) {
                var parameter = constructor.getParameters()[i];
                args[i] = GENERATORS
                    .get(parameter.getType())
                    .generate(annotationsMap.get(parameter.getName()));
            }
            return c.cast(constructor.newInstance(args));
        }

    }

    private <T> Map<String, Annotation[]> parseAnnotations(Class<T> c) {
        Map<String, Annotation[]> annotationMap = new HashMap<>();
        var fields = c.getDeclaredFields();
        for (var field : fields) {
            annotationMap.put(field.getName(), field.getAnnotations());
        }
        return annotationMap;
    }

    private <T> Method findFabricMethod(Class<T> c, String fabricMethodName) {
        return Arrays.stream(c.getMethods())
            .filter(method -> method.getName().equals(fabricMethodName))
            .findFirst()
            .orElse(null);
    }

    private <T> Constructor<?> findConstructor(Class<T> c) {
        return Arrays.stream(c.getConstructors())
            .max(Comparator.comparingInt(Constructor::getParameterCount))
            .orElse(null);
    }
}
