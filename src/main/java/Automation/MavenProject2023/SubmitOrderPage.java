package Automation.MavenProject2023;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SubmitOrderPage {
	WebDriver driver;
	public SubmitOrderPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
      }
	@FindBy(css = ".action__submit")
	WebElement confirm;
	
	public ConfirmationPage checkoutpage() {
		confirm.click();
		return new ConfirmationPage(driver);
	}

}
