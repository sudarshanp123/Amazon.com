package TestClasses;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import POMClasses.AddProductToCardPOM;
import POMClasses.LoginPagePOM;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.Reporter;
import org.testng.annotations.*;

public class BaseClass {

	public  WebDriver driver;

	public LoginPagePOM lp;
	public AddProductToCardPOM ac;
	public UtilityClass ut;

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


	@AfterClass
	public void logout() {
		ut.explicitwait(driver, ac.gotocardbutton());
		ac.gotocartpage();
		ut.explicitwait(driver,ac.deletebutton());
		ac.deleteaddedproduct();
		Reporter.log("Product deleted successfully",true);
		lp.logoutfromAmazon();
		Reporter.log("User logged out successfully",true);
		driver.quit();
	}



}
