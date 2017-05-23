package firstTestNG;

import org.testng.annotations.Test;
import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class NewTest {

	public String baseUrl = "http://newtours.demoaut.com/";
	String driverPath = "C:\\chromedriver.exe";
	public WebDriver driver;

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar,
				JOptionPane.INFORMATION_MESSAGE);
	}

	@Test
	public void verifyHomepageTitle() {

		JFrame frame = new JFrame();
		String[] options = new String[2];
		options[0] = new String("Başlat");
		options[1] = new String("Vazgeç");
		Container frameContent = frame.getContentPane();
		frameContent.setBackground(Color.RED);

		int testStartResult = JOptionPane.showOptionDialog(frameContent,
				"verifyHomepageTitle Testini Başlatmak İstiyor Musunuz?",
				"verifyHomepageTitle Testi", 0,
				JOptionPane.INFORMATION_MESSAGE, null, options, null);

		if (testStartResult == 0) {
			System.out.println("Chrome açılıyor");
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.get(baseUrl);

			String expectedTitle = "Welcome: Mercury Tours";
			String actualTitle = driver.getTitle();

			if (!actualTitle.equals(expectedTitle)) {

				infoBox("verifyHomepageTitle Testi Başarısız\n\nGerçek: "
						+ actualTitle + "\nBeklenen: " + expectedTitle,
						"Başlık Hatalı");

				System.out.println("Gerçek: " + actualTitle);
				System.out.println("Beklenen: " + expectedTitle);

				driver.close();

			} else {

				infoBox("verifyHomepageTitle Testi Başarılı\n\nGerçek: "
						+ actualTitle + "\nBeklenen: " + expectedTitle,
						"Başlık Doğru");

				System.out.println("Test Başarılı");
				driver.close();
			}

			Assert.assertEquals(actualTitle, expectedTitle);

		} else {

			driver.close();

		}

	}
}
