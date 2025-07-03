package TestClasses;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import POMClasses.AddProductToCardPOM;
import POMClasses.LoginPagePOM;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;

public class BaseClass {

	public  WebDriver driver;
	
	public LoginPagePOM lp;
	public AddProductToCardPOM ac;
	public UtilityClass ut;
//	public WebDriver getDriver() {
//		return driver;
//	}
	@BeforeClass
	public void openbrowser() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);		
		options.addArguments("--disable-notifications");
		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		lp = new LoginPagePOM(driver);
		ac = new AddProductToCardPOM(driver);
		ut = new UtilityClass(driver);
		
	}
//	@BeforeMethod
//	public void navigatetourl() {
//	
////		lp.logintoAmazon("7767093084", "Spawar@9090");
//	}
	
	@AfterClass
	public void logout() {
		lp.logoutfromAmazon();
		driver.quit();
	}
// 
//	@AfterTest
//	public void browserteardown() {
//		driver.quit();;
//
//	}


}
