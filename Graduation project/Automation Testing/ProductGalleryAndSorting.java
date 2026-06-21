package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ProductGalleryAndSorting {
    WebDriver w1;

    @BeforeClass
    public void setup() throws InterruptedException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--guest");
        options.addArguments("--disable-save-password-bubble");

        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        w1 = new ChromeDriver(options);
        w1.manage().window().maximize();
        w1.get("https://www.saucedemo.com/");


        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();

        Thread.sleep(2000L);
    }

    @Test(priority = 1)
    public void validateFirstItemName() throws InterruptedException {
        String itemName = w1.findElement(By.className("inventory_item_name")).getText();
        System.out.println("First item found: " + itemName);
        Thread.sleep(2000L);
    }

    @Test(priority = 2)
    public void validateTotalItemsCount() throws InterruptedException {
        int count = w1.findElements(By.className("inventory_item")).size();
        System.out.println("Total items visible: " + count);
        Thread.sleep(2000L);
    }

    @Test(priority = 3)
    public void verifyAddToCartWorkflow() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(2000L);

        WebElement actionButton = w1.findElement(By.id("remove-sauce-labs-fleece-jacket"));
        Assert.assertEquals(actionButton.getText(), "Remove");

        WebElement cartBadge = w1.findElement(By.className("shopping_cart_badge"));
        Assert.assertEquals(cartBadge.getText(), "1");
        System.out.println("Item added successfully.");
    }

    @Test(priority = 4)
    public void validateProductByPrice() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        Thread.sleep(2000L);
    }

    @Test(priority = 5)
    public void validateLastItemIndex() throws InterruptedException {
        w1.findElement(By.id("item_3_title_link")).click();
        Thread.sleep(3000L);
        w1.navigate().back();
        Thread.sleep(2000L);
    }

    @Test(priority = 6)
    public void addMultipleItems() throws InterruptedException {
        List<WebElement> addButtons = w1.findElements(By.xpath("//button[text()='Add to cart']"));
        for(int i=0; i < 3 && i < addButtons.size(); i++) {
            addButtons.get(i).click();
            Thread.sleep(1000L);
        }
    }

    @Test(priority = 7)
    public void validateCartNavigation() throws InterruptedException {
        w1.findElement(By.className("shopping_cart_link")).click();
        System.out.println("Navigated to Cart page.");
        Thread.sleep(4000L);
        w1.navigate().back();
        Thread.sleep(2000L);
    }

    @Test(priority = 8)
    public void validateLogout() throws InterruptedException {
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(2000L);
        w1.findElement(By.id("logout_sidebar_link")).click();
        Thread.sleep(3000L);
        Assert.assertTrue(w1.findElement(By.id("login-button")).isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        if (w1 != null) {
            w1.quit();
        }
    }
}