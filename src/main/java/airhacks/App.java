package airhacks;


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
    }
}
