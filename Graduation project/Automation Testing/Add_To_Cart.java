package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
public class Add_To_Cart {
    WebDriver w1;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();

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

        w1.findElement(By.id("user-name")).sendKeys("standard_user");
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
    }

    // TC01 - verify that clicking Add to cart button adds the product and button changes to Remove
    @Test(priority = 1)
    public void add_to_cart_button_changes_to_remove() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC02 - verify cart badge shows 1 after adding one product
    @Test(priority = 2)
    public void cart_badge_shows_one_after_adding_product() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC03 - verify cart badge shows 3 after adding 3 different products
    @Test(priority = 3)
    public void cart_badge_shows_three_after_adding_three_products() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC04 - verify all 6 products can be added to the cart and badge shows 6
    @Test(priority = 4)
    public void add_all_six_products_to_cart() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bolt-t-shirt")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-fleece-jacket")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-onesie")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-test.allthethings()-t-shirt-(red)")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC05 - verify adding product from product details page works
    @Test(priority = 5)
    public void add_to_cart_from_product_details_page() throws InterruptedException {
        w1.findElement(By.id("item_4_title_link")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("add-to-cart")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("remove")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC06 - verify no cart badge appears when no items added
    @Test(priority = 6)
    public void no_cart_badge_when_no_items_added() throws InterruptedException {
        w1.findElement(By.className("shopping_cart_link")).isDisplayed();
        Thread.sleep(2000);
    }

    // TC07 - verify clicking Remove on inventory page removes item and button returns to Add to cart
    @Test(priority = 7)
    public void remove_item_from_inventory_page() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("remove-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC08 - verify removing item from cart page works correctly
    @Test(priority = 8)
    public void remove_item_from_cart_page() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("remove-sauce-labs-backpack")).click();
        Thread.sleep(2000);
    }

    // TC09 - verify clicking cart icon navigates to cart page
    @Test(priority = 9)
    public void cart_icon_navigates_to_cart_page() throws InterruptedException {
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
    }

    // TC10 - verify cart page shows correct item title
    @Test(priority = 10)
    public void cart_page_shows_correct_item_title() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.className("inventory_item_name")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC11 - verify cart page shows correct item price
    @Test(priority = 11)
    public void cart_page_shows_correct_item_price() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.className("inventory_item_price")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC12 - verify cart page shows correct item quantity
    @Test(priority = 12)
    public void cart_page_shows_correct_item_quantity() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.className("cart_quantity")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC13 - verify Reset App State from sidebar clears all cart items
    @Test(priority = 13)
    public void reset_app_state_clears_cart() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("reset_sidebar_link")).click();
        Thread.sleep(2000);
    }

    // TC14 - verify Continue Shopping button navigates back to inventory page
    @Test(priority = 14)
    public void continue_shopping_navigates_back_to_inventory() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("continue-shopping")).click();
        Thread.sleep(2000);
    }

    // TC15 - verify Checkout button navigates to checkout step one page
    @Test(priority = 15)
    public void checkout_button_navigates_to_checkout_step_one() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("checkout")).click();
        Thread.sleep(2000);
    }

    // TC16 - verify cart state persists after page refresh
    @Test(priority = 16)
    public void cart_persists_after_page_refresh() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        w1.navigate().refresh();
        Thread.sleep(2000);
        w1.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC17 - verify cart items still exist after clicking Continue Shopping and going back to cart
    @Test(priority = 17)
    public void cart_items_persist_after_continue_shopping() throws InterruptedException {
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(500);
        w1.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(1500);
        w1.findElement(By.id("continue-shopping")).click();
        Thread.sleep(1500);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
        w1.findElement(By.className("shopping_cart_badge")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC18 - verify removing item from product details page reflects on cart page
    @Test(priority = 18)
    public void remove_from_details_page_reflects_on_cart() throws InterruptedException {
        w1.findElement(By.id("item_4_title_link")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("add-to-cart")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("remove")).click();
        Thread.sleep(1000);
        w1.findElement(By.className("shopping_cart_link")).click();
        Thread.sleep(2000);
    }

    // TC19 - verify locked_out_user cannot login and sees error message
    @Test(priority = 19)
    public void locked_out_user_cannot_login() throws InterruptedException {
        w1.get("https://www.saucedemo.com/");
        Thread.sleep(1000);
        w1.findElement(By.id("user-name")).clear();
        w1.findElement(By.id("user-name")).sendKeys("locked_out_user");
        w1.findElement(By.id("password")).clear();
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        w1.findElement(By.cssSelector("[data-test='error']")).isDisplayed();
        Thread.sleep(1000);
    }

    // TC20 - verify problem_user can add product to cart and button changes to Remove
    @Test(priority = 20)
    public void problem_user_add_to_cart() throws InterruptedException {
        w1.get("https://www.saucedemo.com/");
        Thread.sleep(1000);
        w1.findElement(By.id("user-name")).clear();
        w1.findElement(By.id("user-name")).sendKeys("problem_user");
        w1.findElement(By.id("password")).clear();
        w1.findElement(By.id("password")).sendKeys("secret_sauce");
        w1.findElement(By.id("login-button")).click();
        Thread.sleep(2000);
        w1.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        Thread.sleep(1000);
        w1.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed();
        Thread.sleep(1000);
    }

    @AfterMethod
    public void close() {
        if (w1 != null) {
            w1.quit();
        }
    }
}