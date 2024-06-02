package test;

import java.io.FileReader;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;


public class LoginTest {

	WebDriver driver;
	JsonParser parserObj;
	JsonElement jsonEleObj;
	

	@SuppressWarnings("deprecation")
	@BeforeMethod(alwaysRun = true)
	public void readJson() {
		
		try {
			FileReader reader = new FileReader("testData\\TF_TestData.json");
			parserObj = new JsonParser();
			jsonEleObj = parserObj.parse(reader);
//			jsonEleObj.isJsonObject();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test(groups={"smoke", "regression"})
	public void validUserShouldBeAbleToLogin() {

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		Assert.assertEquals(dashboardPage.getDashboardValidaitonText(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationTextLogin").getAsString(),
				"Dashboard page is not available!");
		BrowserFactory.tearDown();
	}

	@Test(groups={"regression"})
	public void validateAlertPopup() {

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.clickSigninButton();
		Assert.assertEquals(loginPage.getUserAlertMsg(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationUserAlertText").getAsString(), "User Alert msg doesn't match!!");
		
		loginPage.insertUserName(jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.clickSigninButton();
		Assert.assertEquals(loginPage.getPasswordAlertMsg(), jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("ValidationPasswordAlertText").getAsString(), "Password Alert msg doesn't match!!");
		BrowserFactory.tearDown();
		
	}

}
