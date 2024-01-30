package seleniumuiframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumuiframework.abstractcomponents.BasePage;

public class OrderConfirmation extends BasePage {

	public OrderConfirmation(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	private final String ORDER_CONFIRMATION_MESSAGE_LOCATOR = ".hero-primary";
	
	@FindBy(css = ORDER_CONFIRMATION_MESSAGE_LOCATOR)
	WebElement orderConfirmationMessage;
	
	public String getOrderConfirmationMessage() {
		return orderConfirmationMessage.getText().toLowerCase();
	}

}
