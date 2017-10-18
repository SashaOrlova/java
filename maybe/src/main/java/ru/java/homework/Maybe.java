package ru.java.homework;
import java.util.function.Function;

/**
 * Class for correct work with java classes. Object of class can be nothing or just.
 * From just can return T object, that kept in Maybe.
 * @param <T> type of kept value
 */
public class Maybe<T> {
    private T st = null;
    private Maybe(T t){
        st = t;
    }

    private Maybe(){
        st = null;
    }

    /** Create new Maybe kept given argument
     * @param t what maybe kept
     * @param <T> type of return Maybe
     * @return new Maybe
     */
    @org.jetbrains.annotations.NotNull
    public static <T> Maybe<T> just(T t) {
        return new Maybe<T>(t);
    }

    /** Create empty Maybe
     * @param <T> type of Maybe
     * @return empty Maybe
     */
    public static <T> Maybe<T> nothing() {
        return new Maybe<T>();
    }

    /** Use for receive kept value in Maybe
     * @return kept value
     * @throws MaybeException if Maybe is empty
     */
    public T get() throws MaybeException {
        if (st == null)
            throw new MaybeException();
        else
            return st;
    }

    /**
     * @return false if Maybe empty, true otherwise
     */
    public boolean isPresent() {
        return !(st == null);
    }

    /** Apply mapper for element kept in Maybe
     * @param mapper function that will apply
     * @param <U> type of return Maybe
     * @return Maybe kept result of mapper
     */
    public <U> Maybe<U> map(Function<T, U> mapper) {
        if (!isPresent())
            return new Maybe<>();
        else {
            return new Maybe<>(mapper.apply(st));
        }
    }

    static public class MaybeException extends Exception {

    }
}
