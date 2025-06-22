package ExtendReport;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.*;

public class ExtentTestNGListener implements ITestListener {

	private static ExtentReports extent;
	private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	private void attachScreenshot(ITestResult result, String statusMessage) {
		WebDriver driver = getDriver(result);
		if (driver != null) {
			String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName());
			try {
				test.get().log(Status.INFO, statusMessage)
				.addScreenCaptureFromPath(path);
			} catch (Exception e) {
				test.get().log(Status.WARNING, "Screenshot could not be attached");
			}
		} else {
			test.get().log(Status.WARNING, "Driver is null. Screenshot not attached.");
		}
	}
	public static String getdate() {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		return date.format(new Date()); 

	}
	@Override
	public void onStart(ITestContext context) {
		ExtentHtmlReporter reporter = new ExtentHtmlReporter("test-output/HtmlReport/ExtentReport"+getdate()+".html");
		reporter.config().setReportName("Automation Report");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
		test.set(extentTest);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
//		WebDriver driver = getDriver(result);
//		String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName() + "_pass");
//		test.get().pass("Test Passed").addScreenCaptureFromPath(path);
		attachScreenshot(result, "✅ Test Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = getDriver(result);
		String path = ScreenshotUtil.captureScreenshot(driver, result.getMethod().getMethodName() + "_fail");
		test.get().fail(result.getThrowable()).addScreenCaptureFromPath(path);
//		attachScreenshot(result, "❌ Test Failed: " + result.getThrowable());
//		test.get().fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

	private WebDriver getDriver(ITestResult result) {
		try {
			return (WebDriver) result.getInstance()
					.getClass()
					.getMethod("getDriver")
					.invoke(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}
