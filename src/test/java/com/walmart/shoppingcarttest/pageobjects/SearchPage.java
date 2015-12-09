package com.walmart.shoppingcarttest.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This is PageObject for Search page
 * after login is successful
 * @author SHRUTI
 *
 */
public class SearchPage {

	private final WebDriver driver;
	
	@FindBy(name = "query")
	@CacheLookup
	private WebElement searchBox;
	
	@FindBy(xpath = "//a[@class='js-product-title']")
	@CacheLookup
	private List<WebElement> searchResults;
	
	public SearchPage(WebDriver driver) {
		this.driver = driver;
		//check if correct page is loaded
		if(!driver.getCurrentUrl().startsWith("https://www.walmart.com/account/")) {
			throw new IllegalArgumentException("This is not the search page.");
		}
		//call the PageFactory to initialise the WebElements
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method enters the query in the search box.
	 * @param query
	 * @return
	 */
	public SearchPage enterQuery(String query) {
		searchBox.sendKeys(query);
		return this;
	}
	
	/**
	 * This method runs the searchQuery and waits until the results are loaded
	 * @return
	 */
	public SearchPage submitQuery() {
		searchBox.submit();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfAllElements(searchResults));
		return this;
	}
	
	/**
	 * This method retrieves the Item PageObject of the 
	 * search result selected by its index
	 * @param index
	 * @return null if no search results found
	 */
	public ItemPage getSearchResultItemPage(int index) {
		if(searchResults.size() > 0) {
			String url = searchResults.get(index).getAttribute("href");
			WebElement firstSearchResult = searchResults.get(0);
			firstSearchResult.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.urlContains(url));
			return new ItemPage(driver);
		} else {
			return null;
		}
	}
	
	/**
	 * enters query, gets the search results and 
	 * retrieves Item PageObject for one of the search result
	 * @param query
	 * @param index
	 * @return
	 */
	public ItemPage performSearch(String query, int index) {
		enterQuery(query);
		submitQuery();
		return getSearchResultItemPage(index);
	}
}
