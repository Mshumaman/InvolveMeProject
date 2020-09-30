package involve.me.tests;

import java.util.concurrent.TimeUnit;

import static com.github.automatedowl.tools.AllureEnvironmentWriter.allureEnvironmentWriter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.google.common.collect.ImmutableMap;

import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;

public class BaseTest {
	WebDriver driver;
	String BrowserVersion;
	String BrowserName;

	@Parameters({ "browser", "baseUrl" })
	@BeforeClass
	protected void setup(ITestContext testContext, String browser, String baseUrl) {
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get(Utils.readProperty("url"));
		BrowserVersion = ((RemoteWebDriver) driver).getCapabilities().getVersion();
		BrowserName = ((RemoteWebDriver) driver).getCapabilities().getBrowserName();
		testContext.setAttribute("WebDriver", this.driver);
		allureEnvironmentWriter(ImmutableMap.<String, String>builder().put("Browser", BrowserName)
				.put("Browser.Version", BrowserVersion).put("URL", baseUrl).build());
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
