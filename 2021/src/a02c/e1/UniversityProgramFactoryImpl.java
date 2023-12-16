package a02c.e1;

import static a02c.e1.UniversityProgram.Group.*;

import java.util.*;
import java.util.function.*;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    public UniversityProgram flexible() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Group>, Predicate<Integer>>> getConstraints() {
                return Set.<Pair<Predicate<Group>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, d -> d == 48));
            }

        };
    }

    @Override
    public UniversityProgram fixed() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Group>, Predicate<Integer>>> getConstraints() {
                return Set.<Pair<Predicate<Group>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, d -> d == 60),
                        new Pair<>(n -> n == MANDATORY, d -> d == 12),
                        new Pair<>(n -> n == OPTIONAL, d -> d == 36),
                        new Pair<>(n -> n == THESIS, d -> d == 12));
            }
        };
    }

    @Override
    public UniversityProgram balanced() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Group>, Predicate<Integer>>> getConstraints() {
                return Set.<Pair<Predicate<Group>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, d -> d == 60),
                        new Pair<>(n -> n == MANDATORY, d -> d == 24),
                        new Pair<>(n -> n == OPTIONAL, d -> d >= 24),
                        new Pair<>(n -> n == FREE, d -> d <= 12),
                        new Pair<>(n -> n == THESIS, d -> d <= 12));
            }
        };
    }

    @Override
    public UniversityProgram structured() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Group>, Predicate<Integer>>> getConstraints() {
                return Set.<Pair<Predicate<Group>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, d -> d == 60),
                        new Pair<>(n -> n == MANDATORY, d -> d == 12),
                        new Pair<>(n -> n == OPTIONAL_A, d -> d >= 6),
                        new Pair<>(n -> n == OPTIONAL_B, d -> d >= 6),
                        new Pair<>(n -> n == OPTIONAL_A || n == OPTIONAL_B, d -> d == 30),
                        new Pair<>(n -> n == FREE || n==THESIS, d -> d == 18));
            }
        };
    }

}
