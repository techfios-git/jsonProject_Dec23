package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BrowserFactory {
	
	static WebDriver driver;
	static String browser;
	static String url;
	static String userName;
	static String password;
	
	public static String getUserName() {
		return userName;
	}

	public static String getPassword() {
		return password;
	}

	
	
	public static void readConfig() {
		// InputStream //BufferedReader //FileReader //Scanner

		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser used: " + browser);
			url = prop.getProperty("url");
			userName = prop.getProperty("userName");
			password = prop.getProperty("password");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static WebDriver init() {
		readConfig();
		if (browser.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", "drivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			System.out.println("Please use a valid browser!!!");
		}
		driver.manage().deleteAllCookies();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

}
