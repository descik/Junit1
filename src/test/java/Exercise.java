import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Exercise {


//    private void registers() {
//        for (int i = 0; i < 3; i++) {
//            registers();
//        }
//    }

    private WebDriver driver;

    private String randomNip;

    private String generateRandomEmail() {
        Random rand = new Random();
        int number = rand.nextInt(99999);
        return "wojtus" + number + "@wp.pl";
    }

    private void modifyEmail() {
        valueList.set(0, generateRandomEmail());
    }

    List<String> valueList = Arrays.asList("dsadaup321321@wp.pl", "Adam", "Karol", "haslo1", "haslo1", "Kraków", "20-100", "cokolwiek", "20", "jest");

    List<String> inputList = Arrays.asList("fos_user_registration_form_email","fos_user_registration_form_name","fos_user_registration_form_lastname", "fos_user_registration_form_plainPassword_first", "fos_user_registration_form_plainPassword_second",
            "form_city", "form_postal_code", "form_street", "form_number", "fos_user_registration_form_company_name");



    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/cornholio/Downloads/chromedriver");
        driver = new ChromeDriver();

        driver.get("http://generatory.it/");
        WebElement  nip = driver.findElement(By.id("nipBox"));
        randomNip = nip.getText();


        driver.manage().window().maximize();
        driver.get("https://men-men-s-01.codersguru.pl/");
    }

    @After
    public void tearDown() {
        driver.quit();
    }



    @Test
    public void register (){
        driver.findElement(By.xpath("//input[@value='Załóż konto']")).click();


        driver.findElement(By.id("company")).click();

        WebElement selectField;
        for (int index = 0; index < inputList.size(); index ++) {
            selectField = driver.findElement(By.id(inputList.get(index)));
            modifyEmail();
            selectField.sendKeys(valueList.get(index));

        }

        driver.findElement(By.id("fos_user_registration_form_nip")).sendKeys(randomNip);

        driver.findElement(By.xpath("//input[@type='checkbox']")).click();

        driver.findElement(By.id("register-submit-btn")).click();

        String URL = driver.getCurrentUrl();
        Assert.assertTrue(URL.contains("https://men-men-s-01.codersguru.pl/register/confirmed"));
    }

}

