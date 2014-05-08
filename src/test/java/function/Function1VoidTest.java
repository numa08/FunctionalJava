package function;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Function1VoidTest {

    @Test
    public void assertFunctionArgument() {

        final String expected = "argument";

        final Function1Void<String> function = new Function1Void<String>() {
            @Override
            public void apply(String actual) {
                Assert.assertEquals("failure - argument is invalid", expected, actual);
            }
        };
        function.apply(expected);
    }

    @Test
    public void assertFunctionArgumentType() {

        final String string = "argument";

        final Function1Void<String> function = new Function1Void<String>() {
            @Override
            public void apply(String arg) {
                final Class<?> expected = string.getClass();
                final Class<?> actual = arg.getClass();

                Assert.assertSame("failure - argument type is invalid",expected, actual);
            }
        };

        function.apply(string);
    }
}
