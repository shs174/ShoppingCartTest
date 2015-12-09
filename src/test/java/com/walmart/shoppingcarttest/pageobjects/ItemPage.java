package com.walmart.shoppingcarttest.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.walmart.shoppingcarttest.testbeans.CartContent;

/**
 * This is PageObject for Item
 * Usually when user clicks on one of the search results
 * @author SHRUTI
 *
 */
public class ItemPage {

	private final WebDriver driver;
	
	@FindBy(xpath = "//h1[@itemprop='name']//span")
	@CacheLookup
	private WebElement itemHeadingDiv;
	
	@FindBy(xpath = "//div[@data-name='product-quantity-dropdown']//div//div[@class='chooser-option-current js-chooser-option-current']")
	@CacheLookup
	private WebElement quantityDropDownDiv;
	
	@FindBy(id = "WMItemAddToCartBtn")
	@CacheLookup
	private WebElement addToCartButton;
	
	@FindBy(id = "PACSubTtlItemLnk")
	@CacheLookup
	private WebElement itemSummaryDiv;
	
	@FindBy(xpath = "//div[@class='cart-item-name js-product-title']//a//span")
	@CacheLookup
	private List<WebElement> itemElements;
	
	
	public ItemPage(WebDriver driver) {
		this.driver = driver;
		//check if correct page is loaded
		if(!driver.getCurrentUrl().contains("www.walmart.com/ip/")) {
			throw new IllegalArgumentException("This is not the item page.");
		}
		//call the PageFactory to initialise the WebElements
		PageFactory.initElements(driver, this);
	}
	
	/**
	 * This method retrieves the name of the item
	 * described in this page
	 * @return
	 */
	public String getItemName() {
		return itemHeadingDiv.getText();
	}
	
	/**
	 * This method retrieves the quantity set
	 * for this item
	 * @return
	 */
	public Integer getQuantityInDropDown() {
		String quantityValueString = quantityDropDownDiv.getText();
		Integer quantityValue = Integer.parseInt(quantityValueString);
		return quantityValue;
	}
	
	/**
	 * This method adds the item to the cart
	 * @return
	 */
	public ItemPage addToCart() {
		addToCartButton.click();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(itemSummaryDiv));
		return this;
	}
	
	/**
	 * This method retrieves the CartContent object 
	 * which describes the number of cart items and the
	 * names of those items
	 * @return
	 */
	public CartContent getCartContent() {
		CartContent cartContent = new CartContent();
		
		String itemSummaryText = itemSummaryDiv.getText();
		String quantityString = itemSummaryText.substring(1,itemSummaryText.indexOf(" "));
		cartContent.setNoOfItems(Integer.parseInt(quantityString));
		List<String> listOfItems = new ArrayList<String>();
		for(WebElement itemElement: itemElements) {
			listOfItems.add(itemElement.getText());
		}
		cartContent.setListOfItems(listOfItems);
		return cartContent;
	}

}
