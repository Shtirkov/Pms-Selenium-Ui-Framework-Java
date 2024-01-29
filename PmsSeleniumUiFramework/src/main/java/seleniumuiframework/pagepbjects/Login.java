package seleniumuiframework.pagepbjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumuiframework.abstractcomponents.BasePage;

public class Login extends BasePage{

	WebDriver driver;
	
	public Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public final String userEmailLocator ="userEmail";
	public final String userPasswordLocator ="userPassword";
	public final String loginLocator ="login";
	
	@FindBy(id=userEmailLocator)
	WebElement userEmail;
	
	@FindBy(id=userPasswordLocator)
	WebElement userPassword;
	
	@FindBy(id=loginLocator)
	WebElement loginButton;
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
	}
}
