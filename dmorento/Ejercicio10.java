package com.selenium.dmorento;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio10 {
	public static void main(String[] args) throws InterruptedException{
		
		//Ejercicio 4. Denis Moreno Torres
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
				
				driver.findElement(By.id("firstName")).sendKeys(name);
				driver.findElement(By.id("lastName")).sendKeys(lastName);
				driver.findElement(By.id("userEmail")).sendKeys(mail);
				driver.findElement(By.id("userEmail")).sendKeys(Keys.TAB.toString());
				
				driver.findElement(By.id("gender-radio-1")).sendKeys(Keys.SPACE.toString());
				driver.findElement(By.id("userNumber")).sendKeys(phone);
				driver.findElement(By.id("userNumber")).sendKeys(Keys.TAB.toString());
				
				//Write date of birth
				WebElement calendarElement = driver.findElement(By.id("dateOfBirthInput")); 
				calendarElement.sendKeys(dob);
				calendarElement.sendKeys(Keys.ENTER.toString());		

				driver.findElement(By.id("subjectsInput")).sendKeys(subjects);		
				driver.findElement(By.id("subjectsInput")).sendKeys(Keys.TAB.toString());
				driver.findElement(By.id("hobbies-checkbox-1")).sendKeys(Keys.SPACE.toString());
				
				driver.findElement(By.id("currentAddress")).sendKeys(address);
				driver.findElement(By.id("currentAddress")).sendKeys(Keys.TAB.toString());
				
				driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.SPACE.toString());
				driver.findElement(By.id("react-select-3-input")).sendKeys(Keys.TAB.toString());
				
				driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.TAB.toString());
				driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.SPACE.toString());
				driver.findElement(By.id("react-select-4-input")).sendKeys(Keys.TAB.toString());	
				
				driver.findElement(By.id("submit")).submit();
				
				//Ejercicio 10 ##############################################################################################################
				Thread.sleep(2000);
				//Verify with the modal title that it is being displayed and then is printed
				String modalTitle = driver.findElement(By.id("example-modal-sizes-title-lg")).getText();
				assertEquals("Error. The modal is not shown or the title is not the expected", modalTitle, "Thanks for submitting the form");
				System.out.println(modalTitle);
				
				String expectedStudentName = name + " " + lastName;
				List<WebElement> modalElements = driver.findElements(By.cssSelector("tr > td:nth-of-type(2)"));
				int count = 1;
				for(WebElement element : modalElements) {
					assertTrue("The element " + element.getText() + " is not displayed as expected. ", element.isDisplayed());
					switch(count) {
						case 1: assertEquals("The name is not the expected. ", element.getText(), expectedStudentName);
								System.out.println(" Student Name - " + element.getText());
								break;
						case 2: assertEquals("The email is not the expected. ", element.getText(), mail);
								System.out.println(" Email - " + element.getText());
								break;
						case 3: assertEquals("The gender is not the expected. ", element.getText(), "Male");
								System.out.println(" Gender - " + element.getText());
								break;
						case 4: assertEquals("The mobile is not the expected. ", element.getText(), phone);
								System.out.println(" Mobile - " + element.getText());
								break;
						case 5: assertEquals("The date of birth is not the expected. ", element.getText(), "11 March,1989");
								System.out.println(" Date of Birth - " + element.getText());
								break;
						case 6: assertEquals("The subjects is not the expected. ", element.getText(), subjects);
								System.out.println(" Subjects - " + element.getText());
								break;
						case 7: assertEquals("The hobbies is not the expected. ", element.getText(), "Sports");
								System.out.println(" Hobbies - " + element.getText());
								break;
						case 9:  assertEquals("The address is not the expected. ", element.getText(), address);
								System.out.println(" Address - " +  element.getText());
								break;
						case 10:  assertEquals("The value for State and City is not the expected. ", element.getText(), "NCR Delhi");
								System.out.println(" State and City - " + element.getText());
								break;
					}
					count++;
				}
				System.out.println("All the values in the modal are the expected");
			}
		}
