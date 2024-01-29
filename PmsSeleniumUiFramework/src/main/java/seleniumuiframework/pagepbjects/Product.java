package seleniumuiframework.pagepbjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import seleniumuiframework.abstractcomponents.BasePage;

public class Product extends BasePage {

	WebDriver driver;
	
	public Product(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public final String productsLocator =".mb-3";
	
	@FindBy(css=productsLocator)
	List<WebElement> products;
	
	public List<WebElement> getProducts(){
		waitForElementToAppear(By.cssSelector(productsLocator));
		return products;
	}
	
	
}
