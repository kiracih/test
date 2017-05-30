package firstTestNG;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class NewTest {

	public String baseUrl = "http://demosite.center/wordpress/wp-login.php";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	@BeforeTest
	public void setup() {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver(capabilities);
		driver.get(baseUrl);
		// driver.manage().window().maximize(); not applicable while in incognito mode
	}

	@Test(priority = 0)
	public void verifyHomepageTitle() {

		System.out.println("Chrome açılıyor");

		String expectedTitle = "WordPress Demo Install › Log In";
		String actualTitle = driver.getTitle();

		Assert.assertEquals(actualTitle, expectedTitle);

	}

	@Test(priority = 1)
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

	@Test(priority = 2)
	public void logout() {

		WebElement adminPanel = driver.findElement(By
				.xpath("//*[@id='wp-admin-bar-my-account']"));

		Actions build = new Actions(driver);
		build.moveToElement(adminPanel).build().perform();

		WebDriverWait wait = new WebDriverWait(driver, 1);
		WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wp-admin-bar-logout']/a")));
		logout.click();
		
		String expectedTitle = "WordPress Demo Install › Log In" ;
		String actualTitle = driver.getTitle();
		
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@AfterTest
	public void close() {

		driver.close();
	}

}
