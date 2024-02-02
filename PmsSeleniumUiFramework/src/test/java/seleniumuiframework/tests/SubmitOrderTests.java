package seleniumuiframework.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumuiframework.frameworkcomponents.BaseTest;
import seleniumuiframework.pageobjects.CheckoutPage;
import seleniumuiframework.pageobjects.MyCartPage;
import seleniumuiframework.pageobjects.OrderConfirmationPage;
import seleniumuiframework.pageobjects.OrdersPage;
import seleniumuiframework.pageobjects.ProductPage;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;

public class SubmitOrderTests extends BaseTest {

	private String myProduct = "Iphone 13 pro";

	@Test(groups = { "Orders" }, dataProvider = "userData")
	public void SubmitOrder(String userEmail, String userPassword) throws IOException {
		ProductPage productPage = loginPage.login(userEmail, userPassword);
		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		MyCartPage myCartPage = productPage.goToMyCartPage();
		Assert.assertTrue(myCartPage.verifyThatProductIsAddedToCart(myProduct));
		CheckoutPage checkoutPage = myCartPage.clickCheckoutButton();
		checkoutPage.selectCountryByName("United states");
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();

		Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "thankyou for the order.");
	}

	@Test(dependsOnMethods = { "SubmitOrder" }, groups = { "Orders" }, dataProvider = "userData")

	public void OrderIsDisplayedInOrdersPageWhenItIsSubmitted(String userEmail, String userPassword) {
		ProductPage productPage = loginPage.login(userEmail, userPassword);
		OrdersPage ordersPage = productPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyThatOrderIsPlaced(myProduct));
	}

	@DataProvider(name = "userData")
	private Object[][] getUserData() throws IOException {
		List<HashMap<String, String>> data = dp
				.getDataFromJsonFile("src\\test\\java\\seleniumuiframework\\testdata\\UserData.json");
		String userEmail = data.get(0).get("email");
		String userPassword = data.get(0).get("password");
		return new Object[][] { { userEmail, userPassword }};
	}
}
