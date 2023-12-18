package a01a.e1;

import java.util.*;
import java.util.function.Function;

public class SubsequenceCombinerFactoryImpl implements SubsequenceCombinerFactory {

    @Override
    public SubsequenceCombiner<Integer, Integer> tripletsToSum() {
        return new SubsequenceCombiner<Integer, Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> result = new LinkedList<>();
                List<Integer> triple = new LinkedList<>();
                for (Integer value : list) {
                    triple.add(value);
                    if (triple.size() == 3) {
                        result.add(triple.stream().mapToInt(Integer::intValue).sum());
                        triple = new LinkedList<>();
                    }
                }
                if (!triple.isEmpty()) {
                    result.add(triple.stream().mapToInt(Integer::intValue).sum());
                }

                return result;
            }

        };
    }

    @Override
    public <X> SubsequenceCombiner<X, List<X>> tripletsToList() {
        return new SubsequenceCombiner<X, List<X>>() {

            @Override
            public List<List<X>> combine(List<X> list) {
                List<List<X>> result = new LinkedList<>();
                List<X> triple = new LinkedList<>();
                for (X value : list) {
                    triple.add(value);
                    if (triple.size() == 3) {
                        result.add(new LinkedList<>(triple));
                        triple.clear();
                    }
                }
                if (!triple.isEmpty()) {
                    result.add(new LinkedList<>(triple));
                }
                return result;
            }

        };
    }

    @Override
    public SubsequenceCombiner<Integer, Integer> countUntilZero() {
        return new SubsequenceCombiner<Integer, Integer>() {

            @Override
            public List<Integer> combine(List<Integer> list) {
                List<Integer> result = new LinkedList<>();
                int count = 0;
                for (Integer value : list) {
                    count++;
                    if (value == 0) {
                        result.add(--count);
                        count = 0;
                    }
                }
                if (count != 0) {
                    result.add(count);
                }
                return result;
            }

        };
    }

    @Override
    public <X, Y> SubsequenceCombiner<X, Y> singleReplacer(Function<X, Y> function) {
        return new SubsequenceCombiner<X, Y>() {

            @Override
            public List<Y> combine(List<X> list) {
                List<Y> result = new LinkedList<>();
                for (X x : list) {
                    result.add(function.apply(x));
                }
                return result;
            }

        };
    }

    @Override
    public SubsequenceCombiner<Integer, List<Integer>> cumulateToList(int threshold) {
        return new SubsequenceCombiner<Integer, List<Integer>>() {

            @Override
            public List<List<Integer>> combine(List<Integer> list) {
                List<Integer> sum100 = new LinkedList<>();
                List<List<Integer>> result = new LinkedList<>();
                for (Integer value : list) {
                    sum100.add(value);
                    if (sum100.stream().mapToInt(Integer::intValue).sum() >= 100) {
                        result.add(new LinkedList<>(sum100));
                        sum100.clear();
                    }
                }
                if (!sum100.isEmpty()) {
                    result.add(new LinkedList<>(sum100));
                }
                return result;
            }

        };
    }

}
