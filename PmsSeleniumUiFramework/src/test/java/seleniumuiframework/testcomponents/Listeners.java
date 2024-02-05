package seleniumuiframework.testcomponents;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import seleniumuiframework.utils.ExtentReporter;

public class Listeners implements ITestListener {
	private ExtentReporter er;
	private ExtentReports reporter;
	private ExtentTest test;

	public Listeners() {
		er = new ExtentReporter();
		reporter = er.getExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = reporter.createTest(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {

	}

	@Override
	public void onStart(ITestContext context) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}
}
