package com.selenium.dmorento;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CuraHealthcareServiceWebsite{   

	public static void main(String[] args) throws InterruptedException {	

		final String facility = "Hongkong";
		final String comment = "Please confirm the appointment on my email: dmorento@nttdata.com";
		String healthcareProgram = "Medicaid";
		final String url = "https://katalon-demo-cura.herokuapp.com/";
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmorento\\Desktop\\DRIVERS\\bin\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		navigateTo(driver, url);
		loginScreen(driver);
		formularyScreen(driver, facility, healthcareProgram, comment);
		verifyVisitDataCorrectness(driver, facility, healthcareProgram, comment);
		logout(driver);
	}

	@Test
	public static void verifyVisitDataCorrectness(WebDriver driver, String facility, String healthcareProgram, String comment) {
		final String ID_FACILITY = "facility";
		final String ID_PROGRAM = "program";
		final String ID_COMMENT = "comment";
		String fullFacility = facility.concat(" CURA Healthcare Center");
		
		if(fullFacility.equals(driver.findElement(By.id(ID_FACILITY)).getText())) {
			System.out.println("The facility \"" + fullFacility + "\" is correct");
		}
		if(healthcareProgram.equals(driver.findElement(By.id(ID_PROGRAM)).getText())) {
			System.out.println("The healthcare program \"" + healthcareProgram + "\" is correct");
		}
		if(comment.equals(driver.findElement(By.id(ID_COMMENT)).getText())) {
			System.out.println("The comment \"" + comment + "\" is correct");
		}
	}
	
	public static void logout(WebDriver driver) {
		final String ID_MENU_TOGGLE = "menu-toggle";
		final String CSSSELECTOR_LOGOUT = "a[href='authenticate.php?logout']";
		final String CSSSELECTOR_LOGIN = "a[href='profile.php#login']";
		
		driver.findElement(By.id(ID_MENU_TOGGLE)).click();
		driver.findElement(By.cssSelector(CSSSELECTOR_LOGOUT)).click();
		driver.findElement(By.id(ID_MENU_TOGGLE)).click();
		if(driver.findElement(By.cssSelector(CSSSELECTOR_LOGIN)).getAttribute("href").contains("login")) {
			System.out.println("You have successfully logged out");
		}
		driver.quit();
	}
	
	public static void navigateTo(WebDriver driver, String url) {
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public static void loginScreen(WebDriver driver) {
		final String ID_BTN_LOGIN = "btn-login";
		final String ID_TEXT_USERNAME = "txt-username";
		final String ID_TEXT_PASSWORD	 = "txt-password";
		final String ID_MAKE_APPOINTMENT = "btn-make-appointment";
		final String XPATH_DEMO_USENAME = "//input[@value='John Doe']";
		final String XPATH_DEMO_PASSSWORD = "//input[@value='ThisIsNotAPassword']";
		
		driver.findElement(By.id(ID_MAKE_APPOINTMENT)).click();	
		String username = driver.findElement(By.xpath(XPATH_DEMO_USENAME)).getAttribute("value");
		String password = driver.findElement(By.xpath(XPATH_DEMO_PASSSWORD)).getAttribute("value");
		driver.findElement(By.id(ID_TEXT_USERNAME)).sendKeys(username);
		driver.findElement(By.id(ID_TEXT_PASSWORD)).sendKeys(password);
		driver.findElement(By.id(ID_BTN_LOGIN)).click();
	}
	
	public static void formularyScreen(WebDriver driver, String facility, String healthcareProgram, String comment) {
		final String ID_CHECK_HOSPITAL_READMISSION	 = "chk_hospotal_readmission";
		final String ID_RADIO_HEALTHCARE_PROGRAM	 = "radio_program_";
		final String ID_TXT_VISIT_DATE = "txt_visit_date";
		final String ID_TXT_COMMENT = "txt_comment";
		final String ID_BTN_BOOK_APPOINTMENT = "btn-book-appointment";
		final String CSSSELECTOR_FACILITY = "select[id='combo_facility']>option[value*='";
		final String XPATH_DAY_CALENDAR_SELECTION = "//*[@class='day' and contains(text(),'";
		
		driver.findElement(By.cssSelector(CSSSELECTOR_FACILITY + facility + "']")).click();
		driver.findElement(By.id(ID_CHECK_HOSPITAL_READMISSION)).click();
		driver.findElement(By.cssSelector(CSSSELECTOR_FACILITY + facility + "']")).click();
		driver.findElement(By.id(ID_RADIO_HEALTHCARE_PROGRAM + healthcareProgram.toLowerCase())).click();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		Date date = new Date();
		
		driver.findElement(By.id(ID_TXT_VISIT_DATE)).click();
		driver.findElement(By.xpath(XPATH_DAY_CALENDAR_SELECTION + formatter.format(date) + "')]")).click();
		driver.findElement(By.id(ID_TXT_COMMENT)).click();
		driver.findElement(By.id(ID_TXT_COMMENT)).sendKeys(comment);
		driver.findElement(By.id(ID_BTN_BOOK_APPOINTMENT)).click();
	}
}