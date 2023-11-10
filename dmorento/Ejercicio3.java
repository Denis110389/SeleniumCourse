package com.selenium.dmorento;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Ejercicio3 {

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
		
		//Ejercicio 3
		driver.findElement(By.cssSelector("h2[class*='titular titulo-1']")).click();
		System.out.println("\nLa primera y última noticia del carrusel son: ");
		
		//Guardo todos los elementos del carrusel superior en una lista
		List<WebElement> carruselElements = driver.findElements(By.cssSelector("[class*='texto-suave barra-seo-element']"));
		carruselElements.size();
		//Solo se sacarán por pantalla el primer y último elemento
		int count = 1;
		for(WebElement carrusel : carruselElements) {
			if(count == 1 || count == carruselElements.size()) {
			System.out.println(carrusel.getText());
			}
			count++;
		}
	}
}