package a01c.e1;

import java.io.IOException;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.junit.runner.FilterFactory.FilterNotCreatedException;

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
                } else {
                    return false;
                }
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromIterators(Iterator<Double> times, Iterator<E> content) {
        return new EventHistory<E>() {

            @Override
            public double getTimeOfEvent() {
                return times.next();
            }

            @Override
            public E getEventContent() {
                return content.next();
            }

            @Override
            public boolean moveToNextEvent() {
                return times.hasNext() && content.hasNext();
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromListAndDelta(List<E> content, double initial, double delta) {
        return new EventHistory<E>() {

            private int index = 0;
            private int indexList = 0;
            @Override
            public double getTimeOfEvent() {
                return initial + delta * index++;
            }

            @Override
            public E getEventContent() {
                return content.get(indexList++);
            }

            @Override
            public boolean moveToNextEvent() {
                return indexList < content.size();
            }
            
        };
    }

    @Override
    public <E> EventHistory<E> fromRandomTimesAndSupplier(Supplier<E> content, int size) {
        return new EventHistory<E>() {

            private int counter = 0;
            @Override
            public double getTimeOfEvent() {
                return Math.random();
            }

            @Override
            public E getEventContent() {
                return content.get();
            }

            @Override
            public boolean moveToNextEvent() {
                return ++counter < size;
            }
            
        };
    }

    @Override
    public EventHistory<String> fromFile(String file) throws IOException {
        return new EventHistory<String>() {

            private BufferedReader content = new BufferedReader(new FileReader(file));
            private String[] events;
            private int counter = 0;
            @Override
            public double getTimeOfEvent() {
                try {
                    events = content.readLine().split(":");
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return Double.parseDouble(events[counter++]);
            }

            @Override
            public String getEventContent() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'getEventContent'");
            }

            @Override
            public boolean moveToNextEvent() {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'moveToNextEvent'");
            }
            
        };
    }

}
