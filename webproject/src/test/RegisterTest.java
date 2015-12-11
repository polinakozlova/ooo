package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author Polina Kozlova
 */
public class RegisterTest {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		driver=new FirefoxDriver();
		driver.get("http://localhost:8080/webproject/Controller?action=signUp");
	}
	
	@After
	public void clean() {
		driver.quit();
	}
	
	private String generateRandomEmail(String component) {
		// generate random email adres in order to run test more than once
		int random = (int)(Math. random() * 1000 + 1);
		return random+component;
	}
		
	private void fillOutField(String name,String value) {
		WebElement field=driver.findElement(By.id(name));
		field.clear();
		field.sendKeys(value);
	}
	
	private void submitForm(String firstName,String lastName, String email, String password) {
		fillOutField("firstName", firstName);
		fillOutField("lastName",lastName);
		fillOutField("email", email);
		fillOutField("password", password);
		
		WebElement button=driver.findElement(By.id("signUp"));
		button.click();		
	}
	

	@Test
	public void testRegisterCorrect() {
		String randomEmail=generateRandomEmail("jan.janssens@hotmail.com");
		submitForm("Jan", "Janssens", randomEmail , "1234");
		
		String title=driver.getTitle();
		assertEquals("Home",title);
		
		driver.get("http://localhost:8080/webproject/Controller?action=overview");
		
		ArrayList<WebElement> listItems=(ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
		boolean found=false;
		for (WebElement listItem:listItems) {
			if (listItem.getText().equals(randomEmail+" Jan Janssens")) {
				found=true;
			}
		}
		assertEquals(true, found);
		
	}
	
	@Test
	public void testRegisterFirstNameEmpty(){
		submitForm("", "Janssens", "jan.janssens@hotmail.com", "1234");
		
		String title=driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No firstname given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
		

	}

	@Test
	public void testRegisterLastNameEmpty(){
		submitForm("Jan", "", "jan.janssens@hotmail.com", "1234");
		
		String title=driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No last name given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
		

	}

	@Test
	public void testRegisterEmailEmpty(){
		submitForm("Jan", "Janssens", "", "1234");
		
		String title=driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No id given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("",fieldEmail.getAttribute("value"));
		

	}


	@Test
	public void testRegisterPasswordEmpty(){
		submitForm("Jan", "Janssens", "jan.janssens@hotmail.com", "");
		
		String title=driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No password given", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
		

	}
	
	@Test
	public void testRegisterUserAlreadyExists(){
		String emailRandom = generateRandomEmail("pieter.pieters@hotmail.com");
		submitForm("Pieter", "Pieters", emailRandom, "1234");
		
		driver.get("http://localhost:8080/webproject/Controller?action=signUp");

		submitForm("Pieter", "Pieters", emailRandom, "1234");
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("User already exists", errorMsg.getText());

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Pieter",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Pieters",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals(emailRandom,fieldEmail.getAttribute("value"));
		
		

	}
	

}
