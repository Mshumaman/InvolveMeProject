package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.ForgotPasswordPage;
import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.MainPage;
import involve.me.pageobjects.RegisterPage;

public class ForgotPasswordTests extends BaseTest {

	@Override
	public void setupLogin() {
		// TODO Auto-generated method stub
		super.setupLogin();
	}
	/*
	 * In this test case i will test e2e the function of reset user's password
	 * successfully
	 */
	@Test(description = "reset password test")
	public void tc01_resetPassword() {
		// go to login page
		MainPage mp = new MainPage(driver);
		mp.openLogin();
		LoginPage lp = new LoginPage(driver);
		lp.openForgotPassword();
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("omri.pointer@gmail.com");

		// validation
		String expected = "A reset link has been sent to the email address, if it has been used to register for an account.";
		String actual = fpp.getConfirmMassage();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "unregistered email test")
	public void tc02_wrongEmail() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("omri@gmail.com");

		// validation
		String expected = "We can't find a user with that e-mail address.";
		String actual = fpp.getErrorMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for invalid email's field")
	public void tc03_emptyEmailField() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("a");
		// validation
		String expected = "Please include an '@' in the email address. 'a' is missing an '@'.";
		String actual = fpp.getEmptyEmailFieldsErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for empty email's field")
	public void tc04_invalidEmail() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("");

		// validation
		String expected = "Please fill out this field.";
		String actual = fpp.getEmptyEmailFieldsErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "go to login page test")
	public void tc05_gotoLogin() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.openLogin();

		// validation
		LoginPage lp = new LoginPage(driver);
		String expected = "Log in";
		String actual = lp.getLoginHeader();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "go to register page test")
	public void tc06_gotoRegister() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.openRegister();

		// validation
		RegisterPage rp = new RegisterPage(driver);
		String expected = "Get started for free";
		String actual = rp.getRegisterTitle();
		Assert.assertEquals(actual, expected);

	}
}
