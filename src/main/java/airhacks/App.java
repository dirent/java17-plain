package airhacks;


import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author airhacks.com
 */
public class App {

    public static void main(String[] args) {
        System.out.println( "Hello Java 17!" );
        // Example: Remove all CR and LF characters from a string
        String s = "Line one. \nLine two. \rLine three. \n\rLine four. \r\nLine five.";
        System.out.println( s.replaceAll("[\n\r]", "") );
        // Example: Formatting string lists with collector
        // http://adambien.blog/roller/abien/entry/formatting_string_lists_with_collector
        var words = List.of( "eins", "zwei", "drei" );
        var joined = words.stream().collect(
                Collectors.joining("\", \"", "[ \"", "\" ]") );
        System.out.println( "Joined: " + joined );
    }
}
