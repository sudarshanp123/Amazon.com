package TestClasses;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UtilityClass  {


	public static WebDriver driver;
	public FileInputStream fis;
	public Workbook book;
	public String filepath ="./src/test/resources/Amazon.xlsx";

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
	public WebElement explicitwait(WebDriver driver,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.visibilityOf(ele));

	}
	public WebElement clickexplicitwait(WebDriver driver,WebElement ele) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		return wait.until(ExpectedConditions.elementToBeClickable(ele));

	}
	
	public String readstringexceldata(String sheetname,int row,int col) {
		
		try {
			 fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return book.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		
		
	}
	public long readintexceldata(String sheetname,int row,int col) {
		
		try {
			 fis = new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			 book = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (long) book.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue();
		
		
	}


}
