# ShoppingCartTest  

###1. Requirements  
 * Java JRE 1.7+  
 * Maven  (optional) -> in order to install and run using Maven  
 Ensure environment variables and the path to these directories are set.

###2a. Run the Jar:
	* Download the repository.
	* In the command prompt, go to this repository `ShoppingCartTest` and then enter the following:
		```bash
		$ java -jar target/ShoppingCartTest-0.0.1-SNAPSHOT-tests.jar
		``` 

### OR

###2b. Install using Maven and Run:
	* Download the repository.
	* In the command prompt, go to this repository `ShoppingCartTest` and then enter the following:
		```bash
		$ mvn clean install  
		```
	It will run the tests during this.
	To run the tests again, you can now use the following command in `target` directory:
		```bash
		$ java -jar ShoppingCartTest-0.0.1-SNAPSHOT-tests.jar
		```  
	Based on the tests passed/failed, the output will be shown on the console.
	

###Summary  

I used Selenium WebDriver and JUnit to create this application.  

I also used PageObject and PageObjectFactory to address the feature of reusability and readability.  

PageObject design pattern ensures that the test application separates the operations from the actual test cases. Also, it facilitates for reusability of those operations (such as loginOperation or searchOperation) to create different test cases.  

PageObjectFactory creates the PageObject based on the element search criteria passed. For example, it binds the DOM element that has an id called username with the PageObject field/property called uname. This binding helps to perform operations on the elements easily.  

I used JUnit which is the simplest form of tool to perform unit tests in Java. The operations are executed sequentially in a single test case. A single unit test case is to act independently of the other test cases. This is the reason why I had to place all the tests on operations in a single test case. In order to facilitate reusability of testcases by separating tests on each PageObject operation, I believe TestNG would be helpful. If I had more time, I would explore it and make the test cases more granular and reusable too.  

One of the challenges I faced was to understand the nature of the DOM structure of different item pages. Each item was different in having required fields such as an additional radio button or a drop down. In such cases, clicking AddToCart without setting these required fields will result in a test case failure. Throughout my development, I used the keyword "dvd" to add the first search result item into the cart successfully. Occasionally, I faced the problem of additional required input elements halting the AddToCart operation. If I had more time, I wish to find a more logically correct solution that is independent of identifying such required fields.  
