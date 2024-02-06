package seleniumuiframework.testcomponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import seleniumuiframework.utils.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener {
	private ExtentReporter er;
	private ExtentReports reporter;
	private ExtentTest test;
	private ThreadLocal<ExtentTest> threads;

	public Listeners() {
		er = new ExtentReporter();
		reporter = er.getExtentReport();
		threads = new ThreadLocal<ExtentTest>();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test = reporter.createTest(result.getMethod().getMethodName());
		threads.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		threads.get().log(Status.PASS, "Test passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		threads.get().fail(result.getThrowable());
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
		String filePath = "";
		
		try {
			filePath = takeScreenshot(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		threads.get().addScreenCaptureFromPath(filePath);
	}

	@Override
	public void onTestSkipped(ITestResult result) {		
		threads.get().skip("Marked as skipped because the test is ignored or because of the retry policy");
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
		reporter.flush();
	}
}
