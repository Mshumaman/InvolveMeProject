package involve.me.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.MainPage;
import utils.Utils;

public class BaseTest {
	WebDriver driver;

	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Automation\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(Utils.readProperty("url"));
	}
	@BeforeClass
	public void setupLogin() {
		// go to login page
		MainPage mp = new MainPage(driver);
		mp.openLogin();
		LoginPage lp = new LoginPage(driver);
		lp.login("omri.pointer@gmail.com", "28b31028B310");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
