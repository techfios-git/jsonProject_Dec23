package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ListCustomersPage extends BasePage {

	WebDriver driver;

	public ListCustomersPage(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[9]/a[1]")
	WebElement EDIT_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//tbody/tr[1]/td[9]/a[2]")
	WebElement VIEW_BUTTON_ELEMENT;
	@FindBy(how = How.XPATH, using = "//*[@id=\"DataTables_filter\"]")
	WebElement SEARCH_LIST_CUSTOMERS_ELEMENT;

	public void clickOnEditButton() {
		EDIT_BUTTON_ELEMENT.click();
	}

	public void clickOnViewButton() {
		VIEW_BUTTON_ELEMENT.click();
	}

	public void insertSearchOnListCustomer() {
		SEARCH_LIST_CUSTOMERS_ELEMENT.sendKeys("");
	}

	// tbody/tr[1]/td[2]/a
	// tbody/tr[2]/td[2]/a
	// tbody/tr[3]/td[2]/a
	// tbody/tr[i]/td[2]/a
	// tbody/tr[1]/td[9]/button

	String before_xpath = "//tbody/tr[";
	String after_xpath = "]/td[2]/a";
	String after_xpath_delete = "]/td[9]/button";

	public void validateAndDeleteInsertedName() {
		for (int i = 1; i <= 5; i++) {
			String actualName = driver.findElement(By.xpath(before_xpath + i + after_xpath)).getText();
			System.out.println(actualName);
			if (actualName.contains(AddCustomerPage.name)) {
				System.out.println("Inserted name exist.");
				driver.findElement(By.xpath(before_xpath + i + after_xpath_delete)).click();
			}
			break;
		}
	}

}
