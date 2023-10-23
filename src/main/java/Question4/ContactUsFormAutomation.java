package Question4;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;


public class ContactUsFormAutomation {

    WebDriver driver;

    @BeforeMethod
    public void SetUp() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://webdriveruniversity.com/Contact-Us/contactus.html");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }
    @Test
    public void testValidEmailAddress() {

        WebElement emailAddressBox = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        emailAddressBox.sendKeys(email);
        WebElement successMessage = driver.findElement(By.xpath("//h1[normalize-space()='Thank You for your Message!']"));
        Assert.assertTrue(successMessage.getText().contains("Thank You for your Message!"),
                "Form submitted successfully for a valid email address:");
    }
    @Test
    public void testInvalidEmail() {

        WebElement emailAddressBox = driver.findElement(By.xpath("//input[@placeholder='Email Address']"));
        emailAddressBox.sendKeys("ugur.karadag@.com");
        WebElement errorMessage=driver.findElement(By.xpath("//body"));
        Assert.assertTrue(errorMessage.isDisplayed(),"Error message for invalid email not displayed");
    }
    @AfterMethod
    public void tearDown() {

        driver.quit();
    }
}
