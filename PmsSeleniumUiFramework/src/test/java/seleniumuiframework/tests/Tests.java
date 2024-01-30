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
		
		
		
		

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		loginPage.goTo();
		Product productPage = loginPage.login("JohnSmith@gmail.com", "StrongPass123");

		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		MyCart myCartPage=productPage.goToMyCartPage();

		List<String> expectedItemsInTheCart = List.of(myProduct);
		List<WebElement> actualItemsInTheCart = myCartPage.getItemsInTheCart();

		Assert.assertEquals(actualItemsInTheCart.size(), expectedItemsInTheCart.size());

		for (int i = 0; i < expectedItemsInTheCart.size(); i++) {
			String actualProductName = actualItemsInTheCart.get(i).getText().toLowerCase();
			String expectedProductName = expectedItemsInTheCart.get(i).toLowerCase();
			Assert.assertEquals(actualProductName, expectedProductName);
		}

		Checkout checkoutPage = myCartPage.clickCheckoutButton();
		checkoutPage.selectCountryByName("United states");
		OrderConfirmation orderConfirmationPage = checkoutPage.placeOrder();
		
		Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "thankyou for the order.");

		driver.quit();
	}

}
