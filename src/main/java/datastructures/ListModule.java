package datastructures;

import net.numa08.capter2.Function1;
import net.numa08.capter2.Function1Void;
import net.numa08.capter2.Function2;
import option.Option;

import java.util.Arrays;

public class ListModule {
    public static interface List<T> {

        public abstract Option<T> head();
        public abstract Option<List<T>> tail();
        public abstract boolean isEmpty();

        public List<T> filter(Function1<T, Boolean> f);
        public <T2> List<T2> map(Function1<T, T2> f);
        public  <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f);
        public  <T2> T2 foldRight(T2 seed, Function2<T2, T, T2> f);
        public void foreach(Function1Void<T> f);
    }

    public static final class NonEmptyList<T> implements List<T> {

        private final Option<T> head;
        private final Option<List<T>> tail;

        @Override
        public Option<T> head() {
            return head;
        }

        @Override
        public Option<List<T>> tail() {
            return tail;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public List<T> filter(Function1<T, Boolean> f) {
            if (f.apply(head.get())) {
                return list(head.get(), tail.get().filter(f));
            } else {
                return tail.get().filter(f);
            }
        }

        @Override
        public <T2> List<T2> map(Function1<T, T2> f) {
            return list(f.apply(head.get()), tail.get().map(f));
        }

        @Override
        public <T2> T2 foldLeft(T2 seed, Function2<T2, T, T2> f) {
            return tail.get().foldLeft(f.apply(seed, head.get()), f);
        }

        @Override
        public <T2> T2 foldRight(T2 seed, Function2<T2, T, T2> f) {
            return tail.get().foldRight(f.apply(seed, head.get()), f);
        }

        @Override
        public void foreach(Function1Void<T> f) {
            f.apply(head.get());
            tail.get().foreach(f);
        }

        protected NonEmptyList(T head, List<T> tail) {
            this.head = Option.option(head);
            this.tail = Option.option(tail);
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
            return "(" + head().get() + ", " + tail().get() + ")";
        }
    }

    public static final List<? extends Object> EMPTY = new List<Object>() {
        @Override
        public Option<Object> head() {
            return Option.none();
        }

        @Override
        public Option<List<Object>> tail() {
            return Option.none();
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
