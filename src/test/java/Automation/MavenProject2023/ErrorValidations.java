package Automation.MavenProject2023;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import Automation.TestComponents.BaseTest;

public class ErrorValidations extends BaseTest {
	
	
	@Test(groups= {"ErrorHandling"})
	public void errorValidationPage() {
		ProductCatalogue productCatalogue = landingPage.loginApplication("harikareddy04@gmail.com", "Hari@1234");
		Assert.assertEquals(landingPage.errorMessage(), "Incorrect email or password.");
	
	}
	@Test
	public void productErrorValidations() throws InterruptedException {
		String itemneeded = "ADIDAS ORIGINAL";				
		ProductCatalogue productCatalogue = landingPage.loginApplication("rahulshetty@gmail.com", "Iamking@000");
		List<WebElement> productList= productCatalogue.productList();
		productCatalogue.addProductToCart(itemneeded);
		Thread.sleep(5000);
		//verifying if correct product is added to cart
		CartPage cartPage = productCatalogue.clickCart();;
		Boolean match = cartPage.verifyItemAdded("ADIDAS ORIGINAL 44");
		Assert.assertFalse(match);
	}
	
}
