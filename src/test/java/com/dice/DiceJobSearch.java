package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) {
		
		

		// Set up chrome driver path
		WebDriverManager.chromedriver().setup();

		// Invoke selenium webdriver
		WebDriver driver = new ChromeDriver();

		// full Screen
		driver.manage().window().maximize(); // or maximize fullscreen();

		// set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		/*
		 * Step 1. Launch browser and navigate to dice.com Expected: dice home page
		 * should be displayed.
		 */

		String url = "https://www.dice.com/";
		// driver.navigate().to(url);
		driver.get(url);

		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";

		if (actualTitle.equals(expectedTitle)) {
			System.out.println("Dice homePage succefully loaded");
		} else {
			System.out.println("Step FAIL. Did NOT load succefully");
			throw new RuntimeException("Step FAIL. Did NOT load succefully");
		}

		/*
		 * Step 2. Insert search keyword amd location then click on find tech jobs
		 */
		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);

		driver.findElement(By.id("findTechJobs")).click();

		String count = driver.findElement(By.id("posiCountId")).getText();

		System.out.println(count);
		
		//ensure count is more than 0
		
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0) {
			System.out.println("more than 1000 jobs");
		}else {
			System.out.println("less than 1000 jobs");
		}
		
		driver.close();

	}

}