package com.selenium.pom.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

/**
 * @author sagar.malik
 **/
public class BookFlightPage 
{
	WebDriver driver;

	@FindBy(how = How.NAME, using = "buyFlights")
	WebElement BTN_securePurchase;

	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[1]/b/font")
	WebElement DepartFromTo;

	@FindBy(how = How.XPATH, using = "//tbody/tr[3]/td[2]/font")
	WebElement ServiceClass;

	public BookFlightPage(WebDriver driver) 
	{
		this.driver = driver;
	}

	public void clickOnSecurePurchase() 
	{
		BTN_securePurchase.click();
	}
	
	public String getDepartFromTo() 
	{
		return DepartFromTo.getText();
	}
	
	public String getServiceClass() 
	{
		return ServiceClass.getText();
	}
	
}
