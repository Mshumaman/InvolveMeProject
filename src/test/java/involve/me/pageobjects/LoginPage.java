package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	@FindBy(css = "[type='email']")
	private WebElement emailField;
	@FindBy(css = "[type='password']")
	private WebElement passwordField;
	@FindBy(css = ".btn.btn-primary.btn-lg")
	private WebElement loginBtn;
	@FindBy(css = ".form-group>p>a")
	private WebElement createAccount;
	@FindBy(css = ".alert.alert-danger")
	private WebElement errMsg;
	@FindBy(css = ".form-horizontal.form-login div >  a")
	private WebElement forgotPassword;
	@FindBy(css = ".e-form-heading")
	private WebElement loginTitle;
	@FindBy(css = ".btn.btn-secondary")
	private WebElement readMore;

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@Step("login with user: {emailText} and password: {passwordText}")
	public void login(String emailText, String passwordText) {
		fillText(emailField, emailText);
		fillText(passwordField, passwordText);
		click(loginBtn);
	}

	public void clearFields() {
		clearText(emailField);
		clearText(passwordField);
	}

	public void pressLoginBtn() {
		click(loginBtn);
	}

	public void openForgotPassword() {
		click(forgotPassword);
	}

	public void openCreateAccount() {
		click(createAccount);
	}

	public void openReadMore() {
		click(readMore);
	}

	// validation
	public String getErrMsg() {
		return getText(errMsg);
	}

	public String getLoginHeader() {
		return getText(loginTitle);
	}

	public String getEmptyEmailFieldErrMsg(String attribute) {
		return getAttribute(emailField, attribute);
	}

	public String getEmptyPasswordFieldErrMsg(String attribute) {
		return getAttribute(passwordField, attribute);
	}
}
