package seleniumuiframework.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import seleniumuiframework.pageobjects.LoginPage;
import seleniumuiframework.utils.DataProvider;

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
		String browserType = System.getProperty("browser") == null ? prop.getProperty("browserType") : System.getProperty("browser");
		
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

	protected String takeScreenshot(String testCaseName, WebDriver driver) throws IOException {
		String directory = System.getProperty("user.dir") + "reports\\" + testCaseName + ".png";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(directory);
		FileUtils.copyFile(source, target);
		return directory;
	}

	@BeforeMethod(alwaysRun = true)
	public void launchApplication(ITestContext context) throws IOException {
		initializeDriver();
		context.setAttribute("driver", driver);
		loginPage = new LoginPage(driver);
		String applicationUrl = prop.getProperty("applicationUrl");
		loginPage.goTo(applicationUrl);
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}
}
