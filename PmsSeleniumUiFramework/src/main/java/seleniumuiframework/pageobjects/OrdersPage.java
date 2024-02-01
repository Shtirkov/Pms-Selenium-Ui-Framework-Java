package seleniumuiframework.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import seleniumuiframework.abstractcomponents.BasePage;

public class OrdersPage extends BasePage{

	private final String ORDERED_ITEMS_LOCATOR = "tr[class='ng-star-inserted']";
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ORDERED_ITEMS_LOCATOR)
	List<WebElement> OrderedItems;
	
	public Boolean verifyThatOrderIsPlaced(String productName) {			
		return OrderedItems.stream().anyMatch(p -> p.findElement(By.cssSelector(" td:nth-of-type(2)")).getText().equalsIgnoreCase(productName));
	}
}
