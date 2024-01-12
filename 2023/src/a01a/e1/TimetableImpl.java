package a01a.e1;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.*;

public class TimetableImpl implements Timetable {

    private Map<String, List<Pair<String,Integer>>> table = new HashMap<>();
    private BiFunction<String, String, Integer> filter;
    private int actualFilter=-1;

    public Timetable setFilter(BiFunction<String, String, Integer> bounds) {
        this.filter = bounds;
        return this;
    }

	@Override
    public Timetable addHour(String activity, String day) {
        List<Pair<String,Integer>> days;
        if (!table.containsKey(activity)) {
            days = new LinkedList<>();
        } else {
            days = table.get(activity);
        }

        if (filter != null) {
            System.out.println("applico filtro");
            actualFilter = filter.apply(activity, day);
        }
        days.add(new Pair<String, Integer>(day, actualFilter));
        table.put(activity, days);
        System.out.println("table: " + table);
        return this;
    }

    @Override
    public Set<String> activities() {
        return table.keySet();
    }

    @Override
    public Set<String> days() {
        return table.values().stream()
                .flatMap(v -> v.stream())
                .collect(Collectors.toSet()).stream().map(d->d.get1()).collect(Collectors.toSet());
        // return table.values().stream().collect(Collectors.toSet());
    }

    @Override
    public int getSingleData(String activity, String day) {
        if (actualFilter==-1) {
            return (int) table.getOrDefault(activity, new LinkedList<>())
                .stream().filter(v -> v.get1().contains(day)).count();
        }
        return table.getOrDefault(activity, new LinkedList<>()).stream().filter(v -> v.get1().contains(day))
                .collect(Collectors.toList()).stream().map(v -> v.get2()).mapToInt(Integer::intValue).sum();
    }

    @Override
    public int sums(Set<String> activities, Set<String> days) {
        // return activities.stream().map(a->table.
        // (int) table.entrySet().stream()
        // .filter(e->activities.contains(e.getKey()))
        // .count());
        if (actualFilter==-1) {
            return (int) activities.stream()
                    .flatMap(a -> days.stream().map(d -> getSingleData(a, d)))
                    .mapToInt(Integer::intValue).sum();
        }
        return 0;
    }
}
