package seleniumuiframework.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
  
	public ExtentReports getExtentReport() {
		ExtentSparkReporter reporter = new ExtentSparkReporter("reports//testReport.html");
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports er = new ExtentReports();
		er.attachReporter(reporter);
		return er;
	}
}
