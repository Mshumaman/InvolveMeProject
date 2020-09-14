package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.LoggedInPage;
import involve.me.pageobjects.LoginPage;
import involve.me.pageobjects.MainPage;
import involve.me.pageobjects.YourAccountPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Your Account")
public class YourAccountTests extends BaseTest {

	/*
	 * In this test cases I will test the functions in the "your account" page. In
	 * some test cases i will change the password. In order to prevent the other
	 * tests to fail, I chose to fill different login credentials.
	 */
	@Override
	public void setupLogin() {
		super.setupLogin();
	}

	@Feature("Navigating")
	@Story("As a User when I navigate to the 'Your Account' page, the page should appear")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "navigate to your account page test")
	@Description("Test for navigating to the 'Your Account' page")
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

	@Feature("Navigating")
	@Story("As a User when I press a tab, every tab that clicked should be selected")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Swithcing tabs test")
	@Description("Test for switching the tabs in the 'Your Account' page")
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

	@Feature("Email Confirmation")
	@Story("As a User when I press the 'Confirm Mail' button I should recieve a success message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Confirm email test")
	@Description("Test for Pressing the 'Confirm Email' button in the profile tab")
	private void tc03_confirmEmail() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.pressConfirmNowBtn();

		// validation
		LoggedInPage lip = new LoggedInPage(driver);
		String expected = "A new confirmation e-mail has been sent to your email.";
		String actual = lip.getEmailConfirmationMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Navigating")
	@Story("As a User when I press the 'Edit' button in the 'Profie' tab, the profile tab should appear")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for update info")
	@Description("Test for update info by clicking the edit button on the profile tab")
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

	@Feature("Updating Information")
	@Story("As a User when I fill the upadate info with valid & correct credetials, I the info should be updated")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for successfully updating info")
	@Description("Filling valid credentials and updating the user's info")
	public void tc05_updateInfoSuccessfully() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillUpdateInfo("Indiana", "Jones");

		// validation
		String expected = "Profile successfully updated.";
		String actual = yap.getUpdateSucceedMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Updating Information")
	@Story("As a User when I try to update info when the fields are empty I expect an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "Empty fields in the update info tab")
	@Description("Pressing the update button when the fields are empty")
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

	@Feature("Updating Information")
	@Story("When I try to update info when only the first name is filled I expect an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "only first name is filled test")
	@Description("Pressing the update button when only the the first name field is filled")
	public void tc07_uiEmptyLastNameField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillUpdateInfo("Michael", "");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyLastNameFieldErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Updating Information")
	@Story("When I try to update info when only the first name is filled I expect an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "only last name is filled test")
	@Description("Pressing the update button when only the the last name field is filled")
	public void tc08_uiEmptyFirstNameField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.clearUpdateInfoFields();
		yap.fillUpdateInfo("", "Jordan");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyFirstNameFieldErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Updating Information")
	@Story("When I try to update info when only the first & last name is filled with numbers I expect an error message")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "first & last name filled with numbers")
	@Description("Pressing the update button when first & last name field is filled with numbers")
	public void tc09_uiFillingNamesWithNumbers() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.clearUpdateInfoFields();
		yap.fillUpdateInfo("123", "456");

		// validation
		String expected = "123 456";
		String actual = yap.getProfileNameText();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Updating Information")
	@Story("When I try to update info when only the first & last name is filled with special characters I expect an error message")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "first & last name filled special characters")
	@Description("Pressing the update button when first & last name field is filled with characters")
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

	@Feature("Change Password")
	@Story("As a User when I change the password correctly I should receive a success message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for changing accout password successfully")
	@Description("Successfully changing the password with valid credentials")
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

	@Feature("Change Password")
	@Story("In order to preserve the test suite, I restore the original password")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "test for restoring to the original password")
	@Description("Restore to the original password in order to make sure the suite will work in regression/repeating tests")
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

	@Feature("Change Password")
	@Story("When I try to change the password with wrong old password I should recieve an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for changing password with wrong old password")
	@Description("Unsuitability between old and new password")
	public void tc13_wrongOldPassword() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("a", "1234567890", "1234567890");

		// validation
		String expected = "That is not your old password.";
		String actual = yap.getChangePasswordErrorMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Change Password")
	@Story("When the new password doesn't match the confirm new password I should get an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for new password & confirm new password doesn't match")
	@Description("unsuitability between new and confirm new password")
	public void tc14_passwordConfirmDontMatch() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("1234567890", "1234567890A", "1234567890B");

		// validation
		String expected = "The password confirmation does not match.";
		String actual = yap.getChangePasswordErrorMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Change Password")
	@Story("When I try to complete the change password process and the fields are empty I should get an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for empty fields in the change password tab")
	@Description("Test for changing password when all the fields are empty")
	public void tc15_changePasswordEmptyFields() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("", "", "");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyOldPasswordErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Change Password")
	@Story("When I change the password wiht empty 'new password' field, I should get an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for update new password with empty 'new password' field.")
	@Description("Test for changing password when the 'new password' field is empty")
	public void tc16_emptyNewPasswordField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("1234567890", "", "12345678910");

		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyNewPasswordErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Change Password")
	@Story("When I change the password wiht empty 'new password confirmation' field, I should get an error message")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for update new password with empty 'new password confirmation' field.")
	@Description("Test for changing password when the 'new password confirmation' field is empty")
	public void tc17_emptyNewPasswordConfirmField() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.openChangePasswordTab();
		yap.fillChangePassword("1234567890", "12345678910", "");
		// validation
		String expected = "Please fill out this field.";
		String actual = yap.getEmptyNewPasswordConfirmErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Change Password")
	@Story("When changing the password to one that contains less than 8 characters, I should get an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for invalid password with less than 8 characters")
	@Description("Changing password that contains less than 8 characters")
	public void tc18_invalidNewPassword() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillChangePassword("1234567890", "1234", "1234");

		// validation
		String expected = "Please lengthen this text to 8 characters or more (you are currently using 4 characters).";
		String actual = yap.getEmptyNewPasswordErrMsg("validationMessage");
		Assert.assertEquals(actual, expected);
	}

	@Feature("Change Password")
	@Story("When changing the password to one that contains 8 characters, I should get an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for new password with 8 characters")
	@Description("Changing password that contains exactly 8 characters")
	public void tc19_invalidNewPassword2() {
		YourAccountPage yap = new YourAccountPage(driver);
		yap.fillChangePassword("1234567890", "12345678", "12345678");

		// validation
		String expected = "The password must be at least 10 characters.";
		String actual = yap.getChangePasswordErrorMsg();
		Assert.assertEquals(actual, expected);
	}

}
