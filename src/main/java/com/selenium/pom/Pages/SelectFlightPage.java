package com.selenium.pom.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
/**
 * @author sagar.malik
 **/
public class SelectFlightPage 
{
	WebDriver driver;

	@FindBy(how = How.NAME, using = "reserveFlights")
	WebElement BT_continue;
	
	@FindBy(how = How.XPATH, using = "//tbody/tr[2]/td[1]/b/font")
	WebElement DepartFromTo;
	
	@FindBy(how = How.NAME, using = "outFlight")
	WebElement RBT_outFlightPreference;
	
	public SelectFlightPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public BookFlightPage clickOnContinue() 
	{
		BT_continue.click();
		return PageFactory.initElements(driver, BookFlightPage.class);
	}
	
	public String getDepartFromTo() 
	{
		return DepartFromTo.getText();
	}
	
	public String getFlightPreference() 
	{
		String pref = RBT_outFlightPreference.getAttribute("value");		
		String preference = pref.substring(0, pref.indexOf("$"));
		return preference;
	}
}
