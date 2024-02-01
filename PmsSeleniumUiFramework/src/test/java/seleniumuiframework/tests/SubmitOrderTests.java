package seleniumuiframework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumuiframework.pageobjects.CheckoutPage;
import seleniumuiframework.pageobjects.MyCartPage;
import seleniumuiframework.pageobjects.OrderConfirmationPage;
import seleniumuiframework.pageobjects.ProductPage;
import seleniumuiframework.testcomponents.BaseTest;
import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;

public class SubmitOrderTests extends BaseTest {
	
	@Test
	public void SubmitOrder() throws IOException {
		String myProduct = "Iphone 13 pro";		
		ProductPage productPage = loginPage.login(USER_EMAIL, USER_PASSWORD);
		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		MyCartPage myCartPage = productPage.goToMyCartPage();

		List<String> expectedItemsInTheCart = List.of(myProduct);
		List<WebElement> actualItemsInTheCart = myCartPage.getItemsInTheCart();

		Assert.assertEquals(actualItemsInTheCart.size(), expectedItemsInTheCart.size());

		for (int i = 0; i < expectedItemsInTheCart.size(); i++) {
			String actualProductName = actualItemsInTheCart.get(i).getText().toLowerCase();
			String expectedProductName = expectedItemsInTheCart.get(i).toLowerCase();
			Assert.assertEquals(actualProductName, expectedProductName);
		}

		CheckoutPage checkoutPage = myCartPage.clickCheckoutButton();
		checkoutPage.selectCountryByName("United states");
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();

		Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "thankyou for the order.");	}

}
