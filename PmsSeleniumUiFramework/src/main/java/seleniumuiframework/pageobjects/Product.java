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

	private final String productsLocator = ".mb-3";
	private final String toastMessageLocator = "toast-container";
	private final String addToCartLocator = ".w-10";
	private final String cartButtonLocator = "[routerlink*='cart']";

	@FindBy(css = productsLocator)
	List<WebElement> products;

	@FindBy(css = toastMessageLocator)
	WebElement toastContainer;

	@FindBy(css = cartButtonLocator)
	WebElement cartButton;

	private List<WebElement> getProducts() {
		waitForElementToAppear(By.cssSelector(productsLocator));
		return products;
	}

	public WebElement getProductByName(String productName) {
		return getProducts().stream()
				.filter(p -> p.findElement(By.cssSelector("h5")).getText().equalsIgnoreCase(productName)).findFirst()
				.orElse(null);
	}

	public void addProductToCart(WebElement product) {
		product.findElement(By.cssSelector(addToCartLocator)).click();
		waitForElementToAppear(By.id(toastMessageLocator));
		waitForElementToDisappear(By.id(toastMessageLocator));
	}

	public void goToMyCartPage() {
		waitForElementToBeClickable(By.cssSelector(cartButtonLocator));
		cartButton.click();
	}

}
