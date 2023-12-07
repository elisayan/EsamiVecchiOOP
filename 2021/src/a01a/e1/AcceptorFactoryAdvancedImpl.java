package a01a.e1;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class AcceptorFactoryAdvancedImpl implements AcceptorFactory {

    @Override
    public Acceptor<String, Integer> countEmptyStringsOnAnySequence() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'countEmptyStringsOnAnySequence'");
    }

    @Override
    public Acceptor<Integer, String> showAsStringOnlyOnIncreasingSequences() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'showAsStringOnlyOnIncreasingSequences'");
    }

    @Override
    public Acceptor<Integer, Integer> sumElementsOnlyInTriples() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumElementsOnlyInTriples'");
    }

    @Override
    public <E, O1, O2> Acceptor<E, Pair<O1, O2>> acceptBoth(Acceptor<E, O1> a1, Acceptor<E, O2> a2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'acceptBoth'");
    }

    @Override
    public <E, O, S> Acceptor<E, O> generalised(S initial, BiFunction<E, S, Optional<S>> stateFun,
            Function<S, Optional<O>> outputFun) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generalised'");
    }

}
