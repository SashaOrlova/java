package ru.java.function;

import org.jetbrains.annotations.NotNull;

/**
 * Represents a function that accepts two arguments and produces a result.
 *
 * @param <T1> - type of first argument
 * @param <T2> - type of second argument
 * @param <R>  - return type
 */
public interface Function2<T1, T2, R> {
    R apply(@NotNull T1 arg1, @NotNull T2 arg2);

    /**
     * Returns a composed function that first applies the before function to its input,
     * and then applies this function to the result
     *
     * @param g   the function to apply before this function is applied
     * @param <S> - type of argument
     * @return a composed function
     */
    default <S> Function2<T1, T2, S> compose(@NotNull Function1<? super R, S> g) {
        return (a1, a2) -> g.apply(apply(a1, a2));
    }

    /**
     * Apply first argument of function
     *
     * @param arg - first argument of function
     * @return - partly apply function
     */
    default Function1<T2, R> bind1(@NotNull T1 arg) {
        return t -> apply(arg, t);
    }

    /**
     * Apply second argument of function
     *
     * @param arg - second argument of function
     * @return - partly apply function
     */
    default Function1<T1, R> bind2(@NotNull T2 arg) {
        return t -> apply(t, arg);
    }

    /**
     * make function of one argument, witch returns a function of one argument
     *
     * @return function of one argument
     */
    default Function1<T1, Function1<T2, R>> curry() {
        return t -> bind1(t);
    }
}
