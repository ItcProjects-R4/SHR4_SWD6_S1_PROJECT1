package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class checkout {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user-name"))).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-sauce-labs-backpack"))).click();
    }

    @BeforeMethod
    public void goToCheckoutPage() throws InterruptedException {
        driver.get("https://www.saucedemo.com/checkout-step-one.html");
        Thread.sleep(1000);
    }

    @Test(priority = 1)
    public void Empty_firstname() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("last-name"))).sendKeys("Evan");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("44515");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void Empty_lastname() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Bemo");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("44515");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void Numbers_as_names() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("2412");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("2222");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("44515");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 4)
    public void Postal_code_characters() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("Bemo");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("Evan");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("BemoIsCool");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 5)
    public void Space_fields() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys(" ");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys(" ");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys(" ");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 6)
    public void names_1_character() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("B");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("E");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("A");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 7)
    public void postal_code_1_number() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("B");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("E");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("1");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 8)
    public void Special_Characters() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("@");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("!");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("#");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 9)
    public void no_limits() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-name"))).sendKeys("BemoBemoBemoBemoBemoBemoBemoBemoBemoBemoBemoBemo");
        Thread.sleep(2000);
        driver.findElement(By.id("last-name")).sendKeys("BemoBemoBemoBemoBemoBemoBemoBemoBemoBemoBemoBemo");
        Thread.sleep(2000);
        driver.findElement(By.id("postal-code")).sendKeys("BemoBemoBemoBemoBemoBemoBemoBemoBemoBemoBemoBemo");
        Thread.sleep(2000);
        driver.findElement(By.id("continue")).click();
        Thread.sleep(2000);
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(3000); // وقت إضافي في النهاية قبل قفل المتصفح
        if (driver != null) {
            driver.quit();
        }
    }
}