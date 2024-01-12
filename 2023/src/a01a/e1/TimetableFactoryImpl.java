package a01a.e1;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class TimetableFactoryImpl implements TimetableFactory {

    @Override
    public Timetable empty() {
        return new TimetableImpl();
    }

    @Override
    public Timetable single(String activity, String day) {
        return new TimetableImpl().addHour(activity, day);
    }

    @Override
    public Timetable join(Timetable table1, Timetable table2) {
        TimetableImpl output = new TimetableImpl();
        Set<String> activities = new HashSet<>(table1.activities());
        activities.addAll(table2.activities());

        Set<String> days = new HashSet<>(table1.days());
        days.addAll(table2.days());

        for (String activity : activities) {
            for (String day : days) {
                int hours = table1.getSingleData(activity, day) + table2.getSingleData(activity, day);
                for (int i = 0; i < hours; i++) {
                    output.addHour(activity, day);
                }
            }
        }
       
        return output;
    }

    @Override
    public Timetable cut(Timetable table, BiFunction<String, String, Integer> bounds) {
        TimetableImpl output = new TimetableImpl();
        output.setFilter(bounds);
        for (String a : table.activities()) {
            for (String d : table.days()) {
                output.addHour(a, d);
            }
        }


        // for (String a : table.activities()) {
        //     for (String d : table.days()) {
        //         int allowedHours = bounds.apply(a, d);
        //         int currentHours = table.getSingleData(a, d);
        //         for (int i = 0; i <= Math.min(allowedHours, currentHours); i++) {
        //             output.addHour(a, d);
        //         }
        //     }
        // }

        
        
        return output;
        
        //return new Timetable() {

        //     Map<String,List<String>> table = new HashMap<>();

        //     @Override
        //     public Timetable addHour(String activity, String day) {
        //         List<String> days;
        //         if (!table.containsKey(activity)) {
        //             days= new LinkedList<>();
        //         } else {
        //             days=table.get(activity);
        //         }
        //         days.add(day);
        //         table.put(activity, days);
        //         System.out.println("table: "+table);
        //         return this;
        //     }

        //     @Override
        //     public Set<String> activities() {
        //         return table.keySet();
        //     }

        //     @Override
        //     public Set<String> days() {
        //         return table.values().stream()
        //                 .flatMap(v -> v.stream())
        //                 .collect(Collectors.toSet());
        //     }

        //     @Override
        //     public int getSingleData(String activity, String day) {
        //         return (int) table.getOrDefault(activity, new LinkedList<>())
        //         .stream().filter(v -> v.contains(day)).count();
        //     }

        //     @Override
        //     public int sums(Set<String> activities, Set<String> days) {
        //         return (int) activities.stream()
        //         .flatMap(a -> days.stream().map(d -> getSingleData(a, d)))
        //         .mapToInt(Integer::intValue).sum();
        //     }
            
        // };
         
    }

}
