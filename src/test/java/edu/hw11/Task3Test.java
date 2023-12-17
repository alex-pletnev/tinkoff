package edu.hw11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.bytecode.ByteCodeAppender;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Opcodes;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Task3Test {

    static class Fibonacci implements ByteCodeAppender {

        public static final String CLASS_NAME = "FibonacciImpl";

        public static final String METHOD_NAME = "fib";

        public static final String VALUES_TYPES = "(I)I";

        @Override
        public @NotNull Size apply(
            @NotNull MethodVisitor methodVisitor,
            Implementation.@NotNull Context context,
            @NotNull MethodDescription methodDescription
        ) {

            methodVisitor.visitCode();

            Label l0 = new Label();
            methodVisitor.visitLabel(l0);
            methodVisitor.visitLineNumber(5, l0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);

            Label l1 = new Label();
            methodVisitor.visitJumpInsn(Opcodes.IF_ICMPGT, l1);

            Label l2 = new Label();
            methodVisitor.visitLabel(l2);
            methodVisitor.visitLineNumber(6, l2);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.IRETURN);

            methodVisitor.visitLabel(l1);
            methodVisitor.visitLineNumber(8, l1);
            methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_1);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, VALUES_TYPES, false);
            methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
            methodVisitor.visitVarInsn(Opcodes.ILOAD, 1);
            methodVisitor.visitInsn(Opcodes.ICONST_2);
            methodVisitor.visitInsn(Opcodes.ISUB);
            methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, CLASS_NAME, METHOD_NAME, VALUES_TYPES, false);
            methodVisitor.visitInsn(Opcodes.IADD);
            methodVisitor.visitInsn(Opcodes.IRETURN);

            Label l3 = new Label();
            methodVisitor.visitLabel(l3);
            methodVisitor.visitLocalVariable("n", "I", null, l0, l3, 1);
            methodVisitor.visitMaxs(4, 2);
            methodVisitor.visitEnd();

            return new Size(4, 2);
        }
    }

    @Test
    void testFibonacci()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int excepted = 13;

        var dynamicType = new ByteBuddy(ClassFileVersion.JAVA_V21)
            .subclass(Object.class)
            .name("FibonacciImpl")
            .defineMethod("fib", int.class, Visibility.PUBLIC)
            .withParameter(int.class, "n")
            .intercept(new Implementation.Simple(new Fibonacci()))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded();

        var instance = dynamicType.getConstructor().newInstance();
        int actual = (int) dynamicType.getMethod("fib", int.class).invoke(instance, 7);

        Assertions.assertEquals(excepted, actual);
    }

}
