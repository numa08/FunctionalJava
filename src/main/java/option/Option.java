package option;

abstract public class Option<T> {

    public abstract boolean hasValue();

    public abstract T get();

    public T getOrElse(T alternative) {
        return hasValue() ? get() : alternative;
    }

    public static<T> Option<T> opt(T obj) {
        if (obj == null) {
            return new None<>();
        } else {
            return new Some<>(obj);
        }
    }
}
