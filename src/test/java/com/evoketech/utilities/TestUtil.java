package com.evoketech.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.evoketech.base.BaseTest;

public class TestUtil extends BaseTest{
	
	public static String screenShotPath; 
	public static String scrrenShotName;
	
	public static void captureScreenshot(String methodname) throws IOException {
		
		Date D = new Date();
		scrrenShotName = "_"+ D.toString().replace(":", "_").replace(" ", "_")+".jpg";
		
		File srcFile =  ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		screenShotPath = System.getProperty("user.dir")+"\\target\\surefire-reports\\html\\"+methodname+scrrenShotName;
		FileUtils.copyFile(srcFile, new File(screenShotPath));
	}

}
