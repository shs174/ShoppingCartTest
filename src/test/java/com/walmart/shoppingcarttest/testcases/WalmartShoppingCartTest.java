package com.walmart.shoppingcarttest.testcases;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.walmart.shoppingcarttest.pageobjects.ItemPage;
import com.walmart.shoppingcarttest.pageobjects.LoginPage;
import com.walmart.shoppingcarttest.pageobjects.SearchPage;
import com.walmart.shoppingcarttest.testbeans.CartContent;

/**
 * This class holds the test cases
 * @author SHRUTI
 *
 */
public class WalmartShoppingCartTest {
	
	private WebDriver driver;

	//PageObjects
	private LoginPage loginPage;
	private SearchPage searchPage;
	private ItemPage itemPage;
	
	//constants for setup
	private static final String ACCOUNT_URL = "http://www.walmart.com/account";
	private static final Integer IMPLICIT_TIMEOUT = 10;
	private static final String DRIVER_NAME = "webdriver.chrome.driver";
	private static final String DRIVER_URL = "src/test/resources/chromedriver_win32/chromedriver.exe";
	
	//credentials
	private String username = "shs174@pitt.edu";
	private String password = "shopping";
	
	//custom test values
	private String[] keywordPool = new String[] {"tv","socks","dvd","toys","iPhone"};
	private String query = "dvd";
	private Integer index = 0;

	/**
	 * Setup before running test cases in this class
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		//Load the web driver and load the Walmart Account Login page
		System.setProperty(DRIVER_NAME, DRIVER_URL);
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
		driver.get(ACCOUNT_URL);
	}

	/**
	 * Test case to verify the cart contents
	 */
	@Test
	public void test() {
		//test successful Login
		loginPage = new LoginPage(driver);
		searchPage = loginPage.login(username, password);
		Assert.assertNotNull(searchPage);
		
		//test Search Operation
		//the following piece of code can be used to randomize 
		//the search based on the pool of keywords
		//int pos = ThreadLocalRandom.current().nextInt(0,keywordPool.length);
		//query = keywordPool[pos];
		itemPage = searchPage.performSearch(query, index);
		Assert.assertNotNull(itemPage);
		
		//test Default Quantity in DropDown
		String itemName = itemPage.getItemName();
		Integer quantityInDropDown = itemPage.getQuantityInDropDown();
		Assert.assertNotNull(quantityInDropDown);
		Assert.assertEquals(quantityInDropDown.intValue(), 1);
		
		//test AddToCart Operation
		itemPage = itemPage.addToCart();
		Assert.assertNotNull(itemPage);
		
		//test Cart Content
		CartContent cartContent = itemPage.getCartContent();
		Assert.assertEquals(cartContent.getNoOfItems().intValue(),1);
		for(String item: cartContent.getListOfItems()) {
			Assert.assertEquals(itemName, item);
		}
	}
}
