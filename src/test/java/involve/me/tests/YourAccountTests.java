package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.LoggedInPage;
import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.MainPage;
import involve.me.pageobjects.YourAccountPage;

public class YourAccountTests extends BaseTest {

	/*
	 * In this test casses I will test the functions in the "your account" page. In
	 * some test cases i will change the password. In order to prevent the other
	 * tests to fail, I chose to fill different login credentials.
	 */
	@Override
	public void setupLogin() {
		super.setupLogin();
	}

	@Test(description = "navigate to your account page test")
	public void tc01_navYourAccountPage() {
		MainPage mp = new MainPage(driver);
		mp.openLogin();
		LoginPage lp = new LoginPage(driver);
		lp.login("omri.pointer+2@gmail.com", "1234567890");
		LoggedInPage lip = new LoggedInPage(driver);
		lip.openYourAccount();

		// validation
		YourAccountPage yap = new YourAccountPage(driver);
		String expected = "Your Account";
		String actual = yap.getYourAccountTitle();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Swithcing tabs test")
	public void tc02_switchTabs() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openUpdateInfoTab();
		yap.openChangePasswordTab();
		yap.openProfileTab();

		// validation
		String expected = "Edit";
		String actual = yap.geteditText();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Confirm email test")
	private void tc03_confirmEmail() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.pressConfirmNowBtn();

		// validation
		LoggedInPage lip = new LoggedInPage(driver);
		String expected = "A new confirmation e-mail has been sent to your email.";
		String actual = lip.getEmailConfirmationMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for update info by clicking the edit button on the profile tab")
	public void tc04_clickEditBtn() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.openYourAccount();
		YourAccountPage yap = new YourAccountPage(driver);
		yap.pressEditBtn();

//		validation
		String expected = "Update";
		String actual = yap.getUpdateBtnText();
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "test for successfully updating info")
	public void tc05_updateInfoSuccessfully() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillUpdateInfo("Indiana", "Jones");

		// validation
		String expected = "Profile successfully updated.";
		String actual = yap.getUpdateSucceedMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Empty fields in the update info tab")
	public void tc06_updateInfoEmptyFields() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openUpdateInfoTab();
		yap.clearUpdateInfoFields();
		yap.pressUpdateBtn();

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyFirstNameFieldErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "only first name is filled test")
	public void tc07_uiEmptyLastNameField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillUpdateInfo("Michael", "");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyLastNameFieldErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "only last name is filled test")
	public void tc08_uiEmptyFirstNameField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.clearUpdateInfoFields();
		yap.fillUpdateInfo("", "Jordan");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyFirstNameFieldErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "first & last name filled with numbers")
	public void tc09_uiFillingNamesWithNumbers() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.clearUpdateInfoFields();
		yap.fillUpdateInfo("123", "456");

		// validation
		String expected = "123 456";
		String actual = yap.getProfileNameText();
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "first & last name filled special characters")
	public void tc10_uiNamedFilledWithSpecialCharacters() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openUpdateInfoTab();
		yap.clearUpdateInfoFields();
		yap.fillUpdateInfo("!@#", "$%^");

		// validation
		String expected = "!@# $%^";
		String actual = yap.getProfileNameText();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for changing accout password successfully")
	public void tc11_changePasswordSuccessfully() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.clearChangePasswordFields();
		yap.fillChangePassword("1234567890", "12345678910", "12345678910");

		// validation
		String expected = "Password successfully updated.";
		String actual = yap.getChangePasswordSuccessMsg();
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "test for restoring to the original password")
	public void tc12_restoreOriginalPassword() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.clearChangePasswordFields();
		yap.fillChangePassword("12345678910", "1234567890", "1234567890");

		// validation
		String expected = "Password successfully updated.";
		String actual = yap.getChangePasswordSuccessMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for changing password with wrong old password")
	public void tc13_wrongOldPassword() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("a", "1234567890", "1234567890");

		// validation
		String expected = "That is not your old password.";
		String actual = yap.getChangePasswordErrorMsg();
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "test for new password & confirm new password doesn't match")
	public void tc14_passwordConfirmDontMatch() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("1234567890", "1234567890A", "1234567890B");

		// validation
		String expected = "The password confirmation does not match.";
		String actual = yap.getChangePasswordErrorMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for empty fields in the change password tab")
	public void tc15_changePasswordEmptyFields() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("", "", "");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyOldPasswordErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "test for update new password with empty 'new password' field.")
	public void tc16_emptyNewPasswordField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("1234567890", "", "12345678910");
		
		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyNewPasswordErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for update new password with empty 'new password confirmation' field.")
	public void tc17_emptyNewPasswordConfirmField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("1234567890", "12345678910", "");
		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyNewPasswordConfirmErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for new password with less than 8 characters")
	public void tc18_invalidNewPassword() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillChangePassword("1234567890", "1234", "1234");
		
		// validation
		String expected = "Please lengthen this text to 8 characters or more (you are currently using 4 characters).";
		String actual = yap.getEmptyNewPasswordErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for new password with 8 characters")
	public void tc19_invalidNewPassword2() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillChangePassword("1234567890", "12345678", "12345678");
		
		// validation
		String expected = "The password must be at least 10 characters.";
		String actual = yap.getChangePasswordErrorMsg();
		Assert.assertEquals(actual, expected);

	}

}
