package datastructures;

import option.None;
import option.Option;
import option.Some;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static datastructures.ListModule.List;
import static datastructures.ListModule.emptyList;
import static datastructures.ListModule.list;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ListTest {

    List<String> EMPTYS = emptyList();
    List<Long> EMPTYL = emptyList();

    @Test
    public void callingHeadOnAnEmptyListRaises() {
        final Option<String> expected = new None<>();
        final Option<String> actual = EMPTYS.head();

        assertThat("failure - empty list's head should be None", actual, is(expected));
    }

    @Test
    public void callingTailOnAnEmptyListRaises() {
        final Option<List<String>> expected = new None<>();
        final Option<List<String>> actual = EMPTYS.tail();

        assertThat("failure - empty list's tail should be None", actual, is(expected));
    }

    @Test
    public void callingTailOnAListWithMotlierElementsReturnsANonEmpty() {
        List<String> tail = list("one", list("two", EMPTYS));

        final Option<List<String>> expected = new Some<>(list("two", EMPTYS));
        final Option<List<String>> actual = tail.tail();

        assertThat("failure - tail list is not equal", actual, is(expected));
    }

    @Test
    public void callingHeadOnANonEmptyListReturnsTheHead() {
        List<String> list = list("one", EMPTYS);

        final Option<String> expected = new Some<>("one");
        final Option<String> actual = list.head();

        assertThat("failure - head object is not equal", actual, is(expected));
    }

    @Test
    public void allEmptyListAreEqual() {
        assertEquals(EMPTYS, EMPTYL);
    }

    @Test
    public void listAreRecursiveStructures() {
        List<String> list1 = list("one", list("two", list("three", EMPTYS)));

        String expected = "(one, (two, (three, ())))";
        String actual = list1.toString();

        assertThat("failure - string is not equal", actual, is(expected));
    }
}
