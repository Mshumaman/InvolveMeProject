package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.ForgotPasswordPage;
import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.MainPage;
import involve.me.pageobjects.RegisterPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import utils.AllureAttachment;

@Epic("Forgot Password")
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
	@Feature("Reset Password")
	@Story("As a User when I'm filling the reset password fully and correctly I should get a confirmation message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "reset password test")
	@Description("E2E test for reset user's password")
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
		
		//link to forgot password page
		AllureAttachment.attachURL("https://app.involve.me/password/reset");
	}

	@Feature("Reset Password")
	@Story("As a User when I fill unregistered email I should get an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "unregistered email test")
	@Description("Filling the email field with unregistered user")
	public void tc02_wrongEmail() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("omri@gmail.com");

		// validation
		String expected = "We can't find a user with that e-mail address.";
		String actual = fpp.getErrorMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Reset Password")
	@Story("As a User when I fill invalid email without domain I should get an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for invalid email's field")
	@Description("Filling the email field with credentails that doesn't contains a domain")
	public void tc03_emptyEmailField() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("a");
		// validation
		String expected = "Please include an '@' in the email address. 'a' is missing an '@'.";
		String actual = fpp.getEmptyEmailFieldsErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Reset Password")
	@Story("As a User when I pressing the reset password button without credentials I should get an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for empty email's field")
	@Description("Pressing the reset password button when the email field is empty")
	public void tc04_invalidEmail() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.resetPassword("");

		// validation
		String expected = "Please fill out this field.";
		String actual = fpp.getEmptyEmailFieldsErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Navigating")
	@Story("As a User when I click on the 'Login' link I should be navigated to the login page")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "go to login page test")
	@Description("Navigating to the 'Login' page")
	public void tc05_gotoLogin() {
		ForgotPasswordPage fpp = new ForgotPasswordPage(driver);
		fpp.openLogin();

		// validation
		LoginPage lp = new LoginPage(driver);
		String expected = "Log in";
		String actual = lp.getLoginHeader();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Navigating")
	@Story("As a User when I click on the 'Register' link I should be navigated to the register page")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "go to register page test")
	@Description("Navigating to the 'Register' page")
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
