/*
 */
package airhacks;

import org.junit.jupiter.api.Test;

/**
 *
 * @author airhacks.com
 */
public class JavaTest {

    @Test
    public void hello() {
        var message = """
            hello, duke
                """;
        System.out.println(message);
    }

    @Test
    public void generateJson() {
        var message = """
        {
            "message":"%s"
        }
        """.formatted("hello,duke");
        System.out.println(message);
    }

}
