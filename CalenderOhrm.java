package com.clc.auto.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class CalenderOhrm {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "C:\\Drivers2\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com//");

		WebElement username = driver.findElement(By.id("txtUsername"));
		username.sendKeys("Admin");

		WebElement password = driver.findElement(By.id("txtPassword"));
		password.sendKeys("admin123");

		WebElement login = driver.findElement(By.name("Submit"));
		login.click();

		Actions ac = new Actions(driver);
		ac.moveToElement(driver.findElement(By.id("menu_leave_viewLeaveModule")))
				.moveToElement(driver.findElement(By.id("menu_leave_viewLeaveList"))).click().build().perform();

		driver.findElement(By.id("calFromDate")).click();
		List<WebElement> calheader = driver.findElement(By.id("ui-datepicker-div"))
				.findElement(By.className("ui-datepicker-header")).findElement(By.className("ui-datepicker-title"))
				.findElements(By.tagName("select"));

		// System.out.println(calheader.size());

		Select years = new Select(calheader.get(1));
		years.selectByValue("1990");

		calheader = driver.findElement(By.id("ui-datepicker-div")).findElement(By.className("ui-datepicker-header"))
				.findElement(By.className("ui-datepicker-title")).findElements(By.tagName("select"));

		Select months = new Select(calheader.get(0));
		months.selectByVisibleText("Aug");

		WebElement table = driver.findElement(By.className("ui-datepicker-calendar"));
		WebElement body = table.findElement(By.tagName("tbody"));
		List<WebElement> rows = body.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			System.out.println("\n");
			for (WebElement col : columns) {
				try {
					WebElement date = col.findElement(By.tagName("a")); // we have used try-catch here for no such
																		// element exception
					System.out.print(date.getText() + "\t");
					if (date.getText().equals("24")) {
						date.click();
						break;
					}
				} catch (Exception e) {
				}
			}
		}

		driver.findElement(By.id("calToDate")).click();
		List<WebElement> header = driver.findElement(By.id("ui-datepicker-div"))
				.findElement(By.className("ui-datepicker-header")).findElement(By.className("ui-datepicker-title"))
				.findElements(By.tagName("select"));

		Select year = new Select(header.get(1));
		year.selectByValue("1992");
		
		header = driver.findElement(By.id("ui-datepicker-div"))
				.findElement(By.className("ui-datepicker-header")).findElement(By.className("ui-datepicker-title"))
				.findElements(By.tagName("select"));

		Select month = new Select(header.get(0));
		month.selectByVisibleText("Sep");
		
		List<WebElement> rowss = driver.findElement(By.className("ui-datepicker-calendar")).findElement(By.tagName("tbody")).findElements(By.tagName("tr"));
		for(WebElement rw : rowss) {
			List<WebElement> cols = rw.findElements(By.tagName("td"));
			for(WebElement cl : cols) {
				try {
				WebElement dt=cl.findElement(By.tagName("a"));
				if(dt.getText().equals("20")) {
					dt.click();
					break;
				//System.out.println(date.getText());
				}
				}catch(Exception e){}
			}
		}
		
	}

}
