package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class login {
    WebDriver w1 = new ChromeDriver();

    @BeforeMethod
    public void open() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\IT\\Pictures\\chromedriver-win64\\chromedriver.exe");
        w1.navigate().to("https://www.saucedemo.com/");
        w1.manage().window().maximize();
    }



    //TC01 _ verify accept when enter valid username and password
    @Test(priority = 1)
    public void valid_login_standard_user() throws InterruptedException {
        WebElement d1 = w1.findElement(By.id("user-name"));
        Thread.sleep(1000);
        d1.sendKeys("standard_user");
        WebElement d2 = w1.findElement(By.id("password"));
        Thread.sleep(1000);
        d2.sendKeys("secret_sauce");
        WebElement d3 = w1.findElement(By.id("login-button"));
        d3.click();


        Assert.assertEquals(w1.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(3000);
    }



    //TC02 _ verify accept when enter valid username and password
    @Test(priority = 2)
    public void valid_login_locked_user() throws InterruptedException {
        WebElement d1 = w1.findElement(By.id("user-name"));
        Thread.sleep(1000);
        d1.sendKeys("locked_out_user");

        WebElement d2 = w1.findElement(By.id("password"));
        Thread.sleep(1000);
        d2.sendKeys("secret_sauce");

        WebElement d3 = w1.findElement(By.id("login-button"));
        d3.click();


        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = w1.getCurrentUrl();

        Assert.assertEquals(actualUrl, expectedUrl, "Login failed: The error message appeared on the site!");

        Thread.sleep(3000);
    }



    //TC03 _ verify accept when enter valid username and password
    @Test(priority = 3)
    public void valid_login_problem_user() throws InterruptedException {
        WebElement d1 = w1.findElement(By.id("user-name"));
        d1.sendKeys("problem_user");
        WebElement d2 = w1.findElement(By.id("password"));
        d2.sendKeys("secret_sauce");
        WebElement d3 = w1.findElement(By.id("login-button"));
        d3.click();

        Assert.assertEquals(w1.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(3000);
    }


    //TC04 _ verify accept when enter valid username and password
    @Test(priority = 4)
    public void valid_login_performance_glitch_user() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("performance_glitch_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();

        Assert.assertEquals(w1.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(3000);
    }



    //TC05 _ verify accept when enter valid username and password
    @Test(priority = 5)
    public void valid_login_error_user() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("error_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();

        Assert.assertEquals(w1.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(3000);
    }


    //TC06 _ verify accept when enter valid username and password
    @Test(priority = 6)
    public void valid_login_visual_user() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("visual_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();

        Assert.assertEquals(w1.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Thread.sleep(3000);
    }


    //TC07 _ verify show error when invalid username and password
    @Test(priority = 7)
    public void invalid_login() throws InterruptedException {
        WebElement d1 = w1.findElement(By.id("user-name"));
        d1.sendKeys("jana");
        WebElement d2 = w1.findElement(By.id("password"));
        d2.sendKeys("secret");
        WebElement d3 = w1.findElement(By.id("login-button"));
        d3.click();


        Assert.assertTrue(w1.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed());
        Thread.sleep(3000);
    }


    //TC08 _ verify show error when valid username and invalid password
    @Test(priority = 8)
    public void invalid_login_password() throws InterruptedException {
        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        w1.findElement(By.id("password")).sendKeys("secret");
        w1.findElement(By.id("login-button")).click();


        boolean isErrorVisible = w1.findElement(By.cssSelector("h3[data-test='error']")).isDisplayed();
        Assert.assertTrue(isErrorVisible, "Error message should be displayed for wrong password");
        Thread.sleep(3000);
    }

    @AfterMethod
    public void close() {
        w1.quit();
    }
}