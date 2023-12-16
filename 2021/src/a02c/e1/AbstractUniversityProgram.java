package a02c.e1;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public abstract class AbstractUniversityProgram implements UniversityProgram {

    private Map<String, Pair<Set<Group>, Integer>> coursesMap = new HashMap<>();

    @Override
    public void setCourses(Map<String, Pair<Set<Group>, Integer>> map) {
        this.coursesMap = map;
    }

    @Override
    public boolean isValid(Set<String> courses) {
        for (Pair<Predicate<Group>, Predicate<Integer>> constraint : this.getConstraints()) {
            if (!isConstraintSatisfied(constraint, courses)) {
                return false;
            }
        }
        return true;
    }

    private boolean isConstraintSatisfied(Pair<Predicate<Group>, Predicate<Integer>> constraint, Set<String> courses) {
        int credits = 0;
        for (String course : courses) {
            if (this.coursesMap.containsKey(course)) {
                Set<Group> groups = this.coursesMap.get(course).get1();
                if (groups.stream().anyMatch(constraint.get1())) {
                    credits += this.coursesMap.get(course).get2();
                }
            }
        }
        return constraint.get2().test(credits);
    }

    protected abstract Set<Pair<Predicate<Group>, Predicate<Integer>>> getConstraints();
}
