package a02a.e1;

import static a02a.e1.Diet.Nutrient.CARBS;
import static a02a.e1.Diet.Nutrient.PROTEINS;
import static a02a.e1.Diet.Nutrient.FAT;

import java.util.*;
import java.util.function.Predicate;

import a02a.e1.Diet.Nutrient;

public class DietFactoryImpl implements DietFactory {

    @Override
    public Diet standard() {
        return new AbstractDiet() {

            @Override
            protected Set<Pair<Predicate<Nutrient>, Predicate<Double>>> getConstraint() {
                return Set.<Pair<Predicate<Nutrient>, Predicate<Double>>>of(
                        new Pair<>(n -> true, d -> d >= 1500 && d <= 2000));
            }
        };
    }

    @Override
    public Diet lowCarb() {
        return new AbstractDiet() {

            @Override
            protected Set<Pair<Predicate<Nutrient>, Predicate<Double>>> getConstraint() {
                return Set.<Pair<Predicate<Nutrient>, Predicate<Double>>>of(
                        new Pair<>(n -> true, d -> d >= 1000 && d <= 1500),
                        new Pair<>(c -> c == CARBS, d -> d <= 300));
            }
        };
    }

    @Override
    public Diet highProtein() {
        return new AbstractDiet() {

            @Override
            protected Set<Pair<Predicate<Nutrient>, Predicate<Double>>> getConstraint() {
                return Set.<Pair<Predicate<Nutrient>, Predicate<Double>>>of(
                        new Pair<>(n -> true, d -> d >= 2000 && d <= 2500),
                        new Pair<>(c -> c == CARBS, d -> d <= 300),
                        new Pair<>(p -> p == PROTEINS, d -> d >= 1300));
            }

        };
    }

    @Override
    public Diet balanced() {

        return new AbstractDiet() {

            @Override
            protected Set<Pair<Predicate<Nutrient>, Predicate<Double>>> getConstraint() {
                return Set.<Pair<Predicate<Nutrient>, Predicate<Double>>>of(
                        new Pair<>(n -> true, d -> d >= 1600 && d <= 2000),
                        new Pair<>(c -> c == CARBS, d -> d >= 600),
                        new Pair<>(p -> p == PROTEINS, d -> d >= 600),
                        new Pair<>(f -> f == FAT, d -> d >= 400),
                        new Pair<>(s -> s == FAT || s == PROTEINS, d -> d <= 1100));
            }

        };
    }
}