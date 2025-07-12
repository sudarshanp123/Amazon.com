package TestClasses;
import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import POMClasses.AddAddressPOM;
import POMClasses.AddProductToCardPOM;
import POMClasses.LoginPagePOM;



public class TCLoginToAmazon extends BaseClass {
	public LoginPagePOM lp;
	public AddProductToCardPOM ac;
	public AddAddressPOM add;

	public WebDriver getDriver() {
		return driver;
	}

	
	@Test(priority=0)
	public void verifylogin() {

		lp = new LoginPagePOM(driver);
		lp.logintoAmazon("7767093084", "Spawar@9090");
		String expetectedtitle = "Amazon.com. Spend less. Smile more.";
		Assert.assertEquals(expetectedtitle, driver.getTitle());
		Reporter.log("user is logged in",true);

	}
	

	@Test(priority=1,dependsOnMethods = "verifylogin", enabled = true)
	public void verifyaddproducttocard() {
		lp = new LoginPagePOM(driver);
		ac = new AddProductToCardPOM(driver);       
		ac.entertextinsearchbox();
		ac.clicksearchicon();
		ac.verifySearchedmobile();
//		ac.selectradiobutton();
//		ut.explicitwait(driver,ac.getradiobutton());
		ut.explicitwait(driver, ac.addtocardbutton());
		ac.addproducttocart();
		WebElement text = ac.verifyproductaddedtocard();
		ut.explicitwait(driver, text);		
		Assert.assertEquals(text.getText(), "Added to cart");
		Reporter.log("Produdct added to card", true);


	}
	
	@Test(priority=4,dependsOnMethods = "verifyaddproducttocard" ) 
	public void verifyRemoveProduct() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		ut.clickexplicitwait(driver, ac.gotocaricon());
		ac.gotocartpage();
		ut.explicitwait(driver,ac.deletebutton());
		ac.deleteaddedproduct();
		Reporter.log("Product deleted successfully",true);
	}

	@Test (priority = 3,dependsOnMethods = "verifylogin",enabled=true)
	public void verifyAddAddress() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		add = new AddAddressPOM(driver);
		add.clickonaccount();
		add.clickonaddaddresslink(); 
		add.clickonaddressicon();
		add.clickoncountrydropdown();
		add.selectcountry();
		add.enterfirstname(ut.readstringexceldata("Address", 1, 1));  
		add.enteraddressline1(ut.readstringexceldata("Address", 1, 3));
		add.enteraddressline2(ut.readstringexceldata("Address", 1, 4));
//		add.entercity(ut.readstringexceldata("Address", 1, 5));
		add.enterstate(ut.readstringexceldata("Address", 1, 6));
		add.enterzipcode(ut.readstringexceldata("Address", 1, 7));
		add.entermobilenumber(ut.readstringexceldata("Address", 1, 2));
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ut.clickexplicitwait(driver,add.returnaddaddressbutton());
		add.clickaddaddressbutton(); 		
		String exptext = "Add ID for customs clearance";
		Assert.assertEquals(add.addidtext(), exptext);
	}
	
	@Test (priority=4, dependsOnMethods = "verifyAddAddress", enabled= true)
	public void verifyDeleteAddress() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		add = new AddAddressPOM(driver);
		add.clickonaccount();
		add.clickonaddaddresslink(); 
		add.removeaddress();
		String exptext = "Address removed";
		Assert.assertEquals(add.successmessage().getText(), exptext);
		
	}
	





}
