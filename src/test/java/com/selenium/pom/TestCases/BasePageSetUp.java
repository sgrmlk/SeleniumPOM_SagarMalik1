package com.selenium.pom.TestCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.selenium.pom.util.Constants;
import com.selenium.pom.util.ExtentReportersClass;
import com.selenium.pom.util.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * @author sagar.malik
 **/

public class BasePageSetUp extends ExtentReportersClass
{
	
	public static WebDriver driver = null;
	public static  Properties CONFIG = null;
	public static ExtentReports rep= null;
	public static ExtentTest test= null;
	
	public static int Time;
	
	public static void loadPropertiesFile()
	{
		if (CONFIG == null) 
		{	
			CONFIG = new Properties();
			try 
			{
				FileInputStream fis = new FileInputStream(Constants.CONFIG_FILE_PATH);
				CONFIG.load(fis);
			} catch (IOException e) 
			{
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
	public void openApplication() 
	{
		driver.get(CONFIG.getProperty("baseURL"));
		driver.manage().window().maximize();
		Time = Integer.parseInt(CONFIG.getProperty("time"));
		driver.manage().timeouts().implicitlyWait(Time, TimeUnit.SECONDS);
	}
	
	public static void closeBrowser() 
	{
		driver.close();
	}
	
	public String getCurrentPageTitle() 
	{
		return driver.getTitle();
	}
	
	
	public void initDriver() 
	{
		if (driver == null) 
		{
			if (CONFIG.getProperty("browser").equals("Firefox")) 
			{
				System.setProperty("webdriver.gecko.driver", "drivers\\Geko\\geckodriver.exe");
				DesiredCapabilities dc = new DesiredCapabilities();
				dc.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE);
				dc.setCapability("marionette", true);
				System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
				driver = new FirefoxDriver();
			}
			else if (CONFIG.getProperty("browser").equals("Chrome")) 
			{
				System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			}
		}
		openApplication();
	}
	//initDriver is closed
	
	@BeforeSuite
	public void beforeSuite() 
	{
		loadPropertiesFile();
		initDriver();
	}

	@AfterSuite
	public static void afterSuite() throws IOException 
	{	
		closeBrowser();
		System.out.println("reports flushed");
		rep.flush();	
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method m) 
	{
		rep= ExtentReportersClass.getInstance();
		test= rep.startTest(m.getName());
		Utility.setDefaultResult();
		
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception 
	{	
		if(result.getStatus() == ITestResult.FAILURE)
		{
			test.log(LogStatus.FAIL, "Test case failed due to below reason");
			Utility.takeScreenShot(driver, result.getName());
		}else if((result.getStatus() == ITestResult.SKIP))
        {
			test.log(LogStatus.SKIP, "Test case skipped due to below reason ");       	
        }
        else if(result.getStatus() == ITestResult.SUCCESS)
        {
        	test.log(LogStatus.PASS, "Test case passed. ");
        }

	}
	
}
