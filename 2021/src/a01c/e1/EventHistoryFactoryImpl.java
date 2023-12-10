package a01c.e1;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory {

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        return new EventHistory<E>() {

            private Iterator<Map.Entry<Double, E>> elements = map.entrySet().stream()
                    .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())).iterator();
            private Map.Entry<Double, E> currentElement = elements.next();
            @Override
            public double getTimeOfEvent() {
                return currentElement.getKey();
            }

            @Override
            public E getEventContent() {
                return currentElement.getValue();
            }

            @Override
            public boolean moveToNextEvent() {
                if(elements.hasNext()) {
                    currentElement = elements.next();
                    return true;
                }else {
                    return false;
                }
            }
            
        };
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
