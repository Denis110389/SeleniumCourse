package com.selenium.dmorento;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio6 {
	public static void main(String[] args) throws InterruptedException{
		
		//Ejercicio 6. Denis Moreno Torres
		final String name = "Denis";
		final String lastName = "Moreno";
		final String mail = "dmorento@gmail.com";
		final String phone = "3467766633";
		final String dob = "11 March,1989";
		final String subjects = "Computer Science";
		final String address = "Evergreen Terrace 123, Springfield, US";
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoqa.com/automation-practice-form");
		
		driver.findElement(By.xpath("//input[@placeholder='First Name']")).sendKeys(name); 
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@placeholder='name@example.com']")).sendKeys(mail);
		driver.findElement(By.xpath("//label[@for='gender-radio-1']")).click();
		driver.findElement(By.xpath("//input[@placeholder='Mobile Number']")).sendKeys(phone);
		driver.findElement(By.xpath("//input[@placeholder='Mobile Number']")).sendKeys(Keys.TAB.toString());

		// Write date of birth
		WebElement calendarElement = driver.findElement(By.xpath("//div[@class='react-datepicker__input-container']/input[@id='dateOfBirthInput']"));
		calendarElement.sendKeys(dob);
		calendarElement.sendKeys(Keys.ENTER.toString());	
		
		driver.findElement(By.xpath("//input[@id='subjectsInput']")).sendKeys(subjects);		
		driver.findElement(By.xpath("//input[@id='subjectsInput']")).sendKeys(Keys.TAB.toString());
		
		driver.findElement(By.xpath("//input[@value='1']")).sendKeys(Keys.SPACE.toString());
		driver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys(address); 
		driver.findElement(By.xpath("//textarea[@class='form-control']")).sendKeys(Keys.TAB.toString());
		
		driver.findElement(By.xpath("//div[@class=' css-2b097c-container']//input[@id='react-select-3-input']")).sendKeys("NCR");
		driver.findElement(By.xpath("//div[@class=' css-2b097c-container']//input[@id='react-select-3-input']")).sendKeys(Keys.TAB.toString());
		
		driver.findElement(By.xpath("//input[@id='react-select-4-input']")).sendKeys(Keys.TAB.toString());
		driver.findElement(By.xpath("//input[@id='react-select-4-input']")).sendKeys("Delhi");
		driver.findElement(By.xpath("//input[@id='react-select-4-input']")).sendKeys(Keys.TAB.toString());	
		
		driver.findElement(By.xpath("//button[@type='submit']")).submit();
	}
}