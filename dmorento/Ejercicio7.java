package com.selenium.dmorento;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio7 {
	public static void main(String[] args) throws InterruptedException{
		
		//Ejercicio 7. Denis Moreno Torres
		String url = "https://demoqa.com/alerts";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
		driver.findElement(By.id("alertButton")).click();
		switchToAlertAndAcept(driver);
		
		driver.findElement(By.id("timerAlertButton")).click();
		wait(6000);
		
		switchToAlertAndAcept(driver);
		driver.findElement(By.id("confirmButton")).click();
		switchToAlertAndAcept(driver);
		
		String confirmationMessage = driver.findElement(By.id("confirmResult")).getText();
		assertEquals("The confirmation message is not the expected.", "You selected Ok", confirmationMessage);
		System.out.println("The confirmation message is the expected: \"" + confirmationMessage + "\" ");
		
		String myName = "Denis Moreno";
		driver.findElement(By.id("promtButton")).click();
		driver.switchTo().alert();
		driver.switchTo().alert().sendKeys(myName);
		driver.switchTo().alert().accept();
		String nameConfirmationMessage = driver.findElement(By.id("promptResult")).getText();
		String expectedNameConfirmationMessage = "You entered ".concat(myName);
		assertEquals("The confirmation message is not the expected.",expectedNameConfirmationMessage, nameConfirmationMessage);
		System.out.println("The confirmation message is the expected:  \"" + nameConfirmationMessage + "\" ");
		
		driver.quit();
	}
	
	public static void switchToAlertAndAcept(WebDriver driver) throws InterruptedException{
		driver.switchTo().alert();
		driver.switchTo().alert().accept();
	}
	
	public static void wait(int miliseconds) throws InterruptedException  {
		System.out.println("Waiting " + miliseconds / 1000 + " seconds.");
		Thread.sleep(miliseconds);
	}
}