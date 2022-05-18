package com.selenium.pom.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.log4testng.Logger;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.relevantcodes.extentreports.ExtentTest;
/**
 * @author sagar.malik
 **/
public class Utility extends ExtentReportersClass
{
	public static boolean RESULT_FLAG = false;
	public static String RESULT = null;
	public static String RESULT_DESC = null;
	public static String TC_ID = null;
	public static String TC_DESC = null;
	public static String ERROR_DESC = null;
	public static Logger APPLICATION_LOGS = null;
	public static File scrFile;
	public static String failureImageName = null;

	
	public static void selectDropDownValue(WebElement dropDownWebElement, String textToSelect) 
	{
		Select dropDown = new Select(dropDownWebElement);
		dropDown.selectByVisibleText(textToSelect);
	}

	public static Object[][] getData(String strTCName, String SheetPath, String strTestSuiteName) throws FilloException
	{
		Object[][] dataSetCollection = new Object[1][1];
		Fillo fillo=new Fillo();           
        String value=null;
        String k2=null;
        System.out.println("Before Creating connection");
        Connection connection= fillo.getConnection(SheetPath);           
        String strQuery="Select * from "+strTestSuiteName +" where TC_ID='"+strTCName+"'" ;
        System.out.println(strQuery);
        
        Recordset recordset= connection.executeQuery(strQuery);
        HashMap<String,String> hash=new HashMap<String,String>();  
        
        ArrayList<String> keys=recordset.getFieldNames();
        System.out.println("Operation");
		while(recordset.next()) 
		{
		 	  for (String k1:keys)
		 	  {
		 		  k2=k1;
		 		  value=recordset.getField(k1);
		 		  hash.put(k2, value);
		 	  }
		}                   
        recordset.close();
        connection.close();
        dataSetCollection[0][0] = hash;
		return dataSetCollection;	
	}
	
	
	public static void compareResult(WebDriver driver, String TestCaseID, String Description, String ActualRst, String ExpectedRst, ExtentTest test) throws IOException 
	{
		    TC_ID = TestCaseID;
			System.out.println("Actual Result: "+ActualRst + " Expected Result is: "+ExpectedRst);
			Assert.assertEquals(ActualRst, ExpectedRst);
			System.out.println("Result Flag is: "+ RESULT_FLAG);
	}
	
	public static String takeScreenShot(WebDriver driver, String testCaseDesc) throws IOException 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		Date currentDate = new Date();
		String fileDir = System.getProperty("user.dir")+"\\screenshots\\" + dateFormat.format(currentDate);
		File file = new File(fileDir);
		if (!file.exists()) 
		{
			file.mkdir();
		}
		scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		failureImageName = testCaseDesc + ".png";
		String failureImageLocation = fileDir + "\\" + failureImageName;
		System.out.println("ImageLocation"+ failureImageLocation );
		FileUtils.copyFile(scrFile, new File(failureImageLocation));
		return failureImageLocation;
	}
	
	public static void catchExceptions(ITestResult result) throws IOException 
	{
		
		TC_DESC = result.getName();
		String destDir = ".out/screenshots/";
		String userDirector = "./screenshots/";
		String failureImageLocation = destDir + failureImageName;
		FileUtils.copyFile(scrFile, new File(failureImageLocation));
		String RstDsc = RESULT_DESC.toString().replace("\n", "<br>&nbsp;&rArr;&nbsp;");
		APPLICATION_LOGS.info(RstDsc);

		/** Log Output */
		log("<div id=\"log\"><strong>TestCase#</strong> " + TC_ID + "</div>");
		log("<div><strong>TestCase Description:</strong> " + TC_DESC + "</div>");
		log("<div><span style=\"color: #ff0000;\">" + RstDsc.substring(4) + "</span></div>");
		log("<div style=\"padding-left: 30px;\"><a href=\"" + userDirector + failureImageName + "\"><img src=\""
				+ userDirector + failureImageName + "\" alt=\"" + userDirector + failureImageName
				+ "\" width=\"400\" height=\"200\" /></a></div>");
		log("<div style=\"padding-left: 30px;\"><a href=\"" + userDirector + failureImageName
				+ "\"><strong><span style=\"text-decoration: underline;\"><em>&rArr; Click here to view screenhot.</em></span></strong></a></div>");
		log("<div>&nbsp;</div>");
	
	}
	
	
	private static void log(String text) 
	{
		Reporter.log(text);
	}
	
	public static void setDefaultResult() 
	{
	
		TC_ID = null;
		RESULT = null;
		RESULT_DESC = null;
		RESULT_FLAG = true;
	
	}
	
}
