package Automation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Automation.MavenProject2023.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public LandingPage landingPage;
	public WebDriver initializeDriver() throws IOException {
		
		//Properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream((System.getProperty("user.dir")+"\\src\\main\\java\\Automation\\Resources\\GlobalData.properties"));
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser"): prop.getProperty("browser");
		if(browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browserName.contains("headless")) {
				options.addArguments("headless");
			}
		 driver = new ChromeDriver(options);
		 driver.manage().window().setSize(new Dimension(1440,990)); //full screen
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
		
	}
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		WebDriver driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		driver.close();
	}
	
	public String getScreenshot(String testCasename,WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File( System.getProperty("user.dir")+"//reports//"+testCasename+".png"));
		return System.getProperty("user.dir")+"//reports//"+testCasename+".png"; 
		
		
	}
	
	
}

