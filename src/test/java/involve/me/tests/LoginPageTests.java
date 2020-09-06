package involve.me.tests;

import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import involve.me.pageobjects.LoggedInPage;
import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.NewFeaturesPage;

public class LoginPageTests extends BaseTest {

	/*
	 * In this test case i will test logging in successfully with correct credentials
	 */
	@Test(description = "login success test")
	public void tc01_loginSuceed() {
		LoggedInPage lip = new LoggedInPage(driver);
		//validation
		String expected = "My Workspace";
		String actual = lip.getWorkspaceHeader();
		Assert.assertEquals(actual, expected);
		
		// logout
		lip.logout();
	}

	@Test(dataProvider = "getDataWrongCredentials", description = "filling wrong credentials test")
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

	@Test(description = "pressing login button with empty fields test")
	public void tc03_emptyFieldLogin() {
		LoginPage lp = new LoginPage(driver);
		lp.login("", "");
		lp.pressLoginBtn();

		// validation
		String actual = lp.getEmptyEmailFieldErrMsg("validationMessage");
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "Only the email fields is filled test")
	public void tc04_fillOnlyEmail() {
		LoginPage lp = new LoginPage(driver);
		lp.login("omri.pointer@gmail.com", "");

		// validation
		String actual = lp.getEmptyPasswordFieldErrMsg("validationMessage");
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Only the Password fields is filled test")
	public void tc05_fillOnlyPassword() {
		LoginPage lp = new LoginPage(driver);
		lp.login("", "28b31028B310");

		// validation
		String actual = lp.getEmptyEmailFieldErrMsg("validationMessage");
		String expected = "Please fill out this field.";
		Assert.assertEquals(actual, expected);
	}

	@Test(dataProvider = "getDataInvalidEmail", description = "fill invalid email without domain test")
	public void tc06_invalidEmail(String email, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.login(email, password);
		lp.pressLoginBtn();

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

	@Test(dataProvider = "getDataInvalidEmail2", description = "fill invalid email without localpart test")
	public void tc07_invalidEmail2(String email, String password) {
		LoginPage lp = new LoginPage(driver);
		lp.login(email, password);
		lp.pressLoginBtn();

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

	@Test(description = "test for openning the new features 'read more' page")
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
		String expected = "NEW FEATURE:\n" + "SEND CUSTOM EMAILS TO PARTICIPANTS";
		String actual = nfp.getHeadlineMsg();
		Assert.assertEquals(actual, expected);
		driver.close();
		driver.switchTo().window(main);
	}
}
