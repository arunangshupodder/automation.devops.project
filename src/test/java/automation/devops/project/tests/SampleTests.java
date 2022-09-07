package automation.devops.project.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import automation.devops.project.base.Base;

public class SampleTests extends Base {
	
	@Test
	public void buyPetFish() {
		WebDriver driver = getDriver();
		
		driver.findElement(By.xpath("//a[text()='Enter the Store']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(d -> d.getTitle().contains("JPetStore Demo"));
		
		driver.findElement(By.xpath("//a[contains(@href, 'categoryId=FISH')]")).click();
		driver.findElement(By.xpath("//a[contains(@href, 'productId=FI-SW-01')]")).click();
		driver.findElement(By.xpath(("//a[text()='Add to Cart']"))).click();
		
		Assert.assertTrue(wait.until(d -> d.findElement(By.xpath("//a[text()='Proceed to Checkout']")).isDisplayed()), 
				"Proceed to checkout button is not displayed.");
	}
	
	@Test
	public void buyPetDog() {
		WebDriver driver = getDriver();
		
		driver.findElement(By.xpath("//a[text()='Enter the Store']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(d -> d.getTitle().contains("JPetStore Demo"));
		
		driver.findElement(By.xpath("//a[contains(@href, 'categoryId=DOGS')]")).click();
		driver.findElement(By.xpath("//a[contains(@href, 'productId=K9-BD-01')]")).click();
		driver.findElement(By.xpath(("//a[text()='Add to Cart']"))).click();
		
		Assert.assertTrue(wait.until(d -> d.findElement(By.xpath("//a[text()='Proceed to Checkout']")).isDisplayed()), 
				"Proceed to checkout button is not displayed.");
	}

}
