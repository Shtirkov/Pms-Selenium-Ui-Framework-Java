package seleniumuiframework.frameworkcomponents;

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
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import seleniumuiframework.pageobjects.LoginPage;

public class BaseTest {
	
	private Properties prop;
	private FileInputStream fis;
	private WebDriver driver;
	public DataProvider dp;
	
	public LoginPage loginPage;

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
		
		dp = new DataProvider();
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

	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException {
		initializeDriver();
		loginPage = new LoginPage(driver);		
		String applicationUrl = prop.getProperty("applicationUrl");		
		loginPage.goTo(applicationUrl);				
	}
	
	@AfterMethod(alwaysRun=true)
	public void closeDriver() {
		driver.close();
	}
}
