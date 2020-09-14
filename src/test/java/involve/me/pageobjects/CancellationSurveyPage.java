package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CancellationSurveyPage extends BasePage {
	@FindBy(css = ".e-email")
	private WebElement emailField;
	@FindBy(css = "[data-id='u4yrcen']")
	private WebElement cancelReason1; // The product didn't have the feature I was looking for
	@FindBy(css = "[data-id='nxsxq8f']")
	private WebElement cancelReason2; // Setting up/using the features I needed was too difficult.
	@FindBy(css = "[data-id='n81whfo']")
	private WebElement cancelReason3; // it's too expensive
	@FindBy(css = "[data-id='w4ih12b']")
	private WebElement cancelReason4; // I'm using another service.
	@FindBy(css = "[data-id='ba35j02']")
	private WebElement cancelReason5; // The service is no longer needed
	@FindBy(css = "span .el-checkbox__inner")
	private WebElement saleCheckbox;
	@FindBy(css = ".e-freetxt-answer")
	private WebElement feedbackTextbox;
	@FindBy(css = ".c-button.btn")
	private WebElement completeBtn;
	@FindBy(css = "[title='mobile']")
	private WebElement mobileView;
	@FindBy(css = "[title='desktop'")
	private WebElement desktopView;
	@FindBy(css = ".e-headline.is-shrinkable span")
	private WebElement successMsg;
	@FindBy(css = ".c-question span")
	private WebElement cancelReasonMsg;
	@FindBy(css = ".c-button-group-button.e-close.float-right > img")
	private WebElement exitSurvey;
	@FindBy(css = ".fa.fa-angle-left.text-white")
	private WebElement backBtn;
	@FindBy(css = ".fa.fa-angle-right.text-white")
	private WebElement forwardBtn;
	@FindBy(css = "label > div > div > span")
	private WebElement emailErrMsg;
	@FindBy(css = ".c-question-container.content-item.is-snappable.error .error-container.error > div > span")
	private WebElement notSelectingReasonErrMsg;

	public CancellationSurveyPage(WebDriver driver) {
		super(driver);
	}

	/*
	 * fill a Survey with a user's information
	 */
	public void fillCancelSurvey(String email, String feedback) {
		fillText(emailField, email);
		click(cancelReason1);
		click(saleCheckbox);
		fillText(feedbackTextbox, feedback);
		click(completeBtn);
	}

	public void fillSurveyWithoutReason(String email) {
		fillText(emailField, email);
		click(completeBtn);

	}

	public void surveySwitchReasons(String email) {
		fillText(emailField, email);
		click(cancelReason1);
		click(cancelReason2);
		click(cancelReason3);
		click(cancelReason4);
		click(cancelReason5);
		click(completeBtn);
	}

	public void switchToMobileView() {
		click(mobileView);
	}

	public void switchToDesktopView() {
		click(desktopView);
	}

	public void exitSurvey() {
		click(exitSurvey);
	}

	public void goBackBtn() {
		click(backBtn);
		wait.until(ExpectedConditions.elementToBeClickable(completeBtn));
	}

	public void goForwardBtn() {
		click(forwardBtn);
	}

	// validation
	public String getSuccessMsg() {
		wait.until(ExpectedConditions.visibilityOf(successMsg));
		return getText(successMsg);
	}

	public String getCancelReasonMsg() {
		return getText(cancelReasonMsg);
	}

	public String getInvalidEmailMsg() {
		return getText(emailErrMsg);
	}

	public String getNoSelectReasonErrMsg() {
		return getText(notSelectingReasonErrMsg);
	}
}
