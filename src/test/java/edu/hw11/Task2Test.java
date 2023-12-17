package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;

class Task2Test {

    public static class ArithmeticUtils {

        public int sum(int a, int b) {
            return a + b;
        }
    }

    public static class NewArithmeticImpl {

        public int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    void testChangeClassBehavior()
        throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var excepted = 16;

        ArithmeticUtils dynamicType = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum"))
            .intercept(MethodDelegation.to(new NewArithmeticImpl()))
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded()
            .getDeclaredConstructor()
            .newInstance();

        var actual = dynamicType.sum(4, 4);

        Assertions.assertEquals(excepted, actual);

    }

}



