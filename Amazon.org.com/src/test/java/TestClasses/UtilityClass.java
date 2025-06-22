package TestClasses;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class UtilityClass {


	public static WebDriver driver;

	public UtilityClass(WebDriver driver) {
		this.driver=driver;
	}

	// code for taking current date of system 
	public  String getdate() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		return date.format(new Date()); 

	}
	// Code for taking screen shot 
	public void takescreenshot() {
		TakesScreenshot ts= (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./Screenshot/ScreenSS"+ getdate()+".png");
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {		
			e.printStackTrace();
		}
	}
}
