package pl.com.bottega.inventory.extraPackage.functionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface FunList<T> {

    static <T> FunList<T> empty() {
        return EmptyList.getInstance();
    }

    static <T> FunList<T> empty(Class<T> klass) {
        return EmptyList.getInstance();
    }

    FunList<T> add(T element);

    FunList<T> remove(T element);

    void each(Consumer<T> consumer);

    <R> FunList<R> map(Function<T, R> mapper);

    <AccT> AccT foldLeft(AccT identity, BiFunction<AccT, T, AccT> folder);

    default FunList<T> filter(Predicate<T> predicate) {
        return foldLeft(empty(), (acc, element) -> {
            if (predicate.test(element))
                return acc.add(element);
            else
                return acc;
        });
    }

    int size();
}
