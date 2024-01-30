package seleniumuiframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumuiframework.abstractcomponents.BasePage;

public class Checkout extends BasePage {
	public Checkout(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	private final String COUNTRY_DROPDOWN_LOCATOR = "[placeholder*='Country']";
	private final String COUNTRY_DROPDOWN_OPTIONS_LOCATOR = ".ta-item";
	private final String PLACE_ORDER_BUTTON_LOCATOR = ".action__submit";

	@FindBy(css = COUNTRY_DROPDOWN_LOCATOR)
	WebElement countryDropdown;

	@FindBy(css = COUNTRY_DROPDOWN_OPTIONS_LOCATOR)
	List<WebElement> countryDropdownOptions;

	@FindBy(css = PLACE_ORDER_BUTTON_LOCATOR)
	WebElement placeOrderButton;

	public void selectCountryByName(String countryName) {
		waitForElementToAppear(By.cssSelector(COUNTRY_DROPDOWN_LOCATOR));
		countryDropdown.sendKeys(countryName);
		WebElement myOption = countryDropdownOptions.stream().filter(o -> o.getText().equalsIgnoreCase(countryName))
				.findFirst().orElse(null);
		myOption.click();
	}
	
	public void placeOrder() {
		placeOrderButton.click();
	}

}
