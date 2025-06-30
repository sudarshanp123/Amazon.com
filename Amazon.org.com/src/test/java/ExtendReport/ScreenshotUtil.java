package ExtendReport;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
	
	public WebDriver driver;
	public static String getdate() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		return date.format(new Date()); 

	}
	public static String captureScreenshot(WebDriver driver, String screenshotName) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String destPath = "test-output/screenshots/" + screenshotName+getdate() + ".png";
        File destFile = new File(destPath);
        try {
            destFile.getParentFile().mkdirs(); // Create directories if needed
            Files.copy(src.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String absolutepath= destFile.getAbsolutePath();
        return absolutepath;
    }
}
