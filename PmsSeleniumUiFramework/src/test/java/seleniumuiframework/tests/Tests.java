package seleniumuiframework.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import seleniumuiframework.pageobjects.Login;
import seleniumuiframework.pageobjects.MyCart;
import seleniumuiframework.pageobjects.Product;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tests {

	public static void main(String[] args) throws InterruptedException {

		String myProduct = "Iphone 13 pro";
		WebDriver driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));

		Login loginPage = new Login(driver);
		Product productPage = new Product(driver);
		MyCart myCartPage = new MyCart(driver);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();

		loginPage.goTo();
		loginPage.login("JohnSmith@gmail.com", "StrongPass123");

		WebElement product = productPage.getProductByName(myProduct);
		productPage.addProductToCart(product);
		productPage.goToMyCartPage();		

		List<WebElement> expectedItemsInTheCart = List.of(product);
		List<WebElement> actualItemsInTheCart = myCartPage.getItemsInTheCart();

		//Assert.assertEquals(actualItemsInTheCart, expectedItemsInTheCart);
		myCartPage.clickCheckoutButton();
	
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[placeholder*='Country']"))));
		driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("states");
		List<WebElement> dropdownOptions = driver.findElements(By.cssSelector(".ta-item"));
		WebElement myOption = dropdownOptions.stream().filter(o -> o.getText().equalsIgnoreCase("united states"))
				.findFirst().orElse(null);
		myOption.click();

		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		a.click(driver.findElement(By.cssSelector(".action__submit"))).build().perform();

		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertEquals(confirmationMessage.toLowerCase(), "thankyou for the order.");

		driver.quit();
	}

}
