package a01a.e1;

import java.util.*;
import java.util.stream.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        return new Acceptor<String, Integer>() {

            private int c = 0;

            @Override
            public boolean accept(String e) {
                if (e.isEmpty()) {
                    c++;
                }
                return true;
            }

            @Override
            public Optional<Integer> end() {
                return Optional.ofNullable(c);
            }
        };
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        return new Acceptor<Integer, String>() {

            private List<Integer> list = new LinkedList<>();
            private int i = 0;

            @Override
            public boolean accept(Integer e) {
                i++;
                if (list.isEmpty() || e > list.iterator().next()) {
                    list.add(e);
                    return true;
                }
                return false;
            }

            @Override
            public Optional<String> end() {
                if (i != list.size()) {
                    return Optional.empty();
                }
                return Optional.ofNullable(list.stream().map(s -> s.toString()).collect(Collectors.joining(":")));
            }
        };
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        return new Acceptor<Integer, Integer>() {

            private List<Integer> list = new LinkedList<>();
            private int i = 0;

            @Override
            public boolean accept(Integer e) {
                i++;
                if (list.size() < 3) {
                    list.add(e);
                    return true;
                }
                return false;
            }

            @Override
            public Optional<Integer> end() {
                if (i != 3) {
                    return Optional.empty();
                }
                return Optional.ofNullable(list.stream().mapToInt(Integer::intValue).sum());
            }

        };
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        return new Acceptor<E, Pair<O1, O2>>() {

            @Override
            public boolean accept(E e) {
                return a1.accept(e) && a2.accept(e);
            }

            @Override
            public Optional<Pair<O1, O2>> end() {
                if (!a1.end().isPresent() || !a2.end().isPresent()) {
                    return Optional.empty();
                }
                Pair<O1, O2> pair = new Pair<>(a1.end().get(), a2.end().get());
                return Optional.ofNullable(pair);
            }

        };
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
        return new Acceptor<E, O>() {

            private Optional<S> opt = Optional.of(initial);

            @Override
            public boolean accept(E e) {
                if (opt.isEmpty()) {
                    return false;
                }
                opt = stateFun.apply(e, opt.get());
                return opt.isPresent();
            }

            @Override
            public Optional<O> end() {
                if (opt.isEmpty()) {
                    return Optional.empty();
                }
                return outputFun.apply(opt.get());
            }

        };
    }

}
