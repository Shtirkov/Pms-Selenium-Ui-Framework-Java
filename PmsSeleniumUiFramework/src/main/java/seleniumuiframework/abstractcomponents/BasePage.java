package seleniumuiframework.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	//private WebDriver driver;
	private WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		//this.driver=driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	protected void waitForElementToAppear(By locator) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected void waitForElementToDisappear(By locator) {		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	protected void waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
}
