package seleniumuiframework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumuiframework.pageobjects.Checkout;
import seleniumuiframework.pageobjects.MyCart;
import seleniumuiframework.pageobjects.OrderConfirmation;
import seleniumuiframework.pageobjects.Product;
import seleniumuiframework.testcomponents.BaseTest;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;

public class SubmitOrderTests extends BaseTest {
	
	@Test
	public void submitOrder() throws IOException {
		String myProduct = "Iphone 13 pro";		
		Product productPage = launchApplication();

		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		MyCart myCartPage = productPage.goToMyCartPage();

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
