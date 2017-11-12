package ru.java.homework;
import org.jetbrains.annotations.NotNull;

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
    @NotNull
    public static <T> Maybe<T> just(@NotNull T t) {
        return new Maybe<>(t);
    }

    /** Create empty Maybe
     * @return empty Maybe
     */
    @NotNull
    public static Maybe<?> nothing() {
        return new Maybe<>();
    }

    /** Use for receive kept value in Maybe
     * @return kept value
     * @throws MaybeNothingException if Maybe is empty
     */
    public T get() throws MaybeNothingException {
        if (st == null)
            throw new MaybeNothingException();
        else
            return st;
    }

    /**
     * @return false if Maybe empty, true otherwise
     */
    public boolean isPresent() {
        return st != null;
    }

    /** Apply mapper for element kept in Maybe
     * @param mapper function that will apply
     * @param <U> type of return Maybe
     * @return Maybe kept result of mapper
     */
    public <U> Maybe<U> map(Function<T, U> mapper) {
        if (!isPresent())
            return (Maybe<U>) this;
        else {
            return new Maybe<>(mapper.apply(st));
        }
    }

    static public class MaybeNothingException extends Exception {

    }
}
