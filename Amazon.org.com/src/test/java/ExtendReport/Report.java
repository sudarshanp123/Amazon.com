package ExtendReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Report {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ExtentReports extentreport = new ExtentReports();
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter("./Screenshot/index.html");
		extentreport.attachReporter(sparkreporter);
		
		ExtentTest test01= extentreport.createTest("Test 1");
		test01.pass("Test is passed ");
		ExtentTest test02= extentreport.createTest("Test 1");
		test02.log(Status.FAIL, "Test case Failed");
		extentreport.createTest("Test 1").skip("Test case Skipped");
		extentreport.flush();
	}

}
