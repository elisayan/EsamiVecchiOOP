package a01b.e1;

import java.util.*;
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

            @Override
            public List<String> flatten(List<List<String>> list) {
                var result = new LinkedList<String>();
                for (int i = 0; i < list.size(); i+=2) {
                    List<String> pair = list.subList(i, Math.min(i+2, list.size()));
                }
                return result;
            }
            
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'each'");
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumVectors'");
    }

}
