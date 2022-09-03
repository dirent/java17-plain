package de.dirent;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Brueckentage {

    public static void main( String[] args ) {

        LocalDate start = new LocalDate( 2022, 12, 27 );
        LocalDate end = new LocalDate( 2024, 01, 06 ).plusDays(1);

        Days _2023 = Days.daysBetween( start, end );
        System.out.println( "Days count: " + _2023.getDays() );

        Brueckentage bt = new Brueckentage();

        DateTimeFormatter fmt = DateTimeFormat.forPattern("E dd.MM.yyyy");
        LocalDate day = start;
        while(day.isBefore(end) ) {
            System.out.println(fmt.print(day) + " | " + day.dayOfWeek().get() + " | " + bt.getFt(day));
            day = day.plusDays(1);
        }
    }

    private Map<LocalDate, String> feiertage;

    public Brueckentage() {
        this.feiertage = readFeiertage();
    }

    public String getFt( LocalDate day ) {
        if( this.feiertage.containsKey(day) ) {
            return this.feiertage.get(day);
        }
        return "";
    }

    private Map<LocalDate, String> readFeiertage() {
        Map<LocalDate, String> feiertage = new HashMap<>();
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
                    feiertage.put( dtf.parseLocalDate(parts[0]), parts[1] );
                }
            }
        }
        return feiertage;
    }
}
