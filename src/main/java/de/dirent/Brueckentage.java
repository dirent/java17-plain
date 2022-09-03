package de.dirent;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

public class Brueckentage {

    public static void main( String[] args ) {

        LocalDate start = new LocalDate( 2022, 12, 27 );
        LocalDate end = new LocalDate( 2024, 01, 06 ).plusDays(1);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("E dd.MM.yyyy");
        LocalDate day = start;
        while(day.isBefore(end) ) {
            System.out.println( fmt.print(day) );
            day = day.plusDays(1);
        }

        Days _2023 = Days.daysBetween( start, end );
        System.out.println( "2023 hat " + _2023.getDays() + " Tage.");
    }
}
