package seleniumuiframework.testcomponents;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import seleniumuiframework.pageobjects.Login;
import seleniumuiframework.pageobjects.Product;

public class BaseTest {

	protected WebDriver driver;
	private Properties prop;
	private FileInputStream fis;

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

	public Product launchApplication() throws IOException {
		initializeDriver();
		Login loginPage = new Login(driver);
		String userEmail = prop.getProperty("userEmail");
		String userPassword = prop.getProperty("userPassword");
		String applicationUrl = prop.getProperty("applicationUrl");
		
		loginPage.goTo(applicationUrl);		
		return loginPage.login(userEmail, userPassword);		
	}
}
