package pl.com.bottega.inventory.functionalProgramming;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

class NonEmptyList<T> implements FunList<T> {

    private final T head;
    private final FunList<T> tail;

    public NonEmptyList(T element) {
        head = element;
        tail = EmptyList.getInstance();
    }

    public NonEmptyList(T head, FunList<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public FunList<T> add(T element) {
        return new NonEmptyList<>(head, tail.add(element));
    }

    @Override
    public int size() {
        return 1 + tail.size();
    }

    @Override
    public FunList<T> remove(T element) {
        if (element == head || (element != null && element.equals(head)))
            return tail;
        return new NonEmptyList<>(head, tail.remove(element));
    }

    @Override
    public void each(Consumer<T> consumer) {
        consumer.accept(head);
        tail.each(consumer);
    }

    @Override
    public <R> FunList<R> map(Function<T, R> mapper) {
//        R headMapper = mapper.apply(head);
//        FunList<R> tailMapped = tail.map(mapper);
//        return new NonEmptyList<R>(headMapper, tailMapped);
        return new NonEmptyList<R>(mapper.apply(head), tail.map(mapper));
    }

    @Override
    public <AccT> AccT foldLeft(AccT identity, BiFunction<AccT, T, AccT> folder) {
        AccT foldedHead = folder.apply(identity, head);
        return tail.foldLeft(foldedHead, folder);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NonEmptyList<?> that = (NonEmptyList<?>) o;

        if (!head.equals(that.head)) return false;
        return tail != null ? tail.equals(that.tail) : that.tail == null;
    }

}
