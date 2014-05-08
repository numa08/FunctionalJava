package net.numa08.capter2;

import java.util.Arrays;
import java.util.List;

public class IntCombinator {

    private final List<Integer> list;

    public IntCombinator(List<Integer> list) {
        this.list = list;
    }

    public int sum() {
        final Integer[] array = list.toArray(new Integer[0]);
        return sum(Arrays.asList(array), 0);
    }

    private int sum(List<Integer> list, int accumulator) {
        if (list.isEmpty()) {
            return accumulator;
        }

        final int temp = accumulator + list.get(0);
        final List<Integer> newList = list.subList(1, list.size());
        return sum(newList, temp);
    }
}
