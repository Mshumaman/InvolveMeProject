package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utils.AllureAttachment;

public class ForgotPasswordPage extends BasePage {
	@FindBy(css = ".form-control.e-zoom-input")
	private WebElement emailField;
	@FindBy(css = ".btn.btn-primary")
	private WebElement resetPasswordBtn;
	@FindBy(linkText = "Login")
	private WebElement loginLink;
	@FindBy(linkText = "Register")
	private WebElement registerLink;
	@FindBy(css = ".alert.alert-success")
	private WebElement confirmMsg;
	@FindBy(css = ".alert.alert-danger")
	private WebElement errMsg;

	public ForgotPasswordPage(WebDriver driver) {
		super(driver);
	}

	public void resetPassword(String text) {
		fillText(emailField, text);
		AllureAttachment.attachElementScreenshot(resetPasswordBtn);
		click(resetPasswordBtn);
	}

	public void openLogin() {
		AllureAttachment.attachElementScreenshot(loginLink);
		click(loginLink);
	}

	public void openRegister() {
		AllureAttachment.attachElementScreenshot(registerLink);
		click(registerLink);
	}

	// validation
	public String getConfirmMassage() {
		return getText(confirmMsg);
	}

	public String getErrorMsg() {
		return getText(errMsg);
	}

	public String getEmptyEmailFieldsErrMsg(String attribute) {
		return getAttribute(emailField, attribute);
	}
}
