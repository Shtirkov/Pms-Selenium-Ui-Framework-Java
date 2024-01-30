package seleniumuiframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumuiframework.abstractcomponents.BasePage;

public class MyCart extends BasePage {
	
	public MyCart(WebDriver driver) {
		super(driver);		
		PageFactory.initElements(driver, this);
	}

	private final String CHECKOUT_BUTTON_LOCATOR = "//button[text()='Checkout']";
	private final String ITEMS_IN_THE_CART_LOCATOR = ".cartWrap h3";

	@FindBy(xpath = CHECKOUT_BUTTON_LOCATOR)
	WebElement checkoutButton;

	@FindBy(css = ITEMS_IN_THE_CART_LOCATOR)
	List<WebElement> itemsInTheCart;

	public List<WebElement> getItemsInTheCart() {
		waitForElementToAppear(By.xpath(CHECKOUT_BUTTON_LOCATOR));
		return itemsInTheCart;
	}
	
	public void clickCheckoutButton() {
		waitForElementToBeClickable(By.xpath(CHECKOUT_BUTTON_LOCATOR));
		checkoutButton.click();
	}

}