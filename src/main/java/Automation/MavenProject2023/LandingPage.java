package Automation.MavenProject2023;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Automation.AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent{

	
		WebDriver driver;
		public LandingPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
		
          }
		@FindBy(id = "userEmail")
		WebElement mail;
		
		@FindBy(id = "userPassword")
		WebElement pwd;
		
		@FindBy(name = "login")
		WebElement submit;
		
		@FindBy(css="[class*= 'flyInOut']")
		WebElement error;
		
		public ProductCatalogue loginApplication(String email, String pswrd) {
			
			mail.sendKeys(email);
			pwd.sendKeys(pswrd);
			submit.click();
			return new ProductCatalogue(driver);
		}
		public String errorMessage() {
			waitForWebElementtoAppear(error);
			return error.getText();
		}
		
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client");
		}

}
