package a02b.e1;

import static a02b.e1.UniversityProgram.Sector.*;

import java.util.*;
import java.util.function.Predicate;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    public UniversityProgram flexible() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Sector>, Predicate<Integer>>> getConstraint() {
                return Set.<Pair<Predicate<Sector>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, c -> c == 60));
            }
        };
    }

    @Override
    public UniversityProgram scientific() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Sector>, Predicate<Integer>>> getConstraint() {
                return Set.<Pair<Predicate<Sector>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, c -> c == 60),
                        new Pair<>(n -> n == MATHEMATICS, c -> c >= 12),
                        new Pair<>(n -> n == COMPUTER_SCIENCE, c -> c >= 12),
                        new Pair<>(n -> n == PHYSICS, c -> c >= 12));
            }
        };

    }

    @Override
    public UniversityProgram shortComputerScience() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Sector>, Predicate<Integer>>> getConstraint() {
                return Set.<Pair<Predicate<Sector>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, c -> c >= 48),
                        new Pair<>(n -> n == COMPUTER_ENGINEERING || n == COMPUTER_SCIENCE, c -> c >= 30));
            }

        };
    }

    @Override
    public UniversityProgram realistic() {
        return new AbstractUniversityProgram() {

            @Override
            protected Set<Pair<Predicate<Sector>, Predicate<Integer>>> getConstraint() {
                return Set.<Pair<Predicate<Sector>, Predicate<Integer>>>of(
                        new Pair<>(n -> true, c -> c == 120),
                        new Pair<>(n -> n == COMPUTER_ENGINEERING || n == COMPUTER_SCIENCE, c -> c >= 60),
                        new Pair<>(n -> n == MATHEMATICS || n == PHYSICS, c -> c <= 18),
                        new Pair<>(n -> n == THESIS, c -> c >= 24));
            }

        };
    }

}
