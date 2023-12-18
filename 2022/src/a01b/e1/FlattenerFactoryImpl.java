package a01b.e1;

import java.util.*;
import java.util.function.Function;

public class FlattenerFactoryImpl implements FlattenerFactory {

    @Override
    public Flattener<Integer, Integer> sumEach() {
        return new Flattener<Integer,Integer>() {

            @Override
            public List<Integer> flatten(List<List<Integer>> list) {
                List<Integer> result = new LinkedList<>();
                for (List<Integer> value : list) {
                    result.add(value.stream().mapToInt(Integer::intValue).sum());
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
                List<X> result = new LinkedList<>();
                for (List<X> valueList : list) {
                    for (X x : valueList) {
                        result.add(x);
                    }
                }
                return result;
            }
            
        };
    }

    @Override
    public Flattener<String, String> concatPairs() {
        return new Flattener<String,String>() {

            private String concateList(List<List<String>> list){
                List<String> stringList = new LinkedList<>();
                for (List<String> valueList : list) {
                    for (String string : valueList) {
                        stringList.add(string);
                    }
                }
                return String.join("", stringList);
            }

            @Override
            public List<String> flatten(List<List<String>> list) {
                List<String> result = new LinkedList<>();
                List<List<String>> couple = new LinkedList<>();
                for (List<String> value : list) {
                    couple.add(value);
                    System.out.println("couple: "+couple);
                    if (couple.size() % 2 == 0) {
                        result.add(concateList(couple));
                        System.out.println("result: "+result);
                        couple.clear();
                    }
                }
                if (!couple.isEmpty()) {
                    result.add(concateList(couple));
                }
                return result;
            }
            
        };
    }

    @Override
    public <I, O> Flattener<I, O> each(Function<List<I>, O> mapper) {
        return new Flattener<I,O>() {

            @Override
            public List<O> flatten(List<List<I>> list) {
                List<O> result = new LinkedList<>();
                for (List<I> valueList : list) {
                    result.add(mapper.apply(valueList));
                }
                return result;
            }
            
        };
    }

    @Override
    public Flattener<Integer, Integer> sumVectors() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sumVectors'");
    }

}
