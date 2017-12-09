package ru.java.function;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a function that accepts one argument and produces boolean.
 *
 * @param <T> - type of argument
 */
public interface Predicate<T> extends Function1<T, Boolean> {

    /**
     * true if pred or this true, false otherwise
     *
     * @param pred - other predicate
     * @return new Predicate
     */
    default Predicate<T> or(@NotNull Predicate<? super T> pred) {
        return t -> apply(t) || pred.apply(t);
    }

    /**
     * true if pred and this true, false otherwise
     *
     * @param pred - other predicate
     * @return new Predicate
     */
    default Predicate<T> and(@NotNull Predicate<? super T> pred) {
        return t -> apply(t) && pred.apply(t);
    }

    /**
     * @return new Predicate with negative value
     */
    default Predicate<T> not() {
        return t -> !apply(t);
    }

    static <U> Predicate<U> ALWAYS_TRUE() {
        return (t -> true);
    }
    static <U> Predicate<U> ALWAYS_FALSE() {
        return (t -> false);
    }
}
