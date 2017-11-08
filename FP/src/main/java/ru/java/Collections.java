package ru.java;

import org.jetbrains.annotations.NotNull;
import ru.java.function.Function1;
import ru.java.function.Function2;
import ru.java.function.Predicate;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Class for work with collections and functions
 */
public class Collections {
    /**
     * Iterate over collection and apply f to every element
     *
     * @param a   - collection for mapping
     * @param f   - function for apply
     * @param <E> - type of collection
     * @param <R> - type of new collection
     * @return new ArrayList contains of result of f applying
     */
    public static <E, R> ArrayList<R> map(@NotNull Collection<E> a, @NotNull Function1<? super E, R> f) {
        ArrayList<R> ans = new ArrayList<>();
        for (E elem : a) {
            ans.add(f.apply(elem));
        }
        return ans;
    }

    /**
     * Iterate over collection and choose elements matched predicate
     *
     * @param a   - collection for filter
     * @param p   - condition
     * @param <E> - type of elements in collection
     * @return new ArrayList contains elements matched p
     */
    public static <E> ArrayList<E> filter(@NotNull Collection<E> a, @NotNull Predicate<E> p) {
        ArrayList<E> ans = new ArrayList<>();
        for (E elem : a) {
            if (p.apply(elem))
                ans.add(elem);
        }
        return ans;
    }

    /**
     * Iterate over collection and choose elements while they matched predicate
     *
     * @param a   - collection for takeWhile
     * @param p   - condition
     * @param <E> - type of elements in collection
     * @return new ArrayList contains elements from beginning matched p
     */
    public static <E> ArrayList<E> takeWhile(@NotNull Collection<E> a, @NotNull Predicate<? super E> p) {
        ArrayList<E> ans = new ArrayList<>();
        for (E elem : a) {
            if (p.apply(elem))
                ans.add(elem);
            else
                break;
        }
        return ans;
    }

    /**
     * Iterate over collection and choose elements while they don't matched predicate
     *
     * @param a - collection for takeUnless
     * @param p - condition
     * @param <E> - type of elements in collection
     * @return new ArrayList contains elements from beginning don't matched p
     */
    public static <E> ArrayList<E> takeUnless(@NotNull Collection<E> a, @NotNull Predicate<? super E> p) {
        return takeWhile(a, t -> !p.apply(t));
    }

    /**
     * Right-associative fold of a structure.
     * In the case of lists, foldr, when applied to a binary operator, a starting value (
     * typically the right-identity of the operator), and a list, reduces the Collection using the binary Function,
     * from right to left:
     * @param a - collection for foldr
     * @param f - function of two argument for applying
     * @param start - starting valuable
     * @param <E> - type of elements in collection
     * @return result of computations
     */
    public static <E> E foldr(@NotNull Collection<E> a, @NotNull Function2<E, E, E> f, @NotNull E start) {
        for (E elem : a) {
            start = f.apply(start, elem);
        }
        return start;
    }

    /**
     * Left-associative fold of a structure.
     * In the case of lists, foldr, when applied to a binary operator, a starting value (
     * typically the right-identity of the operator), and a list, reduces the Collection using the binary Function,
     * from left to right:
     * @param a - collection for foldl
     * @param f - function of two argument for applying
     * @param start - starting valuable
     * @param <E> - type of elements in collection
     * @return result of computations
     */
    public static <E> E foldl(@NotNull Collection<E> a, @NotNull Function2<E, E, E> f, @NotNull E start) {
        ArrayList<E> container = new ArrayList<>();
        container.addAll(a);
        for (int i = container.size() - 1; i >= 0; i--) {
            start = f.apply(start, container.get(i));
        }
        return start;
    }
}
