package com.walmart.shoppingcarttest.testbeans;

import java.util.List;

/**
 * This class holds the no. of items 
 * and the names of items in the shopping cart.
 * @author SHRUTI
 *
 */
public class CartContent {

	/**
	 * No of items in the cart
	 */
	private Integer noOfItems;
	/**
	 * Names of the items in cart
	 */
	private List<String> listOfItems;
	
	public CartContent() {}

	/**
	 * 
	 * @return No of items in the cart
	 */
	public Integer getNoOfItems() {
		return noOfItems;
	}

	/**
	 * Sets no of items in the cart
	 * @param noOfItems
	 */
	public void setNoOfItems(Integer noOfItems) {
		this.noOfItems = noOfItems;
	}

	/**
	 * 
	 * @return Names of the items in cart
	 */
	public List<String> getListOfItems() {
		return listOfItems;
	}

	/**
	 * Sets names of the items in cart
	 * @param listOfItems
	 */
	public void setListOfItems(List<String> listOfItems) {
		this.listOfItems = listOfItems;
	}
	
}
