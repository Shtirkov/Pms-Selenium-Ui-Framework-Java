package seleniumuiframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumuiframework.abstractcomponents.BasePage;

public class MyCart extends BasePage {

	private WebDriver driver;

	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String checkoutButtonLocator = "//button[text()='Checkout']";
	private final String itemsInTheCartLocator = ".cartWrap";

	@FindBy(xpath = checkoutButtonLocator)
	WebElement checkoutButton;

	@FindBy(xpath = itemsInTheCartLocator)
	List<WebElement> itemsInTheCart;

	public List<WebElement> getItemsInTheCart() {
		waitForElementToAppear(By.xpath(checkoutButtonLocator));
		return itemsInTheCart;
	}
	
	public void clickCheckoutButton() {
		waitForElementToBeClickable(By.xpath(checkoutButtonLocator));
		checkoutButton.click();
	}

}
