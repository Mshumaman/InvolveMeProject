package involve.me.tests;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import involve.me.pageobjects.LoggedInPage;
import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.NewFeaturesPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import utils.AllureAttachment;

@Epic("Login")
public class LoginPageTests extends BaseTest {

	/*
	 * In this test case i will test logging in successfully with correct
	 * credentials
	 */
	@Feature("Logging In")
	@Story("As a User when I fill correctly the login info I should be logged in")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "login success test")
	@Description("Test for logging in successfully with valid credetials")
	public void tc01_loginSuceed() {
		LoggedInPage lip = new LoggedInPage(driver);
		// validation
		String expected = "My Workspace";
		String actual = lip.getWorkspaceHeader();
		Assert.assertEquals(actual, expected);

		// logout
		lip.logout();

		// link to the login page
		AllureAttachment.attachURL("https://app.involve.me/login");
	}

	@Feature("Logging In")
	@Story("As a User when I fill wrong credentials I should receive an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(dataProvider = "getDataWrongCredentials", description = "filling wrong credentials test")
	@Description("Logging in with valid but wrong credentials")
	public void tc02_loginFailed(String email, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.login(email, password);

		// validation
		String expected = "These credentials do not match our records.";
		String actual = lp.getErrMsg();
		Assert.assertEquals(actual, expected);
	}

	/*
	 * This data is wrong user names and passwords on order to do negative testing
	 * in the login page. The expected result should be error message.
	 */
	@DataProvider
	public Object[][] getDataWrongCredentials() {
		Object[][] myData = { { "omri@gmail.com", "28b31028B310" }, { "omri1@gmail.com", "28b31028B310" },
				{ "omri.pointer@gmail.com", "12345" }, { "omri.pointer@gmail.com", "abcdse" }, };
		return myData;
	}

	@Feature("Logging In")
	@Story("As a User when I try to login when the fields are empty I should receive an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "pressing login button with empty fields test")
	@Description("Login when both email & password fields are empty ")
	public void tc03_emptyFieldLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.login("", "");

		// validation
		String actual = lp.getEmptyEmailFieldErrMsg("validationMessage");
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}

	@Feature("Logging In")
	@Story("As a User when I fill only the email fields I should receive an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Only the email fields is filled test")
	@Description("Login when the password field is empty")
	public void tc04_fillOnlyEmail() {
		LoginPage lp = new LoginPage(driver);
		lp.login("omri.pointer@gmail.com", "");

		// validation
		String actual = lp.getEmptyPasswordFieldErrMsg("validationMessage");
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}

	@Feature("Logging In")
	@Story("As a User when I fill only the password fields I should receive an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Only the Password fields is filled test")
	@Description("Login when the email field is empty")
	public void tc05_fillOnlyPassword() {
		LoginPage lp = new LoginPage(driver);
		lp.login("", "28b31028B310");

		// validation
		String actual = lp.getEmptyEmailFieldErrMsg("validationMessage");
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}

	@Feature("Logging In")
	@Story("As a User when I fill the email field with invalid email that doesn't contain a domain I should receive an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getDataInvalidEmail", description = "fill invalid email without domain test")
	@Description("Login with invalid email that doesn't contains a domain & with valid password")
	public void tc06_invalidEmail(String email, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.login(email, password);

		// validation
		String actual = lp.getEmptyEmailFieldErrMsg("validationMessage");
		String expected = "Please include an '@' in the email address. '" + email + "' is missing an '@'.";
		Assert.assertEquals(actual, expected);
	}

	/*
	 * This data is invalid emails that doesn't contains a domain. The expected
	 * result should be error message.
	 */
	@DataProvider
	public Object[][] getDataInvalidEmail() {
		Object[][] myData = { { "a", "28b31028B310" }, { "OmriMaman", "28b31028B310" }, };
		return myData;
	}

	@Feature("Logging In")
	@Story("As a User when I fill the email field with invalid email that doesn't contain a localpart I should receive an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(dataProvider = "getDataInvalidEmail2", description = "fill invalid email without localpart test")
	@Description("Login with invalid email that doesn't contains a localpart & with valid password")
	public void tc07_invalidEmail2(String email, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.login(email, password);

		// validation
		String actual = lp.getEmptyEmailFieldErrMsg("validationMessage");
		String expected = "Please enter a part followed by '@'. '" + email + "' is incomplete.";
		Assert.assertEquals(actual, expected);
	}

	/*
	 * This data is invalid emails that doesn't contains a localpart. The expected
	 * result should be error message.
	 */
	@DataProvider
	public Object[][] getDataInvalidEmail2() {
		Object[][] myData = { { "@", "28b31028B310" }, { "@gamil.com", "28b31028B310" }, };
		return myData;
	}

	@Feature("Navigating")
	@Story("Pressing the 'Read More' link should navigate to the page")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "test for openning the 'New Features' page")
	@Description("Navigating to 'New Features' page")
	public void tc08_openReadMore() {
		LoginPage lp = new LoginPage(driver);
		String main = driver.getWindowHandle();
		lp.openReadMore();

		// validation
		Set<String> list = driver.getWindowHandles();
		for (String win : list) {
			driver.switchTo().window(win);
		}
		NewFeaturesPage nfp = new NewFeaturesPage(driver);
		String expected = "INVOLVE.ME CAN COLLECT RECURRING PAYMENTS NOW";
		String actual = nfp.getHeadlineMsg();
		Assert.assertEquals(actual, expected);
		driver.close();
		driver.switchTo().window(main);

		// Link to the new features page
		AllureAttachment.attachURL("https://www.involve.me/blog/recurring-payments/");
	}
}
