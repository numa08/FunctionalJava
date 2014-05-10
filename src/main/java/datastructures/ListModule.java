package datastructures;

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
}
