package net.numa08.capter3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static datastructures.ListModule.List;
import static datastructures.ListModule.emptyList;
import static datastructures.ListModule.list;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(JUnit4.class)
public class ListTest {

    @Test
    public void structureListByRepeatedParameters() {
        List<String> empty = emptyList();

        List<String> actual = list("one", "two", "three");
        List<String> expected = list("one", list("two" , list("three", empty)));

        assertThat("failure - list is not equal", expected, is(actual));
    }
}
