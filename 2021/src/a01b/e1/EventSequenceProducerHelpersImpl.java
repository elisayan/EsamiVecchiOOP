package a01b.e1;

import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;


public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers{

    public EventSequenceProducerHelpersImpl() {
        
    }

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return () -> iterator.next();
    }

    @Override
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        List<E> list = new LinkedList<>();
        Pair<Double, E> elem = sequence.getNext();
        while (elem.get1() < toTime) {
            if (elem.get1() >= fromTime) {
                list.add(elem.get2());
            }
            elem = sequence.getNext();            
        }                
        return list;
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        return () -> new Iterator<E>() {
            Pair<Double, E> elem;

            @Override
            public boolean hasNext() {
                try {
                    elem = sequence.getNext();
                } catch (NoSuchElementException e) {
                    return false;
                }
                return elem != null;
            }

            @Override
            public E next() {
                return elem.get2();
            }
        };
    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'nextAt'");
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'filter'");
    }
    
}
