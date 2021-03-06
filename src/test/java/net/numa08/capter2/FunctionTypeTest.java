package net.numa08.capter2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class FunctionTypeTest {

    private void m1 (Function1<? super String, Object> f1) {
        f1.apply("hello");
    }

    @Test
    public void invokeM1() {
        final Function1<Object, Object> f1 = new Function1<Object, Object>() {
            @Override
            public Object apply(Object arg) {
                assertTrue("type is not safe?", arg instanceof String);
                return arg.toString();
            }
        };

        m1(f1);

        final Function1<? super Number, Void> f2 = new Function1<Object, Void>() {
            @Override
            public Void apply(Object number) {
                return null;
            }
        };

        f2.apply(1);
        f2.apply(1.1);
    }

    private Object m2 (Function1<? super String, ? extends String> f2) {
        return f2.apply("hello");
    }

    @Test
    public void invokeM2() {
        final Function1<String, String> f2 = new Function1<String, String>() {
            @Override
            public String apply(String s) {
                return "1";
            }
        };

        final Object result = m2(f2);
        assertTrue("type is not safe?", result instanceof String);
    }

}
