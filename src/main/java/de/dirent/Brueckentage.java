package de.dirent;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Brueckentage {

    public static void main( String[] args ) {

        LocalDate start = new LocalDate( 2022, 12, 27 );
        LocalDate end = new LocalDate( 2024, 01, 06 ).plusDays(1);

        Days _2023 = Days.daysBetween( start, end );
        System.out.println( "Days count: " + _2023.getDays() );

        List<LocalDate> feiertage = readFeiertage();
        System.out.println( "Read " + feiertage.size() + " public holidays." );

        DateTimeFormatter fmt = DateTimeFormat.forPattern("E dd.MM.yyyy");
        LocalDate day = start;
        while(day.isBefore(end) ) {
            System.out.println( fmt.print(day) + " | " + day.dayOfWeek().get() );
            day = day.plusDays(1);
        }
    }

    public static List<LocalDate> readFeiertage() {
        List<LocalDate> feiertage = new ArrayList<>();
        InputStream csvStream = Brueckentage.class.getResourceAsStream("nrw.csv");
        if( csvStream == null ) {
            System.err.println( "No input csv found." );
            return feiertage;
        }
        try( Scanner csv = new Scanner(csvStream) ) {

            // skip first line containing the heading
            if( csv.hasNextLine() ) csv.nextLine();

            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd.MM.yyyy");
            while( csv.hasNextLine() ) {
                String[] parts = csv.nextLine().split(";");
                if( parts.length == 2 ) {
                    feiertage.add( dtf.parseLocalDate(parts[0]) );
                }
            }
        }
        return feiertage;
    }
}
