package pms.PmsSeleniumUiFramework;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tests {

	public static void main(String[] args) throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		String myProduct = "Iphone 13 pro";

		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("JohnSmith@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("StrongPass123");
		driver.findElement(By.id("login")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ngx-pagination")));
		List<WebElement> productsList = driver.findElements(By.xpath("//div[@class='card']"));
		WebElement wantedProduct = productsList.stream()
				.filter(p -> p.findElement(By.cssSelector("h5")).getText().equalsIgnoreCase(myProduct)).findFirst()
				.orElse(null);

		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		wantedProduct.findElement(By.cssSelector(".w-10")).click();
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));		

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Checkout']"))));

		List<WebElement> itemsInTheCart = driver.findElements(By.cssSelector(".cartWrap h3"));
		
		Assert.assertEquals(1, itemsInTheCart.size());
		String actualItemInCart = itemsInTheCart.get(0).getText();
		Assert.assertEquals(actualItemInCart.toLowerCase(), myProduct.toLowerCase());			
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		w.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[placeholder*='Country']"))));
		driver.findElement(By.cssSelector("[placeholder*='Country']")).sendKeys("states");
		List<WebElement> dropdownOptions = driver.findElements(By.cssSelector(".ta-item"));
		WebElement myOption = dropdownOptions.stream().filter(o -> o.getText().equalsIgnoreCase("united states")).findFirst().orElse(null);		
		myOption.click();	
		
		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		a.click(driver.findElement(By.cssSelector(".action__submit"))).build().perform();
		
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertEquals(confirmationMessage.toLowerCase(), "thankyou for the order.");
		
		driver.quit();
	}

}
