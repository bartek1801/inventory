package pl.com.bottega.inventory.functionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class EmptyList<T> implements FunList<T> {

    //Singleton
    private static final EmptyList INSTANCE = new EmptyList<>();

    private EmptyList(){ }

    public static <T> FunList<T> getInstance() {
        return INSTANCE;
    }

    @Override
    public FunList<T> add(T element) {
        return new NonEmptyList<>(element);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public FunList<T> remove(T element) {
        return this;
    }

    @Override
    public void each(Consumer<T> consumer) {

    }

    @Override
    public <R> FunList<R> map(Function<T, R> mapper) {
        return FunList.empty();
    }

    @Override
    public <AccT> AccT foldLeft(AccT identity, BiFunction<AccT, T, AccT> folder) {
        return identity;
    }


}
