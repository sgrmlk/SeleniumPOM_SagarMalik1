package com.selenium.pom.Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.selenium.pom.util.Utility;
/**
 * @author sagar.malik
 **/

public class FlightFinderPage 
{
	
	WebDriver driver;
	
	@FindBys(value = { @FindBy(how = How.NAME, using = "tripType") })
	WebElement RBT_tripType;
	
	@FindBy(how = How.CSS, using = ".fliCode")
	List<WebElement> List_FlightCode;
	
	@FindBy(how = How.CSS, using = "//div[@class='textRight flexOne']")
	List<WebElement> List_Price;
	
	@FindBy(how = How.CSS, using = "#sorting-togglers > div.make-flex.sort-price > div>span")
	WebElement Sort_price;
	
	
	
	public FlightFinderPage(WebDriver driver) 
	{
		this.driver = driver;
	}
	
	public String returnFlightCodeforLowestHighest() 
	{
		return List_FlightCode.get(0).getText();
	}
	
	
	
	public void clickOnPriceSorting(){
		Sort_price.click();
		
	}
	
}
