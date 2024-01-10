package a01c.e1;

import java.util.*;

public class TimeSheetWithBounds implements TimeSheet {

    private final TimeSheet base;
    private final Map<String, Integer> bounds;
    private final boolean perActivity;

    public TimeSheetWithBounds(TimeSheet base, Map<String, Integer> bounds, boolean perActivity) {
        this.base = base;
        this.bounds = bounds;
        this.perActivity = perActivity;
    }

    @Override
    public Set<String> activities() {
        return base.activities();
    }

    @Override
    public Set<String> days() {
        return base.days();
    }

    @Override
    public int getSingleData(String activity, String day) {
        return base.getSingleData(activity, day);
    }

    @Override
    public boolean isValid() {
        if (!base.isValid()) {
            return false;
        }

        if (perActivity) {
            for (String activity : base.activities()) {
                int sum = base.activities().stream().mapToInt(day -> base.getSingleData(activity, day)).sum();

                if (sum > bounds.getOrDefault(activity, Integer.MAX_VALUE)) {
                    return false;
                }
            }
        } else {
            // for (String day : base.days()) {
            //     int sum = base.activities().stream().mapToInt(activity -> base.getSingleData(activity, day)).sum();

            //     if (sum > bounds.getOrDefault(day, Integer.MAX_VALUE)) {
            //         return false;
            //     }
            // }

            for (String activity : base.activities()) {
                int sum = 0;
                for (String day : base.days()) {
                    if (activity.equals(day)) {
                        sum += getSingleData(activity, day);
                    }
                }
    
                if (sum > bounds.getOrDefault(activity, Integer.MAX_VALUE)) {
                    return false;
                }
            }
        }

        return true;
    }
}
