package com.cnesoa.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Maxime on 29/04/2016.
 * Class used to calculate the start and end of current schoolyear
 */
public class DateCalculator {

    public static final Calendar cal = new GregorianCalendar();

    public static LocalDate getStartOfTheYear(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate date;
        if (now.getMonthValue() >= Month.SEPTEMBER.getValue())
            date = LocalDate.of(now.getYear(), Month.SEPTEMBER, 1);
        else
            date = LocalDate.of(now.getYear() -1, Month.SEPTEMBER, 1);
        return date;
    }

    public static LocalDate getEndOfTheYar(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate date;
        if (now.getMonthValue() >= Month.SEPTEMBER.getValue())
            date = LocalDate.of(now.getYear() +1, Month.AUGUST, 1);
        else
            date = LocalDate.of(now.getYear(), Month.AUGUST, 1);
        return date;
    }
}
