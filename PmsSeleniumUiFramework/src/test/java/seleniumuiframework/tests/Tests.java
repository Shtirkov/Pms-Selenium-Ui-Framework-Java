package seleniumuiframework.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import seleniumuiframework.pageobjects.Checkout;
import seleniumuiframework.pageobjects.Login;
import seleniumuiframework.pageobjects.MyCart;
import seleniumuiframework.pageobjects.OrderConfirmation;
import seleniumuiframework.pageobjects.Product;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tests {

	public static void main(String[] args) throws InterruptedException {

		String myProduct = "Iphone 13 pro";
		WebDriver driver = new ChromeDriver();

		Login loginPage = new Login(driver);
		Product productPage = new Product(driver);
		MyCart myCartPage = new MyCart(driver);
		Checkout checkoutPage = new Checkout(driver);
		OrderConfirmation orderConfirmationPage = new OrderConfirmation(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		loginPage.goTo();
		loginPage.login("JohnSmith@gmail.com", "StrongPass123");

		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		productPage.goToMyCartPage();

		List<String> expectedItemsInTheCart = List.of(myProduct);
		List<WebElement> actualItemsInTheCart = myCartPage.getItemsInTheCart();

		Assert.assertEquals(actualItemsInTheCart.size(), expectedItemsInTheCart.size());

		for (int i = 0; i < expectedItemsInTheCart.size(); i++) {
			String actualProductName = actualItemsInTheCart.get(i).getText().toLowerCase();
			String expectedProductName = expectedItemsInTheCart.get(i).toLowerCase();
			Assert.assertEquals(actualProductName, expectedProductName);
		}

		myCartPage.clickCheckoutButton();
		checkoutPage.selectCountryByName("United states");
		checkoutPage.placeOrder();
		Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "thankyou for the order.");

		driver.quit();
	}

}
