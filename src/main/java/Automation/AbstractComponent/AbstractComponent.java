package Automation.AbstractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Automation.MavenProject2023.CartPage;
import Automation.MavenProject2023.OrdersPage;

public class AbstractComponent {
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css= "button[routerlink ='/dashboard/cart']")
	WebElement cart;
	
	@FindBy(css= "button[routerlink* ='myorders']")
	WebElement orders;
	
	//By.id("toast-container")
	public void waitForElementtoAppear(By FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(FindBy));
	}
	
	public void waitForElementtoADisappear(By FindBy) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(FindBy));
        
	} 
	public void waitForWebElementtoAppear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public CartPage clickCart() {
		cart.click();
		return  new CartPage(driver);
	}
	public OrdersPage goToOrderPage() {
		orders.click();
		return new OrdersPage(driver);
		
	}
	
	

}
