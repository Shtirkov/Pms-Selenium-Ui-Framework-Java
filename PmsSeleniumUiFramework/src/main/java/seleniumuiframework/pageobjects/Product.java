package seleniumuiframework.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumuiframework.abstractcomponents.BasePage;

public class Product extends BasePage {

	WebDriver driver;

	public Product(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	private final String PRODUCTS_LOCATOR = ".mb-3";
	private final String TOAST_MESSAGE_LOCATOR = "toast-container";
	private final String ADD_TO_CART_LOCATOR = ".w-10";
	private final String CART_BUTTON_LOCATOR = "[routerlink*='cart']";

	@FindBy(css = PRODUCTS_LOCATOR)
	List<WebElement> products;

	@FindBy(css = TOAST_MESSAGE_LOCATOR)
	WebElement toastContainer;

	@FindBy(css = CART_BUTTON_LOCATOR)
	WebElement cartButton;

	private List<WebElement> getProducts() {
		waitForElementToAppear(By.cssSelector(PRODUCTS_LOCATOR));
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProducts().stream()
				.filter(p -> p.findElement(By.cssSelector("h5")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
	}

	public void addProductToCart(WebElement product) {
		waitForElementToDisappear(By.id(TOAST_MESSAGE_LOCATOR));
		product.findElement(By.cssSelector(ADD_TO_CART_LOCATOR)).click();
		waitForElementToAppear(By.id(TOAST_MESSAGE_LOCATOR));
		waitForElementToDisappear(By.id(TOAST_MESSAGE_LOCATOR));
	}

	public void goToMyCartPage() {
		waitForElementToBeClickable(By.cssSelector(CART_BUTTON_LOCATOR));
		cartButton.click();		
	}

}
