package seleniumuiframework.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumuiframework.abstractcomponents.BasePage;

public class LoginPage extends BasePage{

	private WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	private final String USER_EMAIL_LOCATOR ="userEmail";
	private final String USER_PASSWORD_LOCATOR ="userPassword";
	private final String LOGIN_LOCATOR ="login";
	
	@FindBy(id=USER_EMAIL_LOCATOR)
	WebElement userEmail;
	
	@FindBy(id=USER_PASSWORD_LOCATOR)
	WebElement userPassword;
	
	@FindBy(id=LOGIN_LOCATOR)
	WebElement loginButton;
	
	public LoginPage goTo(String url) {
		driver.get(url);
		return this;
	}
	
	public ProductPage login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		return new ProductPage(driver);
	}
}
