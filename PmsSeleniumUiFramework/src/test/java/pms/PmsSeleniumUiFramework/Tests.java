package pms.PmsSeleniumUiFramework;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Tests {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("JohnSmith@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("StrongPass123");
		driver.findElement(By.id("login")).click();

		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ngx-pagination")));
		List<WebElement> productsList = driver.findElements(By.xpath("//div[@class='card']"));
		List<String> addedProducts = new ArrayList<String>();
		
		productsList.stream().forEach(p -> {
			w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
			addedProducts.add(p.findElement(By.cssSelector("h5")).getText());
			p.findElement(By.cssSelector(".w-10")).click();
			w.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("toast-container"))));
		});
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		w.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//button[text()='Checkout']"))));

		List<WebElement> itemsInTheCart = driver.findElements(By.cssSelector(".cartWrap h3"));
		List<String> itemsInTheCartAsString = itemsInTheCart.stream().map(i -> i.getText()).collect(Collectors.toList());
				
		Assert.assertEquals(itemsInTheCartAsString, addedProducts);
				
		driver.quit();
	}

}
