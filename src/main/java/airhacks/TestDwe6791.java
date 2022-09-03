package airhacks;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class TestDwe6791 {

    public static void main(String[] args) throws Exception {
        System.setProperty( "webdriver.chrome.driver",
                "C:/src/java17-plain/bin/chromedriver" );
        WebDriverManager.chromedriver().setup();
        WebDriver webDriver = new ChromeDriver();
        webDriver.get( "http://localhost:9090/listviewwithexception" );
        WebElement element = webDriver.findElement(By.tagName("h1"));
        System.out.println( "Heading: " + element.getText() );
        sleep(2000);
        WebElement jGrowl = webDriver.findElement(By.cssSelector( ".jgrowl-error" ));
        System.out.println( "jGrowl: " + jGrowl.isDisplayed() );
        webDriver.quit();
    }
}
