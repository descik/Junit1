import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExerciseOne {

    private WebDriver driver;


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/cornholio/Downloads/chromedriver");
        driver = new ChromeDriver();


        driver.manage().window().maximize();
        driver.get("https://men-men-s-01.codersguru.pl/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testPage() {
        String url = "https://men-men-s-01.codersguru.pl/";
        driver.get(url);
        List<String> lists = Arrays.asList("/#how-it-works", "/cennik", "/login", "/regulations");

        for (String i : lists) {

            testLinks(i);
            assertEquals("https://men-men-s-01.codersguru.pl"+i, testLinks(i));
            driver.get(url);
        }

        WebElement facebook = driver.findElement(By.xpath("//a[@href='https://www.facebook.com/codersguru/']"));
        String attributeFacebook = facebook.getAttribute("herf");
        facebook.click();
        assertEquals("https://facebook.com/codersguru/", attributeFacebook);
        driver.get(url);

        WebElement register = driver.findElement(By.cssSelector(".link main-page-top__email-input-sent"));
        String attributeRegister = register.getAttribute("value");
        register.click();
        assertEquals("Załóż konto", attributeRegister);
        driver.get(url);

    }
    private String testLinks(String name) {
        WebElement element = driver.findElement(By.xpath("//a[@href='"+ name +"']"));
        String attribute = element.getAttribute("herf");
        element.click();
        return attribute;
    }
}
