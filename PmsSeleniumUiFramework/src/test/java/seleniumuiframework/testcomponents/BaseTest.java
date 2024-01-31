package seleniumuiframework.testcomponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import seleniumuiframework.pageobjects.Login;
import seleniumuiframework.pageobjects.Product;

public class BaseTest {
	
	private Properties prop;
	private FileInputStream fis;
	private WebDriver driver;
	public Login loginPage;
	public final String USER_EMAIL;
	public final String USER_PASSWORD;
	public final String INVALID_USER_EMAIL;
	public final String INVALID_USER_PASSWORD;	

	public BaseTest() {
		try {
			fis = new FileInputStream("src\\main\\java\\seleniumuiframework\\resources\\GlobalProperties.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		prop = new Properties();
		
		try {
			prop.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		USER_EMAIL = prop.getProperty("userEmail");
		USER_PASSWORD = prop.getProperty("userPassword");
		INVALID_USER_EMAIL = prop.getProperty("invalidUserEmail");
		INVALID_USER_PASSWORD = prop.getProperty("invalidUserPassword");
	}
	
	private void initializeDriver() throws IOException {
		String browserType = prop.getProperty("browserType");

		switch (browserType) {
		case "Chrome":
			driver = new ChromeDriver();
			break;
		case "Firefox":
			driver = new FirefoxDriver();
			break;
		case "Edge":
			driver = new EdgeDriver();
			break;
		case "Safari":
			driver = new SafariDriver();
			break;
		default:
			driver = new ChromeDriver();
			break;
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void launchApplication() throws IOException {
		initializeDriver();
		loginPage = new Login(driver);		
		String applicationUrl = prop.getProperty("applicationUrl");		
		loginPage.goTo(applicationUrl);				
	}
	
	@AfterMethod
	public void closeDriver() {
		driver.close();
	}
}
