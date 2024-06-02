package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage {
	
	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(how = How.XPATH, using = "//strong[text()='Dashboard']") WebElement DASHBOARD_HEADER_ELEMENT;
	@FindBy(how = How.XPATH, using = "/html/body/div[1]/aside[1]/div/nav/ul[2]/li[2]/a/span") WebElement CUSTOMER_MENU_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"customers\"]/li[2]/a/span") WebElement ADD_CUSTOMER_MENU_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"customers\"]/li[3]/a/span") WebElement LIST_CUSTOMER_MENU_ELEMENT;
	
	public String getDashboardValidaitonText() {
		String actualDashboardHeaderText = DASHBOARD_HEADER_ELEMENT.getText();
		return actualDashboardHeaderText;
	}
	
	public void clickOnCustomer() {
		CUSTOMER_MENU_ELEMENT.click();
	}
	
	public void clickOnAddCustomer() {
		ADD_CUSTOMER_MENU_ELEMENT.click();
	}
	
	public void clickOnListCustomer() {
		LIST_CUSTOMER_MENU_ELEMENT.click();
	}
	
}
