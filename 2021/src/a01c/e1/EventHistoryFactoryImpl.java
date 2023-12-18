package a01c.e1;

import java.io.IOException;
import java.util.*;
import java.util.function.Supplier;

public class EventHistoryFactoryImpl implements EventHistoryFactory {

    @Override
    public <E> EventHistory<E> fromMap(Map<Double, E> map) {
        return new EventHistory<E>() {

            private List<Double> keyHistory = map.keySet().stream().sorted().toList();
            //private List<E> valuesHistory = map.values().stream().sorted().toList();
            private Double actualKey = keyHistory.iterator().next();
            @Override
            public double getTimeOfEvent() {
                return actualKey;
            }

            @Override
            public E getEventContent() {
                return map.get(actualKey);
            }

            @Override
            public boolean moveToNextEvent() {
                if(keyHistory.iterator().hasNext()){
                    actualKey=keyHistory.iterator().next();
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
