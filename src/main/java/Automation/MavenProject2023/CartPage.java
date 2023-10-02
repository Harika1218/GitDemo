package Automation.MavenProject2023;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent{

	
		WebDriver driver;
		public CartPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		
          }
		
		@FindBy(css= ".cartSection h3")
		List<WebElement> itemsInCart;
		
		@FindBy(css=".totalRow button")
		WebElement proceed;
		
		@FindBy(css="input[placeholder='Select Country']")
		WebElement country;
		
		By results = By.cssSelector(".ta-results");
		
		@FindBy(xpath ="(//button[contains(@class,'ta-item')])[2]")
		WebElement dropdown;
		
		
		
		public Boolean verifyItemAdded(String itemneeded) {
			
			Boolean match= itemsInCart.stream().anyMatch(s->s.getText().equals(itemneeded));
			return match;
					
		}
		public SubmitOrderPage proceedToCheckOut(String actual) {
			proceed.click();
			Actions a= new Actions(driver);
			a.sendKeys(country, actual).build().perform();
			waitForElementtoAppear(results);
			dropdown.click();
			return new SubmitOrderPage(driver);
			
		}
		
}
