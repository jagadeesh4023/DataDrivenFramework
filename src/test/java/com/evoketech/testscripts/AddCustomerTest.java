package com.evoketech.testscripts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.evoketech.base.BaseTest;

public class AddCustomerTest extends BaseTest {

	@Test(dataProvider = "getData")
	public void addCustomer(String FirstName, String LastName, String PostCode, String AlertText) throws InterruptedException {
        
		driver.findElement(By.xpath(OR.getProperty("BankManagerLogin"))).click();
		Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("AddCustomer"))));
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("AddCustomer"))).click();
		driver.findElement(By.xpath(OR.getProperty("FirstName"))).sendKeys(FirstName);
		driver.findElement(By.xpath(OR.getProperty("LastName"))).sendKeys(LastName);
		driver.findElement(By.xpath(OR.getProperty("PostCode"))).sendKeys(PostCode);
		driver.findElement(By.xpath(OR.getProperty("AddButton"))).click();

		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(AlertText));
		alert.accept();

	}

	@DataProvider
	public Object[][] getData() {

		String sheetName = "AddCustomer";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows - 1][cols];

		for (int rowNum = 2; rowNum <= rows; rowNum++) {

			for (int colNum = 0; colNum < cols; colNum++) {

				data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);

			}

		}

		return data;

	}

}
