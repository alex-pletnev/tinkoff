package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;

class Task2Test {
    private static final Logger LOGGER = LogManager.getLogger();

    static class ArithmeticUtils {

        private ArithmeticUtils() {
        }

        public static int sum(int a, int b) {
            return a + b;
        }
    }

    static class NewArithmeticImpl {

        private NewArithmeticImpl() {

        }

        public static int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    void testChangeClassBehavior() {
        var excepted = 16;

        try {

            ByteBuddyAgent.install();
            new ByteBuddy().redefine(ArithmeticUtils.class)
                .method(named("sum"))
                .intercept(MethodDelegation.to(NewArithmeticImpl.class))
                .make()
                .load(
                    ClassLoader.getSystemClassLoader(),
                    ClassReloadingStrategy.fromInstalledAgent()
                )
                .getLoaded();
        } catch (Throwable e) {
            LOGGER.error(e);
        }
        var actual = ArithmeticUtils.sum(4, 4);

        Assertions.assertEquals(excepted, actual);

    }

}



