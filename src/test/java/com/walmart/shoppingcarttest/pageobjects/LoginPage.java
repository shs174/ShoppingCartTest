package com.walmart.shoppingcarttest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is PageObjet for LoginPage 
 * @author SHRUTI
 *
 */
public class LoginPage {

	private final WebDriver driver;
	
	@FindBy(name = "login-username")
	@CacheLookup
	private WebElement usernameElement;
	
	@FindBy(name = "login-password")
	@CacheLookup
	private WebElement passwordElement;
	
	@FindBy(xpath = "//button[@data-automation-id='login-sign-in']")
	@CacheLookup
	private WebElement loginButtonElement;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		//check if correct page is loaded
		if(!driver.getCurrentUrl().startsWith("https://www.walmart.com/account/login")) {
			throw new IllegalArgumentException("This is not the login page.");
		}
		//call the PageFactory to initialise the WebElements
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method enters username in the login page
	 * @param usernameValue
	 * @return
	 */
	public LoginPage enterUsername(String usernameValue) {
		usernameElement.sendKeys(usernameValue);
		return this;
	}
	
	/**
	 * This method enter password in the login page
	 * @param passwordValue
	 * @return
	 */
	public LoginPage enterPassword(String passwordValue) {
		passwordElement.sendKeys(passwordValue);
		return this;
	}
	
	/**
	 * This method returns Search PageObject 
	 * if login is successful
	 * @return
	 */
	public SearchPage submitLoginSuccessfully() {
		loginButtonElement.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.urlToBe("https://www.walmart.com/account/"));
		return new SearchPage(driver);
	}
	
	/**
	 * enters username/password and retrieves search PageObject
	 * @param usernameValue
	 * @param passwordValue
	 * @return
	 */
	public SearchPage login(String usernameValue, String passwordValue) {
		enterUsername(usernameValue);
		enterPassword(passwordValue);
		return submitLoginSuccessfully();
	}
}
