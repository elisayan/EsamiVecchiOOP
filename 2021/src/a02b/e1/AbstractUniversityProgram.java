package a02b.e1;

import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public abstract class AbstractUniversityProgram implements UniversityProgram {

    Map<String, Pair<Sector, Integer>> coursesMap = new HashMap<>();

    @Override
    public void addCourse(String name, Sector sector, int credits) {
        coursesMap.put(name, new Pair<Sector, Integer>(sector, credits));
    }

    @Override
    public boolean isValid(Set<String> courseNames) {
        List<Pair<Sector,Integer>> courses = courseNames.stream().map(coursesMap::get).collect(Collectors.toList());
        
        return getConstraint().stream().allMatch(c->isConstraintValid(c, courses));
    }

    private boolean isConstraintValid(Pair<Predicate<Sector>, Predicate<Integer>> p,
            List<Pair<Sector, Integer>> courses) {
        return p.get2().test(courses.stream().filter(e -> p.get1().test(e.get1()))
                .mapToInt(c -> c.get2()).sum());
    }

    protected abstract Set<Pair<Predicate<Sector>, Predicate<Integer>>> getConstraint();
}
