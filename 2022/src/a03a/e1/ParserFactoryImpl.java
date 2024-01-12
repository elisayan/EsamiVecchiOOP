package a03a.e1;

import java.util.*;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory {

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> copy = new LinkedList<>();
                while (iterator.hasNext()) {
                    copy.add(iterator.next());
                }
                for (List<X> acceptedList : acceptedSequences) {
                    if (copy.equals(acceptedList)) {
                        return true;
                    }
                }
                return false;
            }

        };
    }

    @Override
    public <X> Parser<X> fromGraph(X x0, Set<Pair<X, X>> transitions, Set<X> acceptanceInputs) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                if (!iterator.hasNext()) {
                    return false;
                }

                Deque<X> copy = new LinkedList<>();
                List<Pair<X, X>> pairList = new LinkedList<>();
                copy.add(x0);

                while (iterator.hasNext()) {
                    X value = iterator.next();
                    copy.add(value);
                    pairList.add(new Pair<X, X>(copy.poll(), value));
                }

                for (Pair<X, X> pair : pairList) {
                    if (!transitions.contains(pair)) {
                        return false;
                    }
                }

                return pairList.get(pairList.size() - 1).getY().equals(acceptanceInputs.iterator().next());

            }

        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {

        return recursive(x -> x.equals(x0)
                ? Optional.of(next.apply(x0).map(y -> fromIteration(y, next))
                        .orElse(fromFinitePossibilities(Set.of(Collections.emptyList()))))  
                : Optional.empty(), false);

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
