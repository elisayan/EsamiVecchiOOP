package a02a.e1;

import java.util.*;
import java.util.function.*;

public abstract class AbstractDiet implements Diet {
    private Map<String, Map<Nutrient, Integer>> foods = new HashMap<>();

    @Override
    public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
        foods.put(name, nutritionMap);

    }

    @Override
    public boolean isValid(Map<String, Double> dietMap) {
        Map<Nutrient, Double> calories = new HashMap<>();
        for (Map.Entry<String, Double> entry : dietMap.entrySet()) {
            for (var entry2 : foods.get(entry.getKey()).entrySet()) {
                calories.merge(entry2.getKey(), entry.getValue() / 100.0 * entry2.getValue(), (a, b) -> a + b);

            }
        }
        return getConstraint().stream().allMatch((c -> isConstraintValid(c, calories)));
    }

    private boolean isConstraintValid(Pair<Predicate<Nutrient>, Predicate<Double>> value,
            Map<Nutrient, Double> calories) {
        return value.get2().test(calories.entrySet().stream().filter(e -> value.get1().test(e.getKey()))
                .mapToDouble(Map.Entry::getValue).sum());
    }

    protected abstract Set<Pair<Predicate<Nutrient>, Predicate<Double>>> getConstraint();
}
