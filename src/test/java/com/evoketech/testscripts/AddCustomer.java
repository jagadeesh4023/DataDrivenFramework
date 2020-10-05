package com.evoketech.testscripts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.testng.Assert;
import org.testng.annotations.Test;

import com.evoketech.base.BaseTest;

public class AddCustomer extends BaseTest {
	
	@Test
	public void AddCustomertest() throws InterruptedException
	{
		driver.findElement(By.xpath(OR.getProperty("AddCustomer"))).click();
		driver.findElement(By.xpath(OR.getProperty("FirstName"))).sendKeys(config.getProperty("FirstName"));
		driver.findElement(By.xpath(OR.getProperty("LastName"))).sendKeys(config.getProperty("LastName"));
		driver.findElement(By.xpath(OR.getProperty("PostCode"))).sendKeys(config.getProperty("PostCode"));
		driver.findElement(By.xpath(OR.getProperty("AddButton"))).click();
		
		Thread.sleep(3000);
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		//Assert.assertTrue(alert.getText().contains(config.getProperty("AlertText")));
		alert.accept();
		
		log.debug("AddCustomerTest executed successfully");
		
		Thread.sleep(3000);
	}

}
