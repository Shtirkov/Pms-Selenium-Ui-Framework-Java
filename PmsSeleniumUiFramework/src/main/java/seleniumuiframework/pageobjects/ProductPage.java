package seleniumuiframework.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumuiframework.abstractcomponents.BasePage;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);		
		PageFactory.initElements(driver, this);
	}

	private final String PRODUCTS_LOCATOR = ".mb-3";
	
	private final String ADD_TO_CART_LOCATOR = ".w-10";
	
	@FindBy(css = PRODUCTS_LOCATOR)
	List<WebElement> products;

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
}
