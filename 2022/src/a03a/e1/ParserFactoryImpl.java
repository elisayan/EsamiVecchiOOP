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
                while(iterator.hasNext()){
                    copy.add(iterator.next());
                }
                for (List<X> acceptedList : acceptedSequences) {
                        if(copy.equals(acceptedList)){
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
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                List<X> correctList = new LinkedList<>();
                List<X> copyList = new LinkedList<>();
                X first = iterator.next();
                copyList.add(first);
                correctList.add(x0);
                if(first.equals(x0)){
                    while (iterator.hasNext()) {
                        Optional<X> correct = next.apply(first);
                        if (correct.isPresent()) {
                            first=iterator.next();
                            copyList.add(first);
                            correctList.add(correct.get());
                            
                        }
                        
                    }
                }
                return correctList.equals(copyList);
            }
            
        };
    }

    @Override
    public <X> Parser<X> recursive(Function<X, Optional<Parser<X>>> nextParser, boolean isFinal) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'recursive'");
    }

    @Override
    public <X> Parser<X> fromParserWithInitial(X x, Parser<X> parser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromParserWithInitial'");
    }

}
