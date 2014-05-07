package function;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class Function1VoidTest {

    @Test
    public void assertFunctioArgument() {

        final String expected = "argument";

        final Function1Void<String> function = new Function1Void<String>() {
            @Override
            public void apply(String actual) {
                Assert.assertEquals("failure - argument is invalid", expected, actual);
            }
        };
        function.apply(expected);
    }
}
