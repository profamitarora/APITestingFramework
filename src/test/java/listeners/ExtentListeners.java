package listeners;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import utilities.MonitoringMail;
import utilities.TestConfig;




public class ExtentListeners implements ITestListener, ISuiteListener {

	static Date d = new Date();
	static String fileName = "Extent_" + d.toString().replace(":", "_").replace(" ", "_") + ".html";

	static String messageBody;
	
	private static ExtentReports extent = ExtentManager.createInstance(System.getProperty("user.dir")+"\\reports\\"+fileName);
	
	public static ThreadLocal<ExtentTest> testReport = new ThreadLocal<ExtentTest>();
	

	public void onTestStart(ITestResult result) {

	
		ExtentTest test = extent.createTest(result.getTestClass().getName()+"     @TestCase : "+result.getMethod().getMethodName());
        testReport.set(test);
        

	}

	public void onTestSuccess(ITestResult result) {

		
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"TEST CASE:- "+ methodName.toUpperCase()+ " PASSED"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testReport.get().pass(m);
		

	}

	public void onTestFailure(ITestResult result) {

	
		
		
		String excepionMessage=Arrays.toString(result.getThrowable().getStackTrace());
		testReport.get().fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
				+ "</font>" + "</b >" + "</summary>" +excepionMessage.replaceAll(",", "<br>")+"</details>"+" \n");
		
	/*	try {

			ExtentManager.captureScreenshot();
			testReport.get().fail("<b>" + "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
					MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
							.build());
		} catch (IOException e) {

		}*/
		
		String failureLogg="TEST CASE FAILED";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testReport.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult result) {
		String methodName=result.getMethod().getMethodName();
		String logText="<b>"+"Test Case:- "+ methodName+ " Skipped"+"</b>";		
		Markup m=MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testReport.get().skip(m);

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {

		

	}

	public void onFinish(ITestContext context) {

		if (extent != null) {

			extent.flush();
		}

	}

	public void onFinish(ISuite suite) {
		
		String ip="";
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String messageBody = "http://"+ip+":8080/job/ApiTestingFramework%20-%20GIT/EXTENT_20HTML_20Report/"+fileName;
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

}
