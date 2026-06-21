package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class end_to_end_scenrio {
    WebDriver w1;

    @BeforeMethod
    public void setUp() {


        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-features=PasswordLeakDetection");

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});

        w1 = new ChromeDriver(options);
        w1.manage().window().maximize();
        w1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        w1.get("https://www.saucedemo.com/");
    }

    @Test(priority = 1)
    public void valid_login() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        Thread.sleep(1000);
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        Thread.sleep(1000);
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 2)
    public void add_to_cart() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void checkout() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        w1.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
        w1.findElement(By.id("first-name")).sendKeys("Ahmed");
        Thread.sleep(500);
        w1.findElement(By.id("last-name")).sendKeys("Mohamed");
        Thread.sleep(500);
        w1.findElement(By.id("postal-code")).sendKeys("12345");
        Thread.sleep(500);
        w1.findElement(By.id("continue")).click();
        Thread.sleep(2000);
        w1.findElement(By.id("finish")).click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void close() {
        if (w1 != null) {
            w1.quit();
        }
    }
}