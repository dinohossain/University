package com.dating.app.idateu;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgeCalculator

    {
        public static final double DAYS_IN_YEAR = 365.242;
        public static final double DAYS_IN_MONTH = DAYS_IN_YEAR / 12d;

        public static int ageCalculation(Date dob) {
            DateFormat sDay = new SimpleDateFormat("dd");
            DateFormat sMonth = new SimpleDateFormat("MM");
            DateFormat sYear = new SimpleDateFormat("yyyy");

            int year = Integer.valueOf(sYear.format(dob));
            int month = Integer.valueOf(sMonth.format(dob));
            int day = Integer.valueOf(sDay.format(dob));

            // Number of days...
            double time = toDays(year, month, day);
            // One day less of the year 2015...

            Date modern = new Date();

            double today = toDays(Integer.valueOf(sYear.format(modern)),
                    (Integer.valueOf(sMonth.format(modern))),
                    (Integer.valueOf(sDay.format(modern)))); // today...

            double diff = today - time;

            int years = (int)Math.round(diff / DAYS_IN_YEAR);
            double over = diff % DAYS_IN_YEAR;
            int months = (int)(over / DAYS_IN_MONTH);
            over = over % DAYS_IN_MONTH;
            int days = (int) over;

        return years;
        }

        public static double toDays(int year, int month, int day) {
            return (year * DAYS_IN_YEAR) + (month * DAYS_IN_MONTH) + day;
        }

    }
