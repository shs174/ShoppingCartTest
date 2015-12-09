package com.walmart.shoppingcarttest.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.walmart.shoppingcarttest.testcases.WalmartShoppingCartTest;

/**
 * This class holds the Main method - 
 * the entry point of this application
 * @author SHRUTI
 *
 */
public class WalmartShoppingCartTestRunner {

	public static void main(String[] args) {
		//run the testcase
		Result result = JUnitCore.runClasses(WalmartShoppingCartTest.class);
		//display if successful or unsuccessful
		if(result.wasSuccessful()) {
			System.out.println("Test was Successful!");
		} else {
			System.out.println("Test was Unsuccessful!");
		}
		//display the list of failures if any
		for(Failure failure: result.getFailures()) {
			System.out.println(failure.getTestHeader()+" -> "+failure.getMessage());
		}
	}

}
