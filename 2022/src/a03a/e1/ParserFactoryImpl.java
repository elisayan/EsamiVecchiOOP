package a03a.e1;

import java.util.*;
import java.util.function.Function;

public class ParserFactoryImpl implements ParserFactory {

    @Override
    public <X> Parser<X> fromFinitePossibilities(Set<List<X>> acceptedSequences) {
        return new Parser<X>() {

            @Override
            public boolean accept(Iterator<X> iterator) {
                for (List<X> acceptedList : acceptedSequences) {
                    if(acceptedList.iterator().equals(iterator)){
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
                Deque<X> copy = new LinkedList<>();
                copy.add(x0);
                while(iterator.hasNext()){
                    copy.add(iterator.next());
                }
                List<Pair<X,X>> pairList = new LinkedList<>();
                for (Pair<X,X> pair : pairList) {
                    pairList.add(new Pair<X,X>(copy.poll(), iterator.next()));
                     return transitions.contains(pair) && pair.getY().equals(acceptanceInputs.iterator().next());
                }             
                return false;
               
            }
            
        };
    }

    @Override
    public <X> Parser<X> fromIteration(X x0, Function<X, Optional<X>> next) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromIteration'");
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
