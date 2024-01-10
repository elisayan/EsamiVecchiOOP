package a01c.e1;

import java.util.*;

public class TimeSheetFactoryImpl implements TimeSheetFactory {

    @Override
    public TimeSheet ofRawData(List<Pair<String, String>> data) {
        Set<String> activities = extractActivities(data);
        Set<String> days = extractDays(data);

        Map<Pair<String, String>, Integer> hoursMap = new HashMap<>();
        for (Pair<String, String> entry : data) {
            hoursMap.put(entry, hoursMap.getOrDefault(entry, 0) + 1);
        }

        return new SimpleTimeSheet(activities, days, hoursMap);
    }

    private Set<String> extractActivities(List<Pair<String, String>> data) {
        Set<String> activities = new HashSet<>();
        for (Pair<String, String> entry : data) {
            activities.add(entry.get1());
        }
        return activities;
    }

    private Set<String> extractDays(List<Pair<String, String>> data) {
        Set<String> days = new HashSet<>();
        for (Pair<String, String> entry : data) {
            days.add(entry.get2());
        }
        return days;
    }

    @Override
    public TimeSheet withBoundsPerActivity(List<Pair<String, String>> data, Map<String, Integer> boundsOnActivities) {
        TimeSheet baseSheet = ofRawData(data);
        return applyBoundsPerActivity(baseSheet, boundsOnActivities);
    }

    @Override
    public TimeSheet withBoundsPerDay(List<Pair<String, String>> data, Map<String, Integer> boundsOnDays) {
        TimeSheet basSheet = ofRawData(data);
        return applyBoundsPerDay(basSheet, boundsOnDays);
    }

    @Override
    public TimeSheet withBounds(List<Pair<String, String>> data, Map<String, Integer> boundsOnActivities,
            Map<String, Integer> boundsOnDays) {
        TimeSheet basSheet = ofRawData(data);
        return applyBounds(basSheet, boundsOnActivities, boundsOnDays);
    }

    private TimeSheet applyBoundsPerActivity(TimeSheet base, Map<String, Integer> bounds) {
        return new TimeSheetWithBounds(base, bounds, true);
    }

    private TimeSheet applyBoundsPerDay(TimeSheet base, Map<String, Integer> bounds) {
        return new TimeSheetWithBounds(base, bounds, false);
    }

    private TimeSheet applyBounds(TimeSheet base, Map<String, Integer> boundsPerActivity,
            Map<String, Integer> boundsPerDay) {
        TimeSheet sheetWithActivityBounds = applyBoundsPerActivity(base, boundsPerActivity);
        return applyBoundsPerDay(sheetWithActivityBounds, boundsPerDay);
    }
}
