package a02b.e1;

import static a02b.e1.UniversityProgram.Sector.COMPUTER_ENGINEERING;
import static a02b.e1.UniversityProgram.Sector.COMPUTER_SCIENCE;
import static a02b.e1.UniversityProgram.Sector.MATHEMATICS;
import static a02b.e1.UniversityProgram.Sector.PHYSICS;

import java.util.*;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory {

    @Override
    public UniversityProgram flexible() {
        return new UniversityProgram() {

            Map<String, Pair<Sector, Integer>> courses = new HashMap<>();
            @Override
            public void addCourse(String name, Sector sector, int credits) {
                Pair<Sector, Integer> pair = new Pair<Sector,Integer>(sector, credits);
                courses.put(name, pair);
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                List<Integer> credits = new LinkedList<>();
                for (String name : courseNames) {
                    credits.add(courses.get(name).get2());
                }
                return credits.stream().mapToInt(Integer::intValue).sum() == 60;
            }
            
        };
    }

    @Override
    public UniversityProgram scientific() {
        return new UniversityProgram() {

            private Map<String, Pair<Sector, Integer>> courses = new HashMap<>();
            @Override
            public void addCourse(String name, Sector sector, int credits) {
                Pair<Sector, Integer> pair = new Pair<Sector,Integer>(sector, credits);
                courses.put(name, pair);
            }

            @Override
            public boolean isValid(Set<String> courseNames) {                
                List<Integer> credits = new LinkedList<>();
                int sumCredits = 0;
                int sumMath = 0;
                int sumComputerScience = 0;
                int sumPhysics = 0;
                for (String name : courseNames) {
                    credits.add(courses.get(name).get2());
                    sumCredits = credits.stream().mapToInt(Integer::intValue).sum();
                    credits = new LinkedList<>();
                    if (courses.get(name).get1().equals(MATHEMATICS)) {
                        credits.add(courses.get(name).get2());
                        sumMath = credits.stream().mapToInt(Integer::intValue).sum();
                    } else if (courses.get(name).get1().equals(COMPUTER_SCIENCE)) {
                        credits.add(courses.get(name).get2());
                        sumComputerScience = credits.stream().mapToInt(Integer::intValue).sum();
                    } else if (courses.get(name).get1().equals(PHYSICS)) {
                        credits.add(courses.get(name).get2());
                        sumPhysics = credits.stream().mapToInt(Integer::intValue).sum();
                    }
                }
                return sumCredits == 60 && sumMath >= 12 && sumComputerScience >= 12 && sumPhysics >= 12;
            }
            
        };
    }

    @Override
    public UniversityProgram shortComputerScience() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'shortComputerScience'");
    }

    @Override
    public UniversityProgram realistic() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'realistic'");
    }

}
