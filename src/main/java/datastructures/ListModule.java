package datastructures;

import net.numa08.capter2.Function1;
import net.numa08.capter2.Function2;

import java.util.Arrays;

public class ListModule {
    public static interface List<T> {

        public abstract T head();
        public abstract List<T> tail();
        public abstract boolean isEmpty();
    }

    public static final class NonEmptyList<T> implements List<T> {

        private final T head;
        private final List<T> tail;

        @Override
        public T head() {
            return head;
        }

        @Override
        public List<T> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        protected NonEmptyList(T head, List<T> tail) {
            this.head = head;
            this.tail = tail;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            List<?> that = (List<?>)obj;
            return head().equals(that.head()) && tail().equals(that.tail());
        }

        @Override
        public int hashCode() {
            return 37 * head().hashCode() + tail().hashCode();
        }

        @Override
        public String toString() {
            return "(" + head() + ", " + tail() + ")";
        }
    }

    public static class EmptyListHasNoHead extends RuntimeException {}

    public static class EmptyListHasNoTail extends RuntimeException {}

    public static final List<? extends Object> EMPTY = new List<Object>() {
        @Override
        public Object head() {
            throw new EmptyListHasNoHead();
        }

        @Override
        public List<Object> tail() {
            throw new EmptyListHasNoTail();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public String toString() {
            return "()";
        }

    };

    @SuppressWarnings(value = "unchecked")
    public static <T> List<T> emptyList() {
        return (List<T>)EMPTY;
    }

    public static <T> List<T> list(T head, List<T> tail) {
        return new NonEmptyList<>(head, tail);
    }

    public static <T> List<T> list(T... elems) {
        if (elems.length == 0) {
            return emptyList();
        }
        final Function2<T[], List<T>, List<T>> list = new Function2<T[], List<T>, List<T>>() {

            @Override
            public List<T> apply(T[] ts, List<T> tList) {
                if (ts.length == 0) {
                    return tList;
                }

                final List<T> l = list(ts[ts.length - 1], tList);
                final T[] e = Arrays.copyOfRange(ts, 0, ts.length - 1);
                return apply(e, l);
            }
        };
        final List<T> empty = emptyList();
        return list.apply(elems, empty);
    }
}
