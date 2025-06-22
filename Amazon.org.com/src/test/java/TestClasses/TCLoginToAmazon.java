package TestClasses;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ExtendReport.ScreenshotUtil;
import POMClasses.LoginPagePOM;


public class TCLoginToAmazon extends BaseClass {

	public WebDriver getDriver() {
	    return driver;
	}

	@Test(priority=0)
	public void login() {
		
		LoginPagePOM lp = new LoginPagePOM(driver);
		UtilityClass ut = new UtilityClass(driver);
		lp.logintoAmazon("7767093084", "Spawar@9090");
		if(driver.getTitle().equals("Amazon.com. Spend less. Smile more.")) {
//			ut.takescreenshot();
			System.out.println("user is logged in");
			lp.logoutfromAmazon();
			System.out.println("user is logged out");
//			ut.takescreenshot();
		}else {
//			ut.takescreenshot();
			System.out.println("Test case is failed");
		}
//		throw new RuntimeException("Intentional failure for screenshot demo.");
	}
	
	@Test(priority=1)
	public void Failtestcase() {
		System.out.println("Failure Test case ");
		Assert.assertEquals(2, 3);
	}


}
