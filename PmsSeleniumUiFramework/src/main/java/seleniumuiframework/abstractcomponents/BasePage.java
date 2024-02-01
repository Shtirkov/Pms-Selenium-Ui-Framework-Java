package seleniumuiframework.abstractcomponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumuiframework.pageobjects.MyCartPage;
import seleniumuiframework.pageobjects.OrdersPage;

public abstract class BasePage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	private final String CART_BUTTON_LOCATOR = "[routerlink*='cart']";
	private final String ORDERS_BUTTON_LOCATOR = "[routerlink*='myorders']";
	protected final String TOAST_MESSAGE_LOCATOR = "toast-container";
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = TOAST_MESSAGE_LOCATOR)
	WebElement toastMessage;
	
	@FindBy(css = CART_BUTTON_LOCATOR)
	WebElement cartButton;
	
	@FindBy(css = ORDERS_BUTTON_LOCATOR)
	WebElement ordersButton;
	
	protected void waitForElementToAppear(By locator) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	protected void waitForElementToDisappear(By locator) {		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	protected void waitForElementToBeClickable(By locator) {
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public MyCartPage goToMyCartPage() {
		waitForElementToBeClickable(By.cssSelector(CART_BUTTON_LOCATOR));
		cartButton.click();	
		return new MyCartPage(driver);
	}
	
	public OrdersPage goToOrdersPage() {
		waitForElementToBeClickable(By.cssSelector(ORDERS_BUTTON_LOCATOR));
		ordersButton.click();	
		return new OrdersPage(driver);
	}
	
	public String getToastMessageText() {
		waitForElementToAppear(By.id(TOAST_MESSAGE_LOCATOR));
		return toastMessage.getText();
	}
	
}
