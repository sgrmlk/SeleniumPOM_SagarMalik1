package com.selenium.pom.util;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
/**
 * @author sagar.malik
 **/

public class ExtentReportersClass
{
	
	public static ExtentReports extent;
    public static ExtentTest test;
	
	public static ExtentReports getInstance()
	{
		if(extent==null)
		{
			Date d=new Date();
	        String fileName=d.toString().replace(":", "_").replace(" ", "_")+".html";
	        System.out.println(" File Name: "+fileName);
	        extent = new ExtentReports(System.getProperty("user.dir") +"\\ExtentReports\\ExtentReport"+fileName, true);
	        extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
	        
	        extent.addSystemInfo("Host Name", "Sagar4476").addSystemInfo("Environment", "DEMO QA Tours").addSystemInfo("User Name", "Sagar Malik");
            //loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
            //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
            
		}
		return extent;
	}
}
