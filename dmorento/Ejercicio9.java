package com.selenium.dmorento;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio9 {
	public static void main(String[] args) throws InterruptedException{
		
		//Ejercicio 9. Denis Moreno Torres
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://uitestingplayground.com/shadowdom");
		
		//Element containing the shadow root
        WebElement shadowHost = driver.findElement(By.xpath("//guid-generator"));

        //Get the shadow root
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        SearchContext shadowRoot = (SearchContext) jsExecutor.executeScript("return arguments[0].shadowRoot", shadowHost);

        //Search for the element in shadow root
        WebElement generateButtonElementFromShadowRoot = shadowRoot.findElement(By.cssSelector("[class='button-generate']"));

        //Performs the click in the element to generate the text
        generateButtonElementFromShadowRoot.click();
        
        WebElement fieldTextElementFromShadowRoot = shadowRoot.findElement(By.cssSelector("[class='edit-field']"));
        String generatedText = fieldTextElementFromShadowRoot.getAttribute("value");
        System.out.println("The generated value in the text field is: " + generatedText);
	}
}
