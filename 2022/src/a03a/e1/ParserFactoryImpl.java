package a03a.e1;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class ParserFactoryImpl implements ParserFactory {

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> acceptedList = new LinkedList<>();
                while (iterator.hasNext()) {
                    acceptedList.add(iterator.next());
                }

                return acceptedSequences.contains(acceptedList);
            }

        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> myList = new LinkedList<>();
                List<Pair<X, X>> myPair = new LinkedList<>();
                while (iterator.hasNext()) {
                    myList.add(iterator.next());
                }

                transitions.forEach(t -> myPair.add(t));
                System.out.println("myPair: " + myPair);

                System.out.println("first element: " + myList.get(0).equals(x0));
                // System.out.println("inside elements:
                // "+myList.subList(1,myList.size()-1).containsAll(myPair.stream().map(p->p.getX()).));//.contains(transitions));
                // System.out.println("last element:
                // "+acceptanceInputs.forEach(e->myList.get(myList.size()-1).equals(e)));

                return myList.get(0).equals(x0)
                        && myList.subList(1, myList.size() - 1).containsAll(myPair)
                        && myList.get(myList.size() - 1).equals(acceptanceInputs.iterator().next());
            }

        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        return recursive(
                x -> x.equals(x0)
                        ? Optional.of(next.apply(x0).map(y -> fromIteration(y, next))
                                .orElse(fromFinitePossibilities(Set.of(Collections.emptyList()))))
                        : Optional.empty(),
                false);
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                while (iterator.hasNext()) {
                    return nextParser.apply(iterator.next()).map(p -> p.accept(iterator)).orElse(false);
                }
                return isFinal;
            }

        };
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        return recursive(x1 -> x1.equals(x) ? Optional.of(parser) : Optional.empty(), false);
    }

}
