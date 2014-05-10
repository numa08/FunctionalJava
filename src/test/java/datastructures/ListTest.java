package datastructures;

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

    @Test(expected = ListModule.EmptyListHasNoHead.class)
    public void callingHeadOnAnEmptyListRaises() {
        EMPTYS.head();
    }

    @Test(expected = ListModule.EmptyListHasNoTail.class)
    public void callingTailOnAnEmptyListRaises() {
        EMPTYS.tail();
    }

    @Test
    public void callingTailOnAListWithMotlierElementsReturnsANonEmpty() {
        List<String> tail = list("one", list("two", EMPTYS));

        final List<String> expected = list("two", EMPTYS);
        final List<String> actual = tail.tail();

        assertThat("failure - tail list is not equal", actual, is(expected));
    }

    @Test
    public void callingHeadOnANonEmptyListReturnsTheHead() {
        List<String> list = list("one", EMPTYS);

        final String expected = "one";
        final String actual = list.head();

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
