package a01c.e1;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory {

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromMap'");
    }

    @Override
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromIterators'");
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromListAndDelta'");
    }

    @Override
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromRandomTimesAndSupplier'");
    }

    @Override
    public EventHistory<String> fromFile(String file) throws IOException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromFile'");
    }

}
