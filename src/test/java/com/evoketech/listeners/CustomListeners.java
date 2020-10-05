package com.evoketech.listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.evoketech.utilities.TestUtil;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends TestUtil implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		
		test = repo.startTest(result.getName().toUpperCase());

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		test.log(LogStatus.PASS, result.getName().toUpperCase() +" Pass");
		repo.endTest(test);
		repo.flush();

	}

	@Override
	public void onTestFailure(ITestResult result) {

		String MethodName = result.getName().toString().trim();
		try {
			TestUtil.captureScreenshot(MethodName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ReportNG
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		Reporter.log("Click on the link to view the Screenshot");
		Reporter.log("<a target = \"_blank\" href=" + TestUtil.screenShotPath + ">Screenshot</a>");
		
		//Reporter.log("<a target = \"_blank\" href=" + TestUtil.screenShotPath + "><img src=" + TestUtil.screenShotPath + "height=200 width=200></img></a>");
		
		//Extent Reports
		
		test.log(LogStatus.FAIL, result.getName().toUpperCase() + " Failed with Exception : " + result.getThrowable());
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenShotPath));
		repo.endTest(test);
		repo.flush();
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
