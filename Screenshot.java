package com.clc.auto.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.io.Files;

public class Screenshot {
	
	static Logger log=Logger.getLogger(Screenshot.class);

	public static void takeScreenShot(WebDriver driver) {

		try {
			// Convert web driver object to TakeScreenshot
			TakesScreenshot ts = (TakesScreenshot) driver;

			// Call getScreenshotAs method to create image file
			File srcfile = ts.getScreenshotAs(OutputType.FILE);

			// Move image file to new destination
//		new File("E:\\Screenshot\\ohrm.png")

			// Copy file at destination

			Files.copy(srcfile, new File("E:\\Screenshot\\ohrm1.png"));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		FileUtils.copyFile("srcfile",new File("E:\\Screenshot\\ohrm.png"));

	}

	public static void main(String[] args) throws IOException {
		
		

		WebDriver driver = null;

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");

		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys("Admin");

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys("admin");

		WebElement login = driver.findElement(By.name("Submit"));
		login.click();
		
		log.info("This is info");
		log.info("file updated successfully");

		takeScreenShot(driver);
		
		

	}

}
