package a01a.e1;

import java.util.*;
import java.util.stream.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        return new Acceptor<String,Integer>() {
            private int empty = 0;

            @Override
            public boolean accept(String e) {
                if(e.length() == 0){
                    empty++;
                }
                return true;
            }

            @Override
            public Optional<Integer> end() {
                return Optional.ofNullable(empty);
            }
            
        };
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        return new Acceptor<Integer,String>() {
            private List<Integer> list = new LinkedList<>();
            private int numAcceptor = 0;

            @Override
            public boolean accept(Integer e) {
                numAcceptor++;
                if(list.isEmpty() || list.iterator().next() < e){
                    list.add(e);
                    return true;
                }
                return false;
            }

            @Override
            public Optional<String> end() {
                if(numAcceptor != list.size()){
                    return Optional.empty();
                    
                }
                return Optional
                        .ofNullable(list.stream().map(s -> Integer.toString(s)).collect(Collectors.joining(":")));
            }
        };
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        return new Acceptor<Integer,Integer>() {

            private List<Integer> list = new LinkedList<>();
            private int sum = 0;
            private int numAcceptor = 0;
            @Override
            public boolean accept(Integer e) {
                numAcceptor++;
                if(list.size() >= 3) {
                    return false;
                }
                sum += e;
                list.add(e);
                return true;
            }

            @Override
            public Optional<Integer> end() {
                if(numAcceptor != 3){
                    return Optional.empty();
                }   
                return Optional.ofNullable(sum);
            }
            
        };
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        return new Acceptor<E,Pair<O1,O2>>() {

            @Override
            public boolean accept(E e) {
                return a1.accept(e) && a2.accept(e);
            }

            @Override
            public Optional<Pair<O1, O2>> end() {
                if(a1.end().isPresent() && a2.end().isPresent()) {
                    final Pair<O1, O2> result = new Pair<O1, O2>(a1.end().get(), a2.end().get());
                    return Optional.ofNullable(result);
                }
                return Optional.empty();
            }
            
        };
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
            return new Acceptor<E,O>() {

               private Optional<S> state = Optional.of(initial);

                @Override
                public boolean accept(E e) {
                    if(state.isEmpty()) {
                        return false;
                    }
                    state = stateFun.apply(e, state.get());
                    return state.isPresent();
                }

                @Override
                public Optional<O> end() {
                    if(state.isEmpty()) {
                        return Optional.empty();
                    }
                    return outputFun.apply(state.get());
                }
                
            };
    }

}
