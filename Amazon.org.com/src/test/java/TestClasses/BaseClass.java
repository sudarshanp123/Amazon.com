package TestClasses;

import java.time.Duration;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import POMClasses.LoginPagePOM;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.*;

public class BaseClass {

	public static WebDriver driver;
	LoginPagePOM lp = new LoginPagePOM(driver);
	@BeforeMethod
	public void openbrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.com/");
	}

	@AfterSuite
	public void logout() {

		driver.quit();

	}


}
