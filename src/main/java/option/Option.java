package option;

abstract public class Option<T> {

    public abstract boolean hasValue();

    public abstract T get();

    public T getOrElse(T alternative) {
        return hasValue() ? get() : alternative;
    }

    public static<T> Option<T> option(T obj) {
        if (obj == null) {
            return none();
        } else {
            return new Some<>(obj);
        }
    }

    public static class NoneHasNoValue extends RuntimeException {}

    public static final Option<? extends Object> None = new Option<Object>() {
        @Override
        public boolean hasValue() {
            return false;
        }

        @Override
        public Object get() {
            throw new NoneHasNoValue();
        }

        @Override
        public String toString() {
            return "None";
        }
    };

    public static <T> Option<T> none() {
        return (Option<T>)None;
    }
 }
