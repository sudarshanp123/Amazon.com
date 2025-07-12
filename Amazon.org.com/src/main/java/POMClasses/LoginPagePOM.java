package POMClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import com.aventstack.extentreports.model.Report;

public class LoginPagePOM {
	public static WebDriver driver;
	// Find all webelements on Login Page
	@FindBy (xpath="//button[text()='Continue shopping']")
	private WebElement continueshopping;
	@FindBy (xpath="//div[@id='nav-link-accountList']")
	private WebElement signinlink;
	@FindBy (xpath="//input[@id='ap_email_login']")
	private WebElement usernametextbox;
	@FindBy (xpath="//input[@type='submit']")
	private WebElement continuebutton;
	@FindBy (xpath="//div/ul/li/a[text()='India ']")
	private WebElement countrycode;
	@FindBy (xpath="//span[@class='a-button-text a-declarative']")
	private WebElement countrycodedropdown;
	@FindBy (xpath="//input[@type='password']")
	private WebElement passwordtextbox;
	@FindBy (xpath="//input[@type='submit']")
	private WebElement siginbutton;
	@FindBy (xpath="//span[contains(text(),'Account &')]")
	private WebElement movetoaccount;
	@FindBy (xpath="//span[text()='Sign Out']")
	private WebElement signoutbutton;



	// created constructor to access the private data members
	public LoginPagePOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void logintoAmazon(String user, String pass) {

		if(driver.getTitle().equals("Amazon.com")) {
			continueshopping.click();
			signinlink.click();
			usernametextbox.sendKeys(user);
			countrycodedropdown.click();
			countrycode.click();
			continuebutton.click();
			passwordtextbox.sendKeys(pass);
			siginbutton.click();
		}
		else if(driver.getTitle().equals("Amazon.com. Spend less. Smile more.")) {
			signinlink.click();
			usernametextbox.sendKeys(user);
			countrycodedropdown.click();
			countrycode.click();
			continuebutton.click();
			passwordtextbox.sendKeys(pass);
			siginbutton.click();
		}
		else {
			Reporter.log("Wrong page displayed",true);
		}
		
	}

	public void logoutfromAmazon() {

		Actions act = new Actions(driver);
		act.moveToElement(movetoaccount).perform();
		act.moveToElement(signoutbutton).click().perform();

	}




}
