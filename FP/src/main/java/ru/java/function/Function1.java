package ru.java.function;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * @param <T> - type of arg
 * @param <R> - return type
 */
public interface Function1<T, R> {
    R apply(@NotNull T arg);

    /**
     * Returns a composed function that first applies the before function to its input,
     * and then applies this function to the result
     *
     * @param g   the function to apply before this function is applied
     * @param <S> - type of argument
     * @return a composed function
     */
    default <S> Function1<T, S> compose(@NotNull Function1<? super R, ? extends S> g) {
        return t -> g.apply(apply(t));
    }
}
