package com.selenium.dmorento;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

	public class Ejercicio8 {
		public static void main(String[] args) throws InterruptedException{
			
			//Ejercicio 8. Denis Moreno Torres
			WebDriver driver=new ChromeDriver();                    
            driver.get("https://demoqa.com/nestedframes");
            driver.manage().window().maximize();
            
            //Localiza el Parent frame
            WebElement parentFrame = driver.findElement(By.id("frame1"));
            driver.switchTo().frame(parentFrame);
            WebElement webElementParentFrame= driver.findElement(By.tagName("body"));
            
            //Obtiene el texto del Parent Frame
            String parentFrameText=webElementParentFrame.getText();
            System.out.println("The Parent Frame text is : " + parentFrameText);           
            
            //Cambia el foco al Child Frame
            driver.switchTo().frame(0);
            
            WebElement webElementChildFrame= driver.findElement(By.tagName("body"));
          //Obtiene el texto del Child Frame
            String childFrameText=webElementChildFrame.getText();
            System.out.println("The Child Frame text is : " + childFrameText); 
            
            driver.quit();
    }
}