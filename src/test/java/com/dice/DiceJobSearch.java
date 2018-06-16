package com.dice;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	public static void main(String[] args) throws FileNotFoundException {
		
		

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
		 * Step 2. Insert search keyword and location then click on find tech jobs
		 */
		Scanner s = new Scanner(new File("/Users/oleksandrdanylchuk/eclipse-workspace/SeleniumMavenAutomation/keywords"));
		
		ArrayList <String> s1 = new ArrayList();
		
		while (s.hasNext()){
		    s1.add(s.next());
		}
		
		
//		s1.add("java");s1.add("selenium");s1.add("qtp");s1.add("hp");s1.add("oca");
//		s1.add("git");s1.add("maven");s1.add("ruby");s1.add("python");s1.add("QA");
		
		
		for(int i=0; i<s1.size();i++) {
			
		
		String keyword = s1.get(i);
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);

		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);

		driver.findElement(By.id("findTechJobs")).click();

		String count = driver.findElement(By.id("posiCountId")).getText();
		
		if(driver.getTitle().contains("Jobs not found | Dice.com")) {
			count = "0";
		}else {
			 count = driver.findElement(By.id("posiCountId")).getText();
		}

		
		
		//ensure count is more than 0
		
		int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0) {
			s1.set(i,s1.get(i)+ " - " +count);
//			s1.add(i, count+"");
			
		}else {
			System.out.println("less than 1000 jobs");
		}
		System.out.println(s1.get(i));
		driver.navigate().back();
		}		
		
		System.out.println(s1);
		
		
		driver.close();
		s.close();
		
		// comment

	}

}
