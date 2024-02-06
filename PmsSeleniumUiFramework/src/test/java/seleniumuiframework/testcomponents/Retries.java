package seleniumuiframework.testcomponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retries implements IRetryAnalyzer {
	private int currentRetriesCount = 0;
	private int maxRetriesCount = 1;

	@Override
	public boolean retry(ITestResult result) {
		if(currentRetriesCount < maxRetriesCount) {
			currentRetriesCount++;
			return true;
		}
		return false;
	}

}
