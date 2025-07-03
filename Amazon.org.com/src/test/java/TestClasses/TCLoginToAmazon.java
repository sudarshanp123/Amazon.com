package TestClasses;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import ExtendReport.ScreenshotUtil;
import POMClasses.AddProductToCardPOM;
import POMClasses.LoginPagePOM;


public class TCLoginToAmazon extends BaseClass {
	public LoginPagePOM lp;
	public AddProductToCardPOM ac;


	@Test(priority=0)
	public void login() {

		lp = new LoginPagePOM(driver);

		lp.logintoAmazon("7767093084", "Spawar@9090");
		String expetectedtitle = "Amazon.com. Spend less. Smile more.";
		Assert.assertEquals(expetectedtitle, driver.getTitle());
		Reporter.log("user is logged in",true);

	}

	@Test(priority=1)
	public void addproducttocard() {
		lp = new LoginPagePOM(driver);
		ac = new AddProductToCardPOM(driver);		
		ac.entertextinsearchbox();
		ac.clicksearchicon();
		ac.verifySearchedmobile();
		ac.addproducttocart();

		WebElement text = ac.verifyproductaddedtocard();
		ut.explicitwait(driver, text);		
		Assert.assertEquals(text.getText(), "Added to cart");
		Reporter.log("Produdct added to card", true);	
	}






}
