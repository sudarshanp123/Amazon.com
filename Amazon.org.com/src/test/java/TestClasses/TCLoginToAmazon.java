package TestClasses;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import ExtendReport.ScreenshotUtil;
import POMClasses.AddProductToCardPOM;
import POMClasses.LoginPagePOM;


public class TCLoginToAmazon extends BaseClass {

	public WebDriver getDriver() {
		return driver;
	}

	@Test(priority=0)
	public void login() {

		LoginPagePOM lp = new LoginPagePOM(driver);

		lp.logintoAmazon("7767093084", "Spawar@9090");
		String expetectedtitle = "Amazon.com. Spend less. Smile more.";
		Assert.assertEquals(expetectedtitle, driver.getTitle());
		System.out.println("user is logged in");
		lp.logoutfromAmazon();
		System.out.println("user is logged out");	
	}

	@Test(priority=1)
	public void addproducttocard() {
		LoginPagePOM lp = new LoginPagePOM(driver);
		AddProductToCardPOM ac = new AddProductToCardPOM(driver);
		lp.logintoAmazon("7767093084", "Spawar@9090");
		ac.entertextinsearchbox();
		ac.clicksearchicon();
		ac.verifySearchedmobile();
		ac.addproducttocart();
		String text = ac.verifyproductaddedtocard();
		Assert.assertEquals(text, "Added to cart");

	}




}
