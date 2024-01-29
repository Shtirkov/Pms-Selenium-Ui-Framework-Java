package seleniumuiframework.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
	
	WebDriver driver;
	
	public BasePage(WebDriver driver) {
		this.driver=driver;
	}
	
	public void waitForElementToAppear(By locator) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
}
