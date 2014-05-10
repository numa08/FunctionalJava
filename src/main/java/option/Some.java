package option;

public final class Some<T> extends Option<T>{

    private final T value;

    public Some(T value) {
        this.value = value;
    }

    @Override
    public boolean hasValue() {
        return true;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Some.class) {
            return false;
        }

        Some<?> that = (Some<?>)obj;
        Object thatValue = that.get();
        return value.equals(thatValue);
    }

    @Override
    public int hashCode() {
        return 37 * value.hashCode();
    }

}
