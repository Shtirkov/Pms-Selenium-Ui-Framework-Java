package seleniumuiframework.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import seleniumuiframework.pageobjects.CheckoutPage;
import seleniumuiframework.pageobjects.MyCartPage;
import seleniumuiframework.pageobjects.OrderConfirmationPage;
import seleniumuiframework.pageobjects.OrdersPage;
import seleniumuiframework.pageobjects.ProductPage;
import seleniumuiframework.testcomponents.BaseTest;
import java.io.IOException;
import org.openqa.selenium.WebElement;

public class SubmitOrderTests extends BaseTest {
	
	private String myProduct = "Iphone 13 pro";
	
	@Test(groups= {"Orders"}) 
	public void SubmitOrder() throws IOException {
		String myProduct = "Iphone 13 pro";		
		ProductPage productPage = loginPage.login(USER_EMAIL, USER_PASSWORD);
		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		MyCartPage myCartPage = productPage.goToMyCartPage();
		Assert.assertTrue(myCartPage.verifyThatProductIsAddedToCart(myProduct));
		CheckoutPage checkoutPage = myCartPage.clickCheckoutButton();
		checkoutPage.selectCountryByName("United states");
		OrderConfirmationPage orderConfirmationPage = checkoutPage.placeOrder();

		Assert.assertEquals(orderConfirmationPage.getOrderConfirmationMessage(), "thankyou for the order.");
		}
	
	@Test(dependsOnMethods = {"SubmitOrder"}, groups= {"Orders"})
	
	public void OrderIsDisplayedInOrdersPageWhenItIsSubmitted() {		
		ProductPage productPage = loginPage.login(USER_EMAIL, USER_PASSWORD);
		OrdersPage ordersPage = productPage.goToOrdersPage();
		Assert.assertTrue(ordersPage.verifyThatOrderIsPlaced(myProduct));
	}
}
