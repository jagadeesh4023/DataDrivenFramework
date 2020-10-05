package com.evoketech.testscripts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.evoketech.base.BaseTest;

public class LoginTest extends BaseTest {

	@Test
	public void loginAsBankManager() throws InterruptedException {

		driver.findElement(By.xpath(OR.getProperty("BankManagerLogin"))).click();
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AddCustomer"))), "No Such Element Found");
		log.debug("LoginTest executed successfully");

	}

}
