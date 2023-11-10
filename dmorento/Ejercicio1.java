package com.selenium.dmorento;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio1 {
	public static void main(String[] args) throws InterruptedException {
		
		//Ejercicio 1. Denis Moreno Torres
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\dmorento\\Desktop\\DRIVERS\\bin\\chromedriver.exe");
		String url = "https://www.estadiodeportivo.com";
		WebDriver driver = new ChromeDriver();
		navigateTo(driver, url);
	}
	public static void navigateTo(WebDriver driver, String url) throws InterruptedException {
		driver.manage().window().maximize();
		driver.get(url);
		Thread.sleep(3000);
	}
}
