package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.CancellationSurveyPage;
import involve.me.pageobjects.LoggedInPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Cancellation Survey")
public class CancellationSurveyTest extends BaseTest {

	/*
	 * In this test case i will test filling the cancellation form e2e successfully
	 */
	@Feature("Navigating")
	@Story("As a User when I'm logging in and navigating to the 'Cancellation Survey' page, the page should appear")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "cancellation survey page test")
	@Description("Logging in and navigating to the 'Cancellation Survey' page")
	public void tc01_openCancelSurvey() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.openTemplates();
		lip.openCancellationSurvey();

		// validation
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		String expected = "What's the reason for your cancellation?";
		String actual = cs.getCancelReasonMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Filling the Cancellation Survey")
	@Story("As a User when I fill the form correctly I should see a confirmation message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for filling all info in cancellation survey")
	@Description("Filling full & correct credetials in the 'Cancellation Survey' page")
	public void tc02_fillCancelSurvey() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.fillCancelSurvey("omri.pointer@gmail.com", "testing 1,2,3");

		// validation
		String expected = "Your cancellation\n" + "has been completed.";
		String actual = cs.getSuccessMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Filling the Cancellation Survey")
	@Story("As a User when I pressing the back button i should navigate back to the 'Cancellation Survey' page")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "test for navigate back to survey")
	@Description("Test for navigating back to 'Cancellation Survey' page after survey completed")
	public void tc03_goBackToSurvey() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.goBackBtn();

		// validation
		String expected = "What's the reason for your cancellation?";
		String actual = cs.getCancelReasonMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Filling the Cancellation Survey")
	@Story("As a User when I trying to complete the survey without information i should get an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "click complete with empty fields test")
	@Description("Test for pressing the complete buttun when the fields are empty")
	public void tc04_emptyFieldsSurvey() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.exitSurvey();
		LoggedInPage lip = new LoggedInPage(driver);
		lip.openCancellationSurvey();
		cs = new CancellationSurveyPage(driver);
		cs.fillCancelSurvey("", "");

		// validation
		String expected = "Fill in all required fields.";
		String actual = cs.getNoSelectReasonErrMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Filling the Cancellation Survey")
	@Story("As a User when I fill invalid email i should get an error message")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for invalid email address")
	@Description("Filling the email field with credentails that doesn't contains a domain")
	public void tc05_invalidEmail() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.fillCancelSurvey("omri", "123");

		// validation
		String expected = "Please enter a valid email address.";
		String actual = cs.getInvalidEmailMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Filling the Cancellation Survey")
	@Story("As a User when I trying to complete the survey without choosing cancellation reason i should get an error msessage")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for click complete button without choosing cancellation reason")
	@Description("pressing the complete button with unselected cancellation reason")
	public void tc06_notChoosingReason() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.exitSurvey();
		LoggedInPage lip = new LoggedInPage(driver);
		lip.openCancellationSurvey();
		cs = new CancellationSurveyPage(driver);
		cs.fillSurveyWithoutReason("omri.pointer@gmail.com");

		// validation
		String expected = "Fill in all required fields.";
		String actual = cs.getNoSelectReasonErrMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Filling the Cancellation Survey")
	@Story("When the user pressing sevral cancellation reasons, every click should switch the marked reason")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for switching selected reason")
	@Description("Clicking all the cancellation reason")
	public void tc07_switchingReasons() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.surveySwitchReasons("omri.pointer@gmail.com");

		// validation
		String expected = "Your cancellation\n" + "has been completed.";
		String actual = cs.getSuccessMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Switching Views")
	@Story("Clicking the mobile icon should change the page's view to mobile view")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "switch to mobile view test")
	@Description("Test for switching to mobile view")
	public void tc08_switchToMobileView() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.switchToMobileView();
	}

	@Feature("Switching Views")
	@Story("Clicking the PC screen icon should change the page's view to desktop view")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "switch to desktop view test")
	@Description("Test for switching to desktop view")
	public void tc09_switchToDesktopView() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.switchToDesktopView();
	}

	@Feature("Navigating")
	@Story("When the user pressing the exit link, the result should be page navgiation back to templates")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "test for exit the survey")
	@Description("Test for exiting the 'Cancellation Survey' page by pressing the exit link")
	public void tc10_exitSurvey() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.exitSurvey();

		// validation
		LoggedInPage lip = new LoggedInPage(driver);
		String expected = "Templates";
		String actual = lip.getTemplateHeadline();
		Assert.assertEquals(actual, expected);
	}
}
