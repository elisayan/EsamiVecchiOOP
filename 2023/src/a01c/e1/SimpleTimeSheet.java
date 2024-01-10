package a01c.e1;

import java.util.*;

public class SimpleTimeSheet implements TimeSheet {
    Set<String> activities;
    Set<String> days;
    Map<Pair<String, String>, Integer> hoursMap;

    public SimpleTimeSheet(Set<String> activities, Set<String> days, Map<Pair<String, String>, Integer> hoursMap) {
        this.activities = activities;
        this.days = days;
        this.hoursMap = hoursMap;
    }

    @Override
    public Set<String> activities() {
        return activities;
    }

    @Override
    public Set<String> days() {
        return days;
    }

    @Override
    public int getSingleData(String activity, String day) {
        Pair<String, String> key = new Pair<String, String>(activity, day);

        if (hoursMap.containsKey(key)) {
            return hoursMap.get(key);
        } else {
            return 0;
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }

    public int sumPerActivity(String activity) {
        int sum = 0;
        for (String day : days) {
            if (activity.equals(day)) {
                sum += getSingleData(activity, day);
            }
        }

        return sum;
    }

    public int sumPerDay(String day) {
        int sum = 0;
        for (String activity : activities) {
            sum += getSingleData(activity, day);
        }

        return sum;
    }
}
