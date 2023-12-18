package a01b.e1;

import java.util.*;
import java.util.function.Predicate;

public class EventSequenceProducerHelpersImpl implements EventSequenceProducerHelpers {

    @Override
    public <E> EventSequenceProducer<E> fromIterator(Iterator<Pair<Double, E>> iterator) {
        return new EventSequenceProducer<E>() {

            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                return iterator.next();
            }
            
        };
    }

    @Override
    public <E> List<E> window(EventSequenceProducer<E> sequence, double fromTime, double toTime) {
        List<E> result = new LinkedList<>();
        Pair<Double, E> actual = sequence.getNext();

        while (actual.get1() < toTime) {
            if (actual.get1() >= fromTime && actual.get1() < toTime) {
                result.add(actual.get2());
            }
            actual = sequence.getNext();
        }

        return result;
    }

    @Override
    public <E> Iterable<E> asEventContentIterable(EventSequenceProducer<E> sequence) {
        return new Iterable<E>() {

            @Override
            public Iterator<E> iterator() {
                return new Iterator<E>() {
                    Pair<Double, E> pair;

                    @Override
                    public boolean hasNext() {
                        try {
                            pair = sequence.getNext();
                        } catch (NoSuchElementException e) {
                            return false;
                        }

                        return pair != null;
                    }

                    @Override
                    public E next() {
                        return pair.get2();
                    }

                };
            }

        };
    }

    @Override
    public <E> Optional<Pair<Double, E>> nextAt(EventSequenceProducer<E> sequence, double time) {
        Pair<Double, E> pair = sequence.getNext();
        while (pair != null) {
            if (pair.get1() > time) {
                return Optional.of(pair);
            }
            try {
                pair = sequence.getNext();
            } catch (NoSuchElementException e) {
                return Optional.empty();
            }

        }
        return Optional.empty();
    }

    @Override
    public <E> EventSequenceProducer<E> filter(EventSequenceProducer<E> sequence, Predicate<E> predicate) {
        return new EventSequenceProducer<E>() {
    
            @Override
            public Pair<Double, E> getNext() throws NoSuchElementException {
                Pair<Double, E> pair = sequence.getNext();
                while (pair != null) {
                    if (predicate.test(pair.get2())) {
                        return pair;
                    }
                    pair = sequence.getNext();
                }
                return pair;
            }
        };

    }

}
