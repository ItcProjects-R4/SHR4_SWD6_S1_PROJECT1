package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\IT\\Pictures\\chromedriver-win64\\chromedriver.exe");
        WebDriver w1=new ChromeDriver();
        w1.navigate().to("https://google.com");
    }
}

