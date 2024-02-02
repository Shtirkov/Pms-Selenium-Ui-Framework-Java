package seleniumuiframework.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import seleniumuiframework.frameworkcomponents.BaseTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

public class ErrorValidationsTests extends BaseTest {

	@Test(groups = { "ErrorValidations" }, dataProvider="userData")
	public void InvalidLoginCredentialsErrorValidation(String userEmail, String userPassword) throws IOException {
		loginPage.login(userEmail, userPassword);
		Assert.assertEquals(loginPage.getToastMessageText(), "Incorrect email or password.");
	}

	@DataProvider(name = "userData")
	private Object[][] getUserData() throws IOException {
		List<HashMap<String, String>> data = dp
				.getDataFromJsonFile("src\\test\\java\\seleniumuiframework\\testdata\\InvalidUserData.json");
		String userEmail = data.get(0).get("email");
		String userPassword = data.get(0).get("password");
		return new Object[][] { { userEmail, userPassword } };
	}
}
