package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.CancellationSurveyPage;
import involve.me.pageobjects.LoggedInPage;

public class CancellationSurveyTest extends BaseTest {

	/*
	 * In this test case i will test filling the cancellation form e2e successfully
	 */
	@Test(description = "cancellation survey page test")
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

	@Test(description = "test for filling all info in cancellation survey")
	public void tc02_fillCancelSurvey() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.fillCancelSurvey("omri.pointer@gmail.com", "testing 1,2,3");

		// validation
		String expected = "Your cancellation\n" + "has been completed.";
		String actual = cs.getSuccessMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for going back to survey after survey completed")
	public void tc03_goBackToSurvey() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.goBackBtn();

		// validation
		String expected = "What's the reason for your cancellation?";
		String actual = cs.getCancelReasonMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "click complete with empty fields test")
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

	@Test(description = "test for invalid email address")
	public void tc05_invalidEmail() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.fillCancelSurvey("omri", "123");

		// validation
		String expected = "Please enter a valid email address.";
		String actual = cs.getInvalidEmailMsg();
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "test for click complete button without choosing cancellation reason")
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

	@Test(description = "test for switching selected reason")
	public void tc07_switchingReasons() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.surveySwitchReasons("omri.pointer@gmail.com");

		// validation
		String expected = "Your cancellation\n" + "has been completed.";
		String actual = cs.getSuccessMsg();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "switch to desktop view test")
	public void tc09_switchToDesktopView() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.switchToDesktopView();
	}

	@Test(description = "switch to mobile view test")
	public void tc08_switchToMobileView() {
		CancellationSurveyPage cs = new CancellationSurveyPage(driver);
		cs.switchToMobileView();
	}

	@Test(description = "test for exit the survey")
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
