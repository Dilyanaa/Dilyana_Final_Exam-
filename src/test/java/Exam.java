import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Exam {

    private WebDriver driver;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\WebDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://shop.pragmatic.bg/");
    }

    @Test

    public void positiveTest() {

        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Register")).click();

        driver.findElement(By.id("input-firstname")).sendKeys("Dilyana");
        driver.findElement(By.id("input-lastname")).sendKeys("Marinova");
        driver.findElement(By.id("input-email")).sendKeys("dilyanaa" + "@gmail.com");
        driver.findElement(By.id("input-telephone")).sendKeys("0883399308");
        driver.findElement(By.id("input-password")).sendKeys("dilyana0883399308");
        driver.findElement(By.id("input-confirm")).sendKeys("dilyana0883399308");

        WebElement subscribeButtonYes = driver.findElement(By.xpath("//label[@class='radio-inline']//following-sibling::input[@value='1']"));

        if (!subscribeButtonYes.isSelected()) {
            subscribeButtonYes.click();
        }
        assertTrue(subscribeButtonYes.isSelected());

        WebElement privacyPolicyCheckbox = driver.findElement(By.xpath("//a[@class='agree']//following-sibling::input[@type='checkbox']"));
        privacyPolicyCheckbox.click();
        assertTrue(privacyPolicyCheckbox.isSelected());

        driver.findElement(By.xpath("//a[@class='agree']/following-sibling::input[@value='Continue']")).click();

        driver.getTitle();
        assertEquals(driver.getTitle(), "Your Account Has Been Created!");
    }

    @Test

    public void negativeTest () {

        driver.findElement(By.linkText("My Account")).click();
        driver.findElement(By.linkText("Register")).click();

        WebElement subscribeButtonYes = driver.findElement(By.xpath("//label[@class='radio-inline']//following-sibling::input[@value='1']"));

        if (!subscribeButtonYes.isSelected()) {
            subscribeButtonYes.click();
        }
        assertTrue(subscribeButtonYes.isSelected());

        WebElement privacyPolicyCheckbox = driver.findElement(By.xpath("//a[@class='agree']//following-sibling::input[@type='checkbox']"));
        privacyPolicyCheckbox.click();
        assertTrue(privacyPolicyCheckbox.isSelected());

        driver.findElement(By.xpath("//a[@class='agree']/following-sibling::input[@value='Continue']")).click();


        WebElement firstName = driver.findElements(By.xpath("//div[contains(@class, 'text-danger')]")).get(0);
        assertEquals(firstName.getText(), "First Name must be between 1 and 32 characters!");

        WebElement lastName = driver.findElements(By.xpath("//div[contains(@class, 'text-danger')]")).get(1);
        assertEquals(lastName.getText(), "Last Name must be between 1 and 32 characters!");

        WebElement email = driver.findElements(By.xpath("//div[contains(@class, 'text-danger')]")).get(2);
        assertEquals(email.getText(), "E-Mail Address does not appear to be valid!");

        WebElement phone = driver.findElements(By.xpath("//div[contains(@class, 'text-danger')]")).get(3);
        assertEquals(phone.getText(), "Telephone must be between 3 and 32 characters!");

        WebElement pass = driver.findElements(By.xpath("//div[contains(@class, 'text-danger')]")).get(4);
        assertEquals(pass.getText(), "Password must be between 4 and 20 characters!");

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

}
