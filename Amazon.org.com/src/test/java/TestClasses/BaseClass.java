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

	public static WebDriver driver;
	LoginPagePOM lp = new LoginPagePOM(driver);
	AddProductToCardPOM ac = new AddProductToCardPOM(driver);
	UtilityClass ut = new UtilityClass(driver);
	@BeforeMethod
	public void openbrowser() {
		ChromeOptions options = new ChromeOptions();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver(options);		
		options.addArguments("--disable-notifications");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.com/");
//		lp.logintoAmazon("7767093084", "Spawar@9090");
	}

	@AfterSuite
	public void logout() {
		driver.quit();;

	}


}
