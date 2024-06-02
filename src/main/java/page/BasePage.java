package page;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

	WebDriver driver;
	
	public String getMsgFromAlert(WebDriver driver) {
		String alertText = driver.switchTo().alert().getText();
		driver.switchTo().alert().accept();
		return alertText;
	}
	
	public void selectFromDropdown(WebElement element, String visibleText) {
		Select sel = new Select(element);
		sel.selectByVisibleText(visibleText);
	}
	
	public int generateRandomNum(int boundNum) {
		Random rnd = new Random();
		int generatedNum = rnd.nextInt(boundNum);
		return generatedNum;
	}
}
