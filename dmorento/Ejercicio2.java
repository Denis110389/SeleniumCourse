package com.selenium.dmorento;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio2 {

	public static void main(String[] args) throws InterruptedException {
		
		//Ejercicio 1. Denis Moreno Torres
		WebDriver driver = new ChromeDriver();
		Ejercicio1.navigateTo(driver, "https://www.estadiodeportivo.com");
				
		//Ejercicio 2
		driver.findElement(By.id("acceptAllMain")).click();
		driver.findElement(By.cssSelector("[alt='Real Betis Balompié']")).click();
		
		System.out.println("Las principales noticias sobre el Betis son: ");
		List<WebElement> frontMainNews = driver.findElements(By.cssSelector("h2[class*='titular titulo-1']"));
		for(WebElement news:frontMainNews) {
			System.out.println(" - " + news.getText());
		}
	}
}

