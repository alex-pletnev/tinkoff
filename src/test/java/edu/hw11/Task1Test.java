package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;

class Task1Test {

    @Test
    void testHelloByteBuddy() throws Exception {
        var excepted = "Hello, ByteBuddy!";

        Class<?> dynamicType = new ByteBuddy()
            .subclass(Object.class)
            .method(named("toString")).intercept(FixedValue.value("Hello, ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded();
        var actual = dynamicType.getDeclaredConstructor().newInstance().toString();

        Assertions.assertEquals(excepted, actual);
    }

}
