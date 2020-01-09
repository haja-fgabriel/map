package ubb.scs.map.ir.seminar.studentsmanagement.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import static ubb.scs.map.ir.seminar.studentsmanagement.utils.Calendar.*;

public class Weeks {
    public static int getWeekInYear(Date d) {
        return Integer.parseInt(new SimpleDateFormat("w").format(d));
    }

    public static int getWeekInSemester(Date d) {
        // FIXME - make sure the calculation of current week is correct

        int week = getWeekInYear(d);

        int yearBeginWeek = getWeekInYear(yearBegin);
        int holidayBeginWeek = getWeekInYear(holidayBegin);
        int holidayEndWeek = getWeekInYear(holidayEnd);

        if (week < holidayBeginWeek) {
            return week - yearBeginWeek + 1;
        } else if (week > holidayEndWeek) {
            return (week - holidayEndWeek) + (holidayBeginWeek - yearBeginWeek) + 1;
        } else
            return 0;
    }

    public static int getCurrentWeek() {
        return getWeekInSemester(new Date());
    }
}
