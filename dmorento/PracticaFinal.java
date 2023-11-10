package com.selenium.dmorento;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PracticaFinal {
	public static void main(String[] args) throws InterruptedException {
		final String url = "https://www.google.com/";
		final String product = "powerbank";
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmorento\\Desktop\\DRIVERS\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		try {
			navigateTo(driver, url);
			acceptGoogleCookies(driver);		
			googleSearch(driver, product);
			accessToAmazonProductResult(driver);
			acceptAmazonCookies(driver);
			
			//If several products of the same kind are shown in the Amazon page, selects the first one
			if(driver.findElement(By.cssSelector("[class*='a-size-mini']")).isDisplayed()) {
				driver.findElement(By.cssSelector("[class*='a-size-mini']")).click();
			}
			//Verify that 'Comprar ya' element is in place, what means we are in the expected Amazon product screen
			assertFalse("Error. You are not in the expected product page.", driver.findElements(By.id("buy-now-button")).isEmpty());
			System.out.println("You accessed successfully to the Amazon product page\n");
			
			printProductTitlePriceAndArrivalDate(driver);
			amazonProductSearchBox(driver, product);
			amazonFreeShippingCheckbox(driver);
			amazonFilterResultsByLowerPrice(driver);				
			printSearchResultsProductNamesAndPrices(driver, product);
		}finally {
			driver.quit();
		}
}

	public static void navigateTo(WebDriver driver, String url) {
		//Maximize the browser window and get the Google url
		driver.manage().window().maximize();
		System.out.println("Navigate to: " + url);
		driver.get(url);
		String currentURL = driver.getCurrentUrl();
		assertEquals("The url is not the expected. ", currentURL, url);
	}
	
	public static void acceptGoogleCookies(WebDriver driver) {
		//Accept Google cookies
		driver.findElement(By.id("L2AGLb")).click();
	}
	
	public static void googleSearch(WebDriver driver, String product) throws InterruptedException {

		//Write in the Google searchbox and click Search
		System.out.println("Searching results in Google search box for: " + product + "\n");
		driver.findElement(By.id("APjFqb")).sendKeys(product);	
		driver.findElement(By.xpath("//div[@class='FPdoLc lJ9FBc']//input[@name='btnK']")).click();
		Thread.sleep(3000);
	}
	
	public static void accessToAmazonProductResult(WebDriver driver) throws InterruptedException {
		try {
			//Select the amazon option for this product
			String amazonResultLocator = ".g a[href*='amazon']";
			driver.findElement(By.cssSelector(amazonResultLocator)).click();
		}catch(NoSuchElementException e){
		  System.out.println(Constants.SEPARATOR + "There are no Amazon results for your search in the first page." + Constants.SEPARATOR + e.getMessage());
		  driver.quit();
		}
		assertTrue("The url accessed is not the expected", driver.getCurrentUrl().contains("amazon.es/"));
		System.out.println("You accessed successfully to the expected Amazon result for your search");
		Thread.sleep(5000);
	}
	
	public static void acceptAmazonCookies(WebDriver driver) {
		//Wait for the accept cookies banner to appear and click on Accept		
		driver.findElement(By.id("sp-cc-accept")).click();
	}
	
	public static void printProductTitlePriceAndArrivalDate(WebDriver driver) {
		
		//Get the product title, price and expected arrival date and print it 
		String productTitle = driver.findElement(By.id("productTitle")).getText();
		System.out.println("The product title is: " + productTitle);
		String price = driver.findElement(By.cssSelector("[class='a-offscreen']")).getAttribute("innerHTML");
		price = price.replace("&nbsp;", "");
		System.out.println("The product price is: " + price);
		String estimatedArrival = driver.findElement(By.xpath("//span[@data-csa-c-type='element']")).getAttribute("data-csa-c-delivery-time");
		System.out.println("The estimated arrival of the product is: " + estimatedArrival);
	}
	
	public static void amazonProductSearchBox(WebDriver driver, String product) throws InterruptedException {

		//Clear and write in the Amazon searchbox and click Search
		System.out.print("\nSearching results in Amazon search box for: " + product + "\n");		
		driver.findElement(By.id("twotabsearchtextbox")).clear();
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(product);
		driver.findElement(By.id("nav-search-submit-button")).click();		
		verifyAmazonFindResults(driver);
		
		/*If 'Cualquier departamento' is available to click it means we are not seeing the results in the matrix format but in columns
		 * to homogenize the process, we click in 'Cualquier departamento', this way we always see the results as a matrix, not as a single column
		 */
		if(!(driver.findElements(By.xpath("//span[contains(text(),'Cualquier departamento')]")).isEmpty())) {
			driver.findElement(By.xpath("//span[contains(text(),'Cualquier departamento')]")).click();
			Thread.sleep(2000);
		}		
	}
	
	public static void verifyAmazonFindResults(WebDriver driver) {

		//Verify results are found
		String numberOfResultsLocator = "[class='a-size-base s-desktop-toolbar a-text-normal'] div[class='a-section a-spacing-small a-spacing-top-small']";
		String resultsForSearch = driver.findElement(By.cssSelector(numberOfResultsLocator)).getText();
		assertTrue("The Amazon search did not found any results. ", resultsForSearch.contains("1-") && resultsForSearch.contains("resultados para"));
		System.out.println("The search found: " + resultsForSearch);
	}
	public static void amazonFreeShippingCheckbox(WebDriver driver) throws InterruptedException {
		
		//Select free shiping checkbox
		System.out.println("Filtering results for Free Shipping availability");
		driver.findElement(By.cssSelector("li[id='p_n_free_shipping_eligible/20930980031'] span.a-list-item div[class*='a-checkbox']")).click();
		Thread.sleep(2000);
		verifyAmazonFindResults(driver);
	}
	
	public static void amazonFilterResultsByLowerPrice( WebDriver driver) {
		
		//Select in the dropdown element the option to order the result from lower to higher price
		System.out.println("Filtering results from lower to higher prices");
		driver.findElement(By.cssSelector("[class='a-dropdown-container']")).click();
		driver.findElement(By.id("s-result-sort-select_1")).click();
		verifyAmazonFindResults(driver);
	}
	
	public static void printSearchResultsProductNamesAndPrices(WebDriver driver, String product) throws InterruptedException {
		
		//Wait for all the results to be displayed
		Thread.sleep(3000);
		
		//Build a list of the total of products shown in the first page (normally in the first page is always 48) (including second hand products)
		List<WebElement> productNames = driver.findElements(By.cssSelector("[cel_widget_id*='MAIN-SEARCH_RESULTS-']"));				
		
		//Count how many products in the first page have a price assigned (brand new products)
		int productsWithAPrice = 0;		
		for (int i = 1; i < 100; i++) {	
			if(!(driver.findElements(By.cssSelector("[cel_widget_id='MAIN-SEARCH_RESULTS-" + i + "'] [data-a-size='xl'] [class='a-offscreen']")).isEmpty())) {
				productsWithAPrice++;
				}
			}
		
		System.out.println("\nThe number of products shown in the first page is " + productNames.size() +", ("+ productsWithAPrice + " of these products have a price assigned) "
				+ "\nThe following list only shows the " + productsWithAPrice + " products available with a price assigned:");
		if(productsWithAPrice>0) {
			/*
			* Count is the total number of products shown, knowing this, if we find an empty locator for any of the elements, the count will be increased, 
			* so we can assure that all the products will be checked and if they have a new product price they will be printed
			*/
			int count = productNames.size();
			for (int i = 1; i <= count; i++) {
				
				//I just want to print new products with a price assigned, not second hand products
				if(!(driver.findElements(By.cssSelector("[cel_widget_id='MAIN-SEARCH_RESULTS-" + i + "'] [data-a-size='xl'] [class='a-offscreen']")).isEmpty())) {
					System.out.println(Constants.SEPARATOR);
					String name = driver.findElement(By.cssSelector("[cel_widget_id='MAIN-SEARCH_RESULTS-" + i + "'] [class='a-size-base-plus a-color-base a-text-normal']")).getText();
					System.out.println(" - " + name);
					String price = driver.findElement(By.cssSelector("[cel_widget_id='MAIN-SEARCH_RESULTS-" + i + "'] [data-a-size='xl'] [class='a-offscreen']")).getAttribute("innerHTML").replace("&nbsp;", "");
					System.out.println( " - " + price);								
				}else {
					//In case we find an empty element, this means we try the next one, so the count is increased, this way the total number of products will be verified
					count++;
				}
			}
		}else {
			//if there are no Amazon results for this product search
			System.out.println("There are no results for: " + product);			
		}
		
	}
}