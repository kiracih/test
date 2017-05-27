package firstTestNG;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
/*import java.awt.Color;
 import java.awt.Container;
 import javax.swing.JFrame;
 import javax.swing.JOptionPane;*/

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NewTest {

	


	public String baseUrl = "http://demosite.center/wordpress/wp-login.php";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void setup() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.get(baseUrl);
	}

	@Test
	public void verifyHomepageTitle() {

		System.out.println("Chrome açılıyor");

		String expectedTitle = "WordPress Demo Install › Log In";
		String actualTitle = driver.getTitle();
		
	
		Assert.assertEquals(actualTitle, expectedTitle);

	}

	@Test
	public void login() {
		
		WebElement username = driver.findElement(By.id("user_login"));
		WebElement password = driver.findElement(By.id("user_pass"));
		WebElement button = driver.findElement(By.id("wp-submit"));
		
		username.clear();
		username.sendKeys("admin");

		password.clear();
		password.sendKeys("demo123");

		button.submit();

		String expectedTitle = "Dashboard ‹ WordPress Demo Install — WordPress";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);
		

	}
	
	@AfterTest
	public void close(){
		
		driver.close();
	}

}
