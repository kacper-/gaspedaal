package com.km.gaspedaal;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Main {
    public static void main(String args[]) {
        String chromeDriverPath = "/Users/kacper/dev/chromedriver-mac-arm64/chromedriver";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
        options.setBinary("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome");
        options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://www.autowereld.nl/goedkope-occasions");
		
		WebElement e = driver.findElement(By.id("search-results"));
		System.out.println(e.getText());
		
		driver.quit();
    }
}
