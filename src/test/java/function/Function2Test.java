package function;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class Function2Test {

    @Test
    public void returnValue() {

        final int expected = 4;

        final Function2<Integer, Void, Integer> function = new Function2<Integer, Void, Integer>() {
            @Override
            public Integer apply(Integer integer, Void aVoid) {
                return integer;
            }
        };

        final int actual = function.apply(expected, null);
        assertThat(expected, is(actual));
    }

    @Test
    public void argument1() {

        final int expected = 4;

        final Function2<Integer, Void, Void> function = new Function2<Integer, Void, Void>() {
            @Override
            public Void apply(Integer actual, Void aVoid) {
                assertThat(expected, is(actual));
                return null;
            }
        };
        function.apply(expected, null);
    }

    @Test
    public void argument2() {

        final int expected = 4;

        final Function2<Void, Integer, Void> function = new Function2<Void, Integer, Void>() {
            @Override
            public Void apply(Void aVoid, Integer actual) {
                assertThat(expected, is(actual));
                return null;
            }
        };

        function.apply(null, expected);
    }
}
