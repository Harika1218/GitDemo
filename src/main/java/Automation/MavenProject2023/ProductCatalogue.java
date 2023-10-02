package Automation.MavenProject2023;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import Automation.AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {

	
		WebDriver driver;
		public ProductCatalogue(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		
          }
		@FindBy(css = ".mb-3")
		List<WebElement> items;
		
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By toastMsg = By.id("toast-container");
		By disappear = By.cssSelector(".ng-animating");
					
		
		public  List<WebElement> productList() {			
			return items;					
		}
		
		public WebElement getProductByName(String itemneeded) {
			
			WebElement finalitem = productList().stream().filter(item-> item.findElement(By.cssSelector("b")).getText().equals(itemneeded)).findFirst().orElse(null);
			return finalitem;		
		}
		public void addProductToCart(String itemneeded) {
			getProductByName( itemneeded).findElement(addToCart).click();
			waitForElementtoAppear(toastMsg);
			waitForElementtoADisappear(disappear);
			
		}
		
			

}
