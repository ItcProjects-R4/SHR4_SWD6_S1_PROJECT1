package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Logout {

    WebDriver w1;

    @BeforeMethod
    public void open() {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");

        w1 = new ChromeDriver(options);
        w1.manage().window().maximize();
    }

    public void login() throws InterruptedException {
        w1.navigate().to("https://www.saucedemo.com/");
        Thread.sleep(1500);
        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(1500);
    }

    // TC01 - Verify successful logout from the side menu
    @Test(priority = 1)
    public void successful_logout() throws InterruptedException {
        login();
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1500);
        Assert.assertTrue(w1.getCurrentUrl().contains("saucedemo.com"));
    }

    // TC02 - Verify that inventory page is restricted after logout
    @Test(priority = 2)
    public void inventory_page_after_logout() throws InterruptedException {
        login();
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1000);
        w1.navigate().to("https://www.saucedemo.com/inventory.html");
        Thread.sleep(1500);
        Assert.assertTrue(w1.getPageSource().contains("login-button"));
    }

    // TC03 - Verify back button does not re-access the account after logout
    @Test(priority = 3)
    public void back_button_after_logout() throws InterruptedException {
        login();
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1500);
        w1.navigate().back();
        Thread.sleep(1500);
        Assert.assertTrue(w1.findElement(By.id("login-button")).isDisplayed());
    }

    // TC04 - Verify logout functionality from the shopping cart page
    @Test(priority = 4)
    public void logout_from_cart_page() throws InterruptedException {
        login();
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertTrue(w1.getCurrentUrl().contains("saucedemo"));
    }

    // TC05 - Verify logout works correctly even with items in the cart
    @Test(priority = 5)
    public void logout_with_items_in_cart() throws InterruptedException {
        login();
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertTrue(w1.getCurrentUrl().contains("saucedemo"));
    }

    // TC06 - Verify logout from an individual product details page
    @Test(priority = 6)
    public void logout_from_product_page() throws InterruptedException {
        login();
        w1.findElement(By.id("item_4_title_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertTrue(w1.getCurrentUrl().contains("saucedemo"));
    }

    // TC07 - Verify login page elements are visible after successful logout
    @Test(priority = 7)
    public void login_page_displayed_after_logout() throws InterruptedException {
        login();
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(1500);
        Assert.assertTrue(w1.findElement(By.id("login-button")).isDisplayed());
    }

    @AfterMethod
    public void close() {
        if (w1 != null) {
            w1.quit();
        }
    }
}
