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
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
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
		String browserType = System.getProperty("browser") == null ? prop.getProperty("browserType")
				: System.getProperty("browser");
		Boolean headlessMode = Boolean
				.parseBoolean(System.getProperty("headless") == null ? prop.getProperty("headlessMode")
						: System.getProperty("headless"));
		AbstractDriverOptions options = null;

		switch (browserType) {
		case "Chrome":
			options = new ChromeOptions();
			if (headlessMode) {
				((ChromeOptions) options).addArguments("headless");
				((ChromeOptions) options).addArguments("window-size=1400,900");
			}
			driver = new ChromeDriver((ChromeOptions) options);
			break;
		case "Firefox":
			options = new FirefoxOptions();
			if (headlessMode) {
				((FirefoxOptions) options).addArguments("headless");
				((FirefoxOptions) options).addArguments("window-size=1400,900");
			}
			driver = new FirefoxDriver((FirefoxOptions) options);
			break;
		case "Edge":
			options = new EdgeOptions();
			if (headlessMode) {
				((EdgeOptions) options).addArguments("headless");
				((EdgeOptions) options).addArguments("window-size=1400,900");
			}
			driver = new EdgeDriver((EdgeOptions) options);
			break;
		case "Safari":
			// Headless mode for Safari browser is not supported at the moment
			driver = new SafariDriver();
			break;
		default:
			options = new ChromeOptions();
			if (headlessMode) {
				((ChromeOptions) options).addArguments("headless");
				((ChromeOptions) options).addArguments("window-size=1400,900");
			}
			driver = new ChromeDriver((ChromeOptions) options);
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
