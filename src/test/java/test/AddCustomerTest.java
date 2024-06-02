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

import page.AddCustomerPage;
import page.DashboardPage;
import page.ListCustomersPage;
import page.LoginPage;
import util.BrowserFactory;

public class AddCustomerTest {

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

	@Test(groups={"regression"})
	public void userShouldBeAbleToCreateNewCustomer() throws InterruptedException {

		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(
				jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("UserName").getAsString());
		loginPage.insertPassword(
				jsonEleObj.getAsJsonObject().get("LoginInfo").getAsJsonObject().get("Password").getAsString());
		loginPage.clickSigninButton();

		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		Assert.assertEquals(dashboardPage.getDashboardValidaitonText(), jsonEleObj.getAsJsonObject().get("LoginInfo")
				.getAsJsonObject().get("ValidationTextLogin").getAsString(), "Dashboard page not available!!");
		dashboardPage.clickOnCustomer();
		dashboardPage.clickOnAddCustomer();

		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		Assert.assertEquals(
				addCustomerPage.getNewCustomerValidaitonText(), jsonEleObj.getAsJsonObject().get("AddContact")
						.getAsJsonObject().get("ValidationTextAddCustomer").getAsString(),
				"Add Customer page not found!!");

		addCustomerPage.insertFullName(
				jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("FullName").getAsString());
		addCustomerPage.selectCompany(
				jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Company").getAsString());
		addCustomerPage.insertEmail(
				jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Email").getAsString());
		addCustomerPage.insertPhone(
				jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Phone").getAsString());
		addCustomerPage.insertAddress(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address")
				.getAsJsonArray().get(0).getAsJsonObject().get("Street").getAsString());

		addCustomerPage.insertCity(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address")
				.getAsJsonArray().get(0).getAsJsonObject().get("City").getAsString());

		addCustomerPage.insertZip(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address")
				.getAsJsonArray().get(0).getAsJsonObject().get("Zip").getAsString());

		addCustomerPage.selectCountry(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Address")
				.getAsJsonArray().get(1).getAsJsonObject().get("Country").getAsString());

		addCustomerPage.selectGroup(jsonEleObj.getAsJsonObject().get("AddContact").getAsJsonObject().get("Group").getAsString());
		addCustomerPage.clickOnSubmitButton();

		ListCustomersPage listCustomerPage = PageFactory.initElements(driver, ListCustomersPage.class);
		listCustomerPage.validateAndDeleteInsertedName();

	}

}
