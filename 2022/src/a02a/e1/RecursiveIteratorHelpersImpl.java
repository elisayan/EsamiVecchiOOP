package a02a.e1;

import java.util.*;

public class RecursiveIteratorHelpersImpl implements RecursiveIteratorHelpers {

    @Override
    public <X> RecursiveIterator<X> fromList(List<X> list) {
        if (list.isEmpty()) {
            return null;
        }
        return new RecursiveIterator<X>() {

            int i = 0;

            @Override
            public X getElement() {
                return list.get(i);
            }

            @Override
            public RecursiveIterator<X> next() {
                i++;
                if (i >= list.size()) {
                    return null;
                }
                return this;
            }

        };
    }

    @Override
    public <X> List<X> toList(RecursiveIterator<X> input, int max) {
        List<X> output = new LinkedList<>();
        output.add(input.getElement());
        var temp = input.next();
        while (output.size() < max && temp!=null) {
            output.add(temp.getElement());
            temp = temp.next();
        }
        return output;
    }

    @Override
    public <X, Y> RecursiveIterator<Pair<X, Y>> zip(RecursiveIterator<X> first, RecursiveIterator<Y> second) {
        return new RecursiveIterator<Pair<X,Y>>() {

            RecursiveIterator<X> a = first;
            RecursiveIterator<Y> b = second;

            @Override
            public Pair<X, Y> getElement() {
                return new Pair<X, Y>(a.getElement(), b.getElement());
            }

            @Override
            public RecursiveIterator<Pair<X, Y>> next() {
                a = a.next();
                b = b.next();
                return this;
            }

        };
    }

    @Override
    public <X> RecursiveIterator<Pair<X, Integer>> zipWithIndex(RecursiveIterator<X> iterator) {
        return new RecursiveIterator<Pair<X, Integer>>() {

            RecursiveIterator<X> element = iterator;
            int i = 0;

            @Override
            public Pair<X, Integer> getElement() {
                return new Pair<X, Integer>(element.getElement(), i);
            }

            @Override
            public RecursiveIterator<Pair<X, Integer>> next() {
                i++;
                element = element.next();
                if (element == null) {
                    return null;
                }
                return this;
            }

        };
    }

    @Override
    public <X> RecursiveIterator<X> alternate(RecursiveIterator<X> first, RecursiveIterator<X> second) {
        return new RecursiveIterator<X>() {

            RecursiveIterator<X> a = first;
            RecursiveIterator<X> b = second;
            Deque<X> history = new LinkedList<>();
            @Override
            public X getElement() {
                if (a != null) {
                    history.add(a.getElement());
                }

                if (b != null) {
                    history.add(b.getElement());
                }

                return history.poll();
            }

            @Override
            public RecursiveIterator<X> next() {
                if(a==null){
                    return null;
                }
                a = first.next();
                b = second.next();
                
                return this;
            }
            
        };
    }

}
