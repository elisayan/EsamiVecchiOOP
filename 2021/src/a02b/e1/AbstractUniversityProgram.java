package a02b.e1;

import java.util.*;

public abstract class AbstractUniversityProgram implements UniversityProgram {

    Map<String, Pair<Sector, Integer>> courses = new HashMap<>();

    @Override
    public void addCourse(String name, Sector sector, int credits) {
        Pair<Sector, Integer> pair = new Pair<Sector, Integer>(sector, credits);
        courses.put(name, pair);

    }
}
