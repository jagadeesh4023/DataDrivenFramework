package com.evoketech.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.evoketech.utilities.ExcelReader;
import com.evoketech.utilities.ExtentManager;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	public static FileInputStream fis;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "//src//test//resources//ExcelReader//excel.xlsx");
	public static WebDriverWait wait;
	public ExtentReports repo = ExtentManager.getInstance();
	public static ExtentTest test;

	@BeforeSuite
	public void setUp() throws IOException {
		if (driver == null) {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\Config.properties");
			config.load(fis);
			log.debug("config file loaded sucessfully");

			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\Properties\\OR.properties");
			OR.load(fis);
			log.debug("OR file loaded successfully");

		}

		if (config.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.debug("chrome browser launched successfully");

		} else if (config.getProperty("browser").equalsIgnoreCase("firfox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.debug("firefox browser launched successfully");

		} else if (config.getProperty("browser").equalsIgnoreCase("internetexplorer")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
			log.debug("IE browser launched successfully");

		} else {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			log.debug("edge browser launched successfully");
		}

		driver.get(config.getProperty("url"));
		log.debug("Navigated to: " + config.getProperty("url"));
		driver.manage().window().maximize();
		log.debug("browser is maximised");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		/*
		 * driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.
		 * getProperty("implicit wait")), TimeUnit.SECONDS);
		 */
		wait = new WebDriverWait(driver, 5);

	}

	public boolean isElementPresent(By by) {

		try {
			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {
			return false;
		}

	}

	@AfterSuite
	public void tearDown() {

		if (driver != null) {
			driver.quit();

		}
		log.debug("Browser quit successfully");

	}

}
