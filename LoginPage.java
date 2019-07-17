package com.clc.auto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import com.clc.auto.selenium.AppBrowserStuff;

public class LoginPage {

	public static AdminUsers navigateToAdmin(WebDriver driver) {

		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.id("menu_admin_viewAdminModule")))
				.moveToElement(driver.findElement(By.id("menu_admin_UserManagement")))
				.moveToElement(driver.findElement(By.id("menu_admin_viewSystemUsers"))).click().build().perform();
		
		return PageFactory.initElements(driver, AdminUsers.class);
		
		

	}

}
