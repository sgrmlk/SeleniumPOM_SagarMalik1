package com.selenium.pom.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
/**
 * @author sagar.malik
 **/
public class LandingPage 
{
	WebDriver driver;
	
	@FindBy(how = How.CSS, using = "#fromCity")
	WebElement EDT_FromLocation;
	
	@FindBy(how = How.CSS, using = "div.react-autosuggest__container.react-autosuggest__container--open > input")
	WebElement DRP_ToLocation;
	
	@FindBy(how = How.CSS, using = "#toCity")
	WebElement EDT_ToLocation;
	
	@FindBy(how = How.XPATH, using = "//a[text()='Search']")
	WebElement BTN_Search;

	//@FindBy(how = How.XPATH, using = "//a[contains(.,'SIGN-ON')]") 
	@FindBy(how = How.XPATH, using = "//i[@class= 'wewidgeticon we_close']")
	WebElement BTN_Close;

	@FindBy(how = How.XPATH, using = "//iframe[contains(@name,'notification')]")
	WebElement Iframe;
	
	@FindBy(how = How.CSS, using = "p.font14.appendBottom5.blackText")
	List<WebElement> Dropdownvalues ;
	
	@FindBy(how = How.CSS, using = "li.menu_Flights > a > span.false.chNavText.darkGreyText")
	WebElement FlightMenu ;

	public LandingPage(WebDriver driver) 
	{
		this.driver = driver;
	}

	public FlightFinderPage searchFlights(String from, String to) 
	{
		
		System.out.println("Before Login: "+driver.getTitle());
		try {
			Thread.sleep(4000);
			driver.switchTo().frame(Iframe);
			BTN_Close.click();
			driver.switchTo().parentFrame();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		EDT_FromLocation.click();
		DRP_ToLocation.sendKeys(from);
		Dropdownvalues.get(0).click();

		Dropdownvalues.get(1).click();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FlightMenu.click();
		BTN_Search.click();
		System.out.println("After Login: "+driver.getTitle());
		return PageFactory.initElements(driver, FlightFinderPage.class);
	}
	

}
