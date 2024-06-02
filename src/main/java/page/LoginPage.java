package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends BasePage{
	
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//*[@id=\"user_name\"]") WebElement USER_NAME_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"password\"]") WebElement PASSWORD_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"login_submit\"]") WebElement SIGNIN_BUTTON_ELEMENT;
	
	public void insertUserName(String userName) {
		USER_NAME_ELEMENT.sendKeys(userName);
	}
	
	public void insertPassword(String password) {
		PASSWORD_ELEMENT.sendKeys(password);
	}

	public void clickSigninButton() {
		SIGNIN_BUTTON_ELEMENT.click();
	}
	
	public String getUserAlertMsg() {
		String actualUserAlertMsg = getMsgFromAlert(driver);
		return actualUserAlertMsg;
	}
	
	public String getPasswordAlertMsg() {
		String actualPasswordAlertMsg = getMsgFromAlert(driver);
		return actualPasswordAlertMsg;
	}

	
	
	
	
}
