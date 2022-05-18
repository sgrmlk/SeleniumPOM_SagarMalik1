package com.selenium.pom.TestCases;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.selenium.pom.Pages.FlightFinderPage;
import com.selenium.pom.Pages.LandingPage;
import com.selenium.pom.Pages.SelectFlightPage;
import com.selenium.pom.util.Constants;
import com.selenium.pom.util.ExtentReportersClass;
import com.selenium.pom.util.Utility;



import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
/**
 * @author sagar.malik
 **/
public class MakeMyTrip extends BasePageSetUp
{
	
	@Test(priority=1, dataProvider = "SearchFlightsDP")
	public void searchFlights(HashMap<String, String> table) throws Exception 
	{
		
		String fromLocation = table.get("FromLocation");
		String toLocation = table.get("ToLocation");
		
		LandingPage landPage = PageFactory.initElements(driver, LandingPage.class);
		FlightFinderPage flightFinder = landPage.searchFlights(fromLocation, toLocation);
		System.out.println("____"+flightFinder.returnFlightCodeforLowestHighest());
	    flightFinder.clickOnPriceSorting();
	    System.out.println("+++" +flightFinder.returnFlightCodeforLowestHighest());
		Assert.assertEquals(table.get("ExpectedResult"), driver.getTitle());
		driver.findElement(By.xpath("//a[contains(.,'SIGN-OFF')]")).click();
		//rep.flush();
	}	
	

	
	@DataProvider(name="SearchFlightsDP")
	public Object[][] TC_001DataProvider() throws Exception 
	{
		System.out.println("Hello Fetching Data");
		return Utility.getData ("TC01", Constants.TEST_DATA_PATH, Constants.TEST_DATA_SHEET);
		
	}
	

}
