package de.dirent;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.InputStream;
import java.util.*;

public class Brueckentage {

    public static void main( String[] args ) {
        int year = 2023;
        List<LocalDate> brueckentage = new Brueckentage(year).getBrueckentage();

        DateTimeFormatter fmt = DateTimeFormat.forPattern("E dd.MM.yyyy");
        System.out.print( "Brückentage in " + year + ": " );
        brueckentage.forEach( bt -> System.out.print(fmt.print(bt) + "  ") );
    }

    private Map<LocalDate, String> feiertage;
    private List<LocalDate> brueckentage;

    public List<LocalDate> getBrueckentage() {
        return this.brueckentage;
    }

    public Brueckentage( int year ) {
        this.feiertage = readFeiertage();

        LocalDate start = new LocalDate( year-1, 12, 27 );
        LocalDate end = new LocalDate( year+1, 01, 06 ).plusDays(1);

        LocalDate day = start;
        List<List<LocalDate>> aggregated = new ArrayList<>();
        List<LocalDate> days = new ArrayList<>();
        aggregated.add(days);
        while(day.isBefore(end) ) {
            List<LocalDate> currentDays = aggregated.get( aggregated.size()-1 );
            if( currentDays.isEmpty() ) {
                currentDays.add(day);
            } else if( (!isAt(currentDays.get(0))  &&  isAt(day))  ||  (isAt(currentDays.get(0))  &&  !isAt(day)) ) {
                List<LocalDate> nextDays = new ArrayList<>();
                nextDays.add(day);
                aggregated.add(nextDays);
            } else {
                currentDays.add(day);
            }

            day = day.plusDays(1);
        }

        this.brueckentage = new ArrayList<>();
        aggregated.stream().filter( agg -> agg.size() == 1  &&  isAt(agg.get(0) ) ).forEach( agg -> {
            this.brueckentage.add(agg.get(0));
        });
    }

    protected boolean isFt( LocalDate day ) {
        return this.feiertage.containsKey(day);
    }

    protected boolean isAt( LocalDate day ) {
        return day.dayOfWeek().get() <= 5  &&  !isFt(day);
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
