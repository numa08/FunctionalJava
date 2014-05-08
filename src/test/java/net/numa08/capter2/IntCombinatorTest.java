package net.numa08.capter2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class IntCombinatorTest {

    @Test
    public void sumAll() {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9 ,10);
        final IntCombinator combinator = new IntCombinator(list);

        final  int expected = 55;
        final int actual = combinator.sum();

        assertThat("failure - list sum value is invalid", actual, is(expected));
    }

    @Test
    public void sumEmpty() {
        final List<Integer> list = Arrays.asList();
        final IntCombinator combinator = new IntCombinator(list);

        final int expected = 0;
        final int actual = combinator.sum();

        assertThat("failure - list sum value is invalid", actual, is(expected));
    }

    @Test
    public void sumUnit() {
        final List<Integer> list = Arrays.asList(1);
        final IntCombinator combinator = new IntCombinator(list);

        final int expected = 1;
        final int actual = combinator.sum();

        assertThat("failure - list sum value is invalid", actual, is(expected));
    }
}
