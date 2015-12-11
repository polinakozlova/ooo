package test;

import org.openqa.selenium.WebDriver;

public class ProductOverviewPage {
	private WebDriver driver;
	private static final String URL = "http://localhost:8080/webproject";
	
	public ProductOverviewPage(WebDriver driver){
		this.driver = driver;
		driver.get(URL + "Controller?action=addProduct");
	}
	
}
