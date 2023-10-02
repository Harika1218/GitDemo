package Automation.MavenProject2023;

import java.time.Duration;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("harikareddy1204@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Harika@1234");
		driver.findElement(By.name("login")).click();
		
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement finalitem = items.stream().filter(item-> item.findElement(By.cssSelector("b")).getText().equals("ADIDAS ORIGINAL")).findFirst().orElse(null);
		System.out.println(finalitem.getText());
		finalitem.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("button[routerlink ='/dashboard/cart']")).click();
		
		//verifying if correct product is added to cart
		List<WebElement> products = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match= products.stream().anyMatch(s->s.getText().equals("ADIDAS ORIGINAL"));
		Assert.assertTrue(match);
		
		//proceeding to checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		WebElement cardno=driver.findElement(By.cssSelector("input[value='4542 9931 9292 2293']"));
		cardno.clear();
		cardno.sendKeys("23465533456");
		Select dropdown = new Select(driver.findElement(By.cssSelector(".field.small select:first-of-type")));
		dropdown.selectByVisibleText("04");
		
		Select dropdown1 = new Select(driver.findElement(By.cssSelector(".field.small select:last-of-type")));
		dropdown1.selectByVisibleText("12");
		Actions a= new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "ind").build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String message= driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(message, "THANKYOU FOR THE ORDER.");
		driver.close();
		
		
				
				

		
		
		
		
		
		
		

	}

}
