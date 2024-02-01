package seleniumuiframework.tests;

import org.testng.annotations.Test;
import org.testng.Assert;

import seleniumuiframework.testcomponents.BaseTest;

public class ErrorValidationsTests extends BaseTest {
	
	@Test
	public void InvalidLoginCredentialsErrorValidation() {
		loginPage.login(INVALID_USER_EMAIL, INVALID_USER_PASSWORD);
		Assert.assertEquals(loginPage.getToastMessageText(), "Incorrect email or password.");
	}
}
