package firstTestNG;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class saveDraft extends happyPath {

	happyPath happyPath = new happyPath();

@Test(priority=2)
	public void quickpress() throws Exception {
		
		WebElement draftTitle = driver.findElement(By.id("title"));
		WebElement draftContent = driver.findElement(By.id("content"));
		WebElement draftTags = driver.findElement(By.id("tags-input"));
		WebElement saveDraft = driver.findElement(By.id("save-post"));
		
		
		draftTitle.clear();
		draftTitle.sendKeys("Yeni Başlık");
		
		draftContent.clear();
		draftContent.sendKeys("Yeni İçerik - Test123");
		
		draftTags.clear();
		draftTags.sendKeys("new,test,selenium");
		
		saveDraft.click();
		
		Thread.sleep(5000);
		
		TakesScreenshot scrShot = ((TakesScreenshot)driver);
		
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile = new File("C://Users/Halil/Desktop/test.png");
		FileUtils.copyFile(SrcFile, DestFile);
		
				
	}

	@Override
	@AfterTest
	public void logout() {}

}
