package a01a.e1;

import java.util.*;
import java.util.stream.*;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                int sum = 0;
                var sumList = new LinkedList<Integer>();
                for (int i = 0; i < list.size(); i += 3) {
                    List<Integer> tripletta = list.subList(i, Math.min(i + 3, list.size()));
                    sum = tripletta.stream().mapToInt(Integer::intValue).sum();
                    sumList.add(sum);
                }
                return sumList;
            }

        };
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X,List<X>>() {

            @Override
            public List<List<X>> combine(List<X> list) {
                var sumList = new LinkedList<List<X>>();
                for (int i = 0; i < list.size(); i += 3) {
                    List<X> tripletta = list.subList(i, Math.min(i + 3, list.size()));
                    sumList.add(new LinkedList<>(tripletta));
                }
                return sumList;
            }

        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer,Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                
                var output = new LinkedList<Integer>();
                int value = 0;
                int lastElement = list.get(list.size() - 1);
                for (Integer element : list) {
                    if (element.equals(0)) {
                        output.add(value);
                        value = 0;
                    } else {
                        value++;
                    }
                }
                if (lastElement != 0) {
                    output.add(value);
                }
                return output;
            }
            
        };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X,Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                // List<Y> result = new LinkedList<>();  
                // for (X x : list) {
                //     result.add(function.apply(x));
                // }   
                // return result;
                final List<Y> result = list.stream().map(function).collect(Collectors.toCollection(LinkedList::new));
                return result;             
            }
            
        };
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return new SubsequenceCombiner<Integer,List<Integer>>() {

            @Override
            public List<List<Integer>> combine(List<Integer> list) {
                var result = new LinkedList<List<Integer>>();
                var sum100List = new LinkedList<Integer>();
                int sum = 0;
                for (Integer value : list) {
                    sum100List.add(value);
                    
                    sum = sum100List.stream().mapToInt(Integer::intValue).sum();
                    if (sum >= threshold || value.equals(list.get(list.size() - 1))) {
                        result.add(new LinkedList<>(sum100List));
                        sum100List.clear();
                    }
                }
                return result;
            }
            
        };
    }

}
