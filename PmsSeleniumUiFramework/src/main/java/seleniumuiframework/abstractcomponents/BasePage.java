package seleniumuiframework.abstractcomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumuiframework.pageobjects.MyCart;

public abstract class BasePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	private final String CART_BUTTON_LOCATOR = "[routerlink*='cart']";
	
	@FindBy(css = CART_BUTTON_LOCATOR)
	WebElement cartButton;
	
	protected void waitForElementToAppear(By locator) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected void waitForElementToDisappear(By locator) {		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	protected void waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public MyCart goToMyCartPage() {
		waitForElementToBeClickable(By.cssSelector(CART_BUTTON_LOCATOR));
		cartButton.click();	
		return new MyCart(driver);
	}
	
}
