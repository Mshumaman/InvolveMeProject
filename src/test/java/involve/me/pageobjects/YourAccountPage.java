package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class YourAccountPage extends BasePage {
	@FindBy(css = "[aria-controls='profile']")
	private WebElement profileTab;
	@FindBy(css = ".nav-item #tab-edit")
	private WebElement updateInfoTab;
	@FindBy(css = "[aria-controls='password']")
	private WebElement changePasswordTab;
	@FindBy(css = ".btn.btn-primary.pull-right.btn-sm.btn-switch-account-edit")
	private WebElement editBtn;
	@FindBy(css = ".btn.btn-sm.btn-secondary.pull-right")
	private WebElement confirmNowBtn;
	@FindBy(css = "[name='first_name']")
	private WebElement firstNameField;
	@FindBy(css = "[name='last_name']")
	private WebElement lastNameField;
	@FindBy(css = "#timezone")
	private WebElement timezoneMenu;
	@FindBy(css = "[for='monthly_report']")
	private WebElement monthlyReportsCheckbox;
	@FindBy(css = "div:nth-child(9) button")
	private WebElement updateBtn;
	@FindBy(css = "[name='old_password']")
	private WebElement oldPasswordfield;
	@FindBy(css = "[name='password']")
	private WebElement newPasswordField;
	@FindBy(css = "[name='password_confirmation']")
	private WebElement confirmNewPasswordField;
	@FindBy(css = "div:nth-child(6) button")
	private WebElement updatePasswordBtn;
	@FindBy(css = ".e-title")
	private WebElement yourAccountTitle;
	@FindBy(css = ".alert.alert-success")
	private WebElement updateSucceedMsg;
	@FindBy(css = "tr:nth-child(1) > td")
	private WebElement profileName;
	@FindBy(css = ".alert.alert-success")
	private WebElement changePasswordSuccessMsg;
	@FindBy(css = ".alert.alert-danger")
	private WebElement changePasswordErrorMsg;

	public YourAccountPage(WebDriver driver) {
		super(driver);
	}

	public void openProfileTab() {
		click(profileTab);
	}

	public void openUpdateInfoTab() {
		click(updateInfoTab);
	}

	public void openChangePasswordTab() {
		click(changePasswordTab);
	}

	public void pressConfirmNowBtn() {
		click(confirmNowBtn);
	}

	public void pressEditBtn() { // in the profile tab
		click(editBtn);
	}

	public void fillUpdateInfo(String firstName, String lastName) {
		fillText(firstNameField, firstName);
		fillText(lastNameField, lastName);
		Select timezone = new Select(timezoneMenu);
		timezone.selectByValue("America/New_York");
		click(monthlyReportsCheckbox);
		click(updateBtn);

	}

	public void fillChangePassword(String oldPass, String newPass, String confirmNewPass) {
		fillText(oldPasswordfield, oldPass);
		fillText(newPasswordField, newPass);
		fillText(confirmNewPasswordField, confirmNewPass);
		click(updatePasswordBtn);
	}

	public void clearUpdateInfoFields() {
		clearText(firstNameField);
		clearText(lastNameField);
	}

	public void clearChangePasswordFields() {
		clearText(oldPasswordfield);
		clearText(newPasswordField);
		clearText(confirmNewPasswordField);
	}

	public void pressUpdateBtn() {
		click(updateBtn);
	}

	// validation
	public String getYourAccountTitle() {
		return getText(yourAccountTitle);
	}

	public String geteditText() {
		wait.until(ExpectedConditions.elementToBeClickable(editBtn));
		return getText(editBtn);
	}

	public String getUpdateBtnText() {
		return getText(updateBtn);
	}

	public String getUpdateSucceedMsg() {
		return getText(updateSucceedMsg);
	}

	public String getEmptyFirstNameFieldErrMsg(String attribute) {
		return getAttribute(firstNameField, attribute);
	}

	public String getEmptyLastNameFieldErrMsg(String attribute) {
		return getAttribute(lastNameField, attribute);
	}

	public String getProfileNameText() {
		return getText(profileName);
	}

	public String getChangePasswordSuccessMsg() {
		return getText(changePasswordSuccessMsg);
	}

	public String getChangePasswordErrorMsg() {
		return getText(changePasswordErrorMsg);
	}

	public String getEmptyOldPasswordErrMsg(String attribute) {
		return getAttribute(oldPasswordfield, attribute);
	}

	public String getEmptyNewPasswordErrMsg(String attribute) {
		return getAttribute(newPasswordField, attribute);
	}

	public String getEmptyNewPasswordConfirmErrMsg(String attribute) {
		return getAttribute(confirmNewPasswordField, attribute);
	}
}
