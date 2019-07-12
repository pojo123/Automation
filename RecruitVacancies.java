package com.clc.auto.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class RecruitVacancies {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers2\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com//");

		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys("Admin");

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys("admin123");

		WebElement login = driver.findElement(By.name("Submit"));
		login.click();

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewRecruitmentModule\"]/b")))
				.moveToElement(driver.findElement(By.xpath("//*[@id=\"menu_recruitment_viewJobVacancy\"]"))).click()
				.build().perform();

		WebElement button = driver.findElement(By.name("btnAdd"));
		button.click();
		TimeUnit.SECONDS.sleep(2);

		Select jobtitle = new Select(driver.findElement(By.id("addJobVacancy_jobTitle")));
		jobtitle.selectByVisibleText("CEO");
		TimeUnit.SECONDS.sleep(2);

		WebElement vacancy = driver.findElement(By.id("addJobVacancy_name"));
		vacancy.sendKeys("Java Developer");
		TimeUnit.SECONDS.sleep(2);


		driver.findElement(By.id("addJobVacancy_hiringManager")).sendKeys("Linda Anderson");

		WebElement position = driver.findElement(By.id("addJobVacancy_noOfPositions"));
		position.sendKeys("4");
		TimeUnit.SECONDS.sleep(2);

		WebElement description = driver.findElement(By.id("addJobVacancy_description"));
		description.sendKeys("Core Java, Selenium");
		TimeUnit.SECONDS.sleep(2);

		WebElement checkbox = driver.findElement(By.id("addJobVacancy_status"));
		checkbox.click();
		TimeUnit.SECONDS.sleep(2);

		WebElement save = driver.findElement(By.id("btnSave"));
		save.click();

	}

}
