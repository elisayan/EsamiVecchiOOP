package a01b.e1;

import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class FlattenerFactoryImpl implements FlattenerFactory {

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                var result = new LinkedList<Integer>();
                for (List<Integer> values : list) {
                    int sum = values.stream().mapToInt(Integer::intValue).sum();
                    result.add(sum);
                }
                return result;
            }
            
        };
    }

    @Override
    public <X> Flattener<X, X> flattenAll() {
        return new Flattener<X,X>() {

            @Override
            public List<X> flatten(List<List<X>> list) {
                var result = new LinkedList<X>();
                for (List<X> x : list) {
                    for (X x2 : x) {
                        result.add(x2);
                    }
                }
                return result;
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String,String>() {

            private String concatenate(List<String> list) {
                return String.join("", list);
            }

            @Override
            public List<String> flatten(List<List<String>> list) {
                
                var result = new LinkedList<String>();
                var temp = new LinkedList<String>();
                for (List<String> values : list) {
                    temp.add(concatenate(values));
                    if (temp.size() % 2 == 0) {
                        result.add(concatenate(temp));
                        temp.clear();
                    }
                }
                if(!temp.isEmpty()){
                    result.add(concatenate(list.get(list.size()-1)));
                }
                return result;
            }
            
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        // return new Flattener<I,O>() {

        //     @Override
        //     public List<O> flatten(List<List<I>> list) {
        //         // var result = new LinkedList<O>();
        //         // for (List<I> input : list) {
        //         //     result.add(mapper.apply(input));
        //         // }
        //         // return result;

        //         final List<O> result = list.stream().map(mapper).collect(Collectors.toCollection(LinkedList::new));
        //         return result;
        //     }
            
        // };

        return (list) -> list.stream().map(i -> mapper.apply(i)).toList();
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                Map<Integer, Integer> sum = new HashMap<>();
                Queue<List<Integer>> result = new LinkedList<>(list);
                int i = 0;
                while (!result.isEmpty()) {
                    List<Integer> h = result.poll();
                    while (i < h.size()) {
                        sum.merge(i, h.get(i), (x, y) -> x + y);
                        i++;
                    }
                    i = 0;
                }
                return sum.values().stream().toList();
            }

        };
        
    }

}
