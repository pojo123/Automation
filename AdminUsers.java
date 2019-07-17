package com.clc.auto.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AdminUsers {

	public static AdminUsers userTable(WebDriver driver) {

		WebElement table = driver.findElement(By.id("resultTable"));
		WebElement tbody = table.findElement(By.tagName("tbody"));
		List<WebElement> rows = tbody.findElements(By.tagName("tr"));
		
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.tagName("td"));
			
			for (WebElement column : columns) {
				try {
					WebElement col = column.findElement(By.tagName("a"));
					System.out.println(col.getText());
					String str[]= {"fiona.grace","jasmine.morgan","linda.anderson"};
					for(String string :str) {
						if(string.equals(col.getText())) {
							columns.get(0).findElement(By.tagName("input")).click();
							break;
						}
					}
 				} catch (Exception e) {

				}
			}
		}

		return PageFactory.initElements(driver, AdminUsers.class);
	}
}
