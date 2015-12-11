package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AddProductPage {
	private WebDriver driver;
	private static final String URL = "http://localhost:8080/webproject";
	
	public AddProductPage(WebDriver driver){
		this.driver = driver;
		driver.get(URL + "/addProduct.jsp");
	}
	
	public void setID(String id){
		WebElement idField = driver.findElement(By.id("id"));
		idField.clear();
		idField.sendKeys(id);
	}
	
	public void setDescription(String description){
		WebElement descriptionField = driver.findElement(By.id("description"));
		descriptionField.clear();
		descriptionField.sendKeys(description);
	}
	
	public void setPrice(String price){
		WebElement priceField = driver.findElement(By.id("price"));
		priceField.clear();
		priceField.sendKeys(price);
	}
	
	public ProductOverviewPage confirm(){
		WebElement addButton = driver.findElement(By.id("addProduct"));
		addButton.click();
		return new ProductOverviewPage(driver);
	}
	
}
