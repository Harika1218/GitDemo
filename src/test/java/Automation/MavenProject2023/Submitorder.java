package Automation.MavenProject2023;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;
import Automation.TestComponents.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Submitorder extends BaseTest {
	String itemneeded = "ADIDAS ORIGINAL";
	@Test(dataProvider ="getData",groups= {"Purchase"})
	public void submitorder(HashMap<String,String> input) throws IOException, InterruptedException{
		//public void submitorder(String email,String pswrd, String itemneeded) throws IOException, InterruptedException{
								
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("pswrd"));
		List<WebElement> productList= productCatalogue.productList();
		productCatalogue.addProductToCart(input.get("itemneeded"));
		Thread.sleep(5000);
		
		
		//verifying if correct product is added to cart
		CartPage cartPage = productCatalogue.clickCart();;
		Boolean match = cartPage.verifyItemAdded(input.get("itemneeded"));
		Assert.assertTrue(match);	
		//proceeding to checkout		
		SubmitOrderPage submitOrderPage = cartPage.proceedToCheckOut("Ind");
		//COnfirmation
		ConfirmationPage confirmationPage = submitOrderPage.checkoutpage();		
		String message = confirmationPage.confirmationMessage();		
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
	}	
	
	
	@Test(dependsOnMethods= "submitorder", retryAnalyzer = Retry.class)
	public void orderHistoryTest() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("harikareddy1204@gmail.com", "Harika@1234");
		
		OrdersPage ordersPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(itemneeded));


	}
	/*@DataProvider
	public Object[][] getData() {		
		return new Object[][]  {{"harikareddy1204@gmail.com", "Harika@1234","ADIDAS ORIGINAL"},{"anshika@gmail.com","Iamking@000", "ZARA COAT 3"}};
	}*/
	
	@DataProvider
	public Object[][] getData() {
		HashMap<String,String> map = new HashMap<String,String>();
		map.put("email", "harikareddy1204@gmail.com");
		map.put("pswrd", "Harika@1234");
		map.put("itemneeded", "ADIDAS ORIGINAL");
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		map1.put("email", "anshika@gmail.com");
		map1.put("pswrd", "Iamking@000");
		map1.put("itemneeded", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};	
		
		
				
	}
	

}
