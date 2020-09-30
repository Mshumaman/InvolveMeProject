package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.AllureAttachment;

public class LoggedInPage extends BasePage {
	@FindBy(css = ".fas.fa-caret-down.ml-2")
	private WebElement openUserDropDownMenu;
	@FindBy(linkText = "Log Out")
	private WebElement logoutBtn;
	@FindBy(linkText = "Templates")
	private WebElement templates;
	@FindBy(css = "#filter-survey")
	private WebElement surveyCategory;
	@FindBy(css = "[alt='Cancellation Survey']")
	private WebElement cancelationSurvey;
	@FindBy(css = ".col-md-12.c-list-header .e-title")
	private WebElement templatesHeader;
	@FindBy(css = "li:nth-child(2) > a")
	private WebElement yourAccount;
	@FindBy(css = ".pb-24> .alert")
	private WebElement confirmEmailMsg;
	@FindBy(css = ".px-3.py-2.rounded ")
	private WebElement newProjectBtn;
	@FindBy(css = ".blank-center .title")
	private WebElement blankCanvas;
	@FindBy(css = "div:nth-child(1) > div.absolute.right-0.left-0.mt-8.mx-3.z-100.flex-1 button > svg")
	private WebElement newProjectDropdownMenu;
	@FindBy(css = ":nth-child(1) > div.absolute li:nth-child(8) > button")
	private WebElement deleteNewProject;
	@FindBy(css = "#confirm-delete-button")
	private WebElement deleteProjectConfirmBtn;
	@FindBy(css = ".px-5>button")
	private WebElement newWorkspacePlusBtn;
	@FindBy(css = ".p-6>input")
	private WebElement newWorkspaceField;
	@FindBy(css = "#confirm-create-button")
	private WebElement createNewWorkspaceBtn; // also use to delete workspace
	@FindBy(css = ".flex-grow.items-center >h1")
	private WebElement workspaceNameHeader;
	@FindBy(css = ".relative.mr-3>button")
	private WebElement workplaceDropdownMenu;
	@FindBy(css = ".hover\\:bg-red-600")
	private WebElement deleteWorkspace;
	@FindBy(css = ".p-6 >input")
	private WebElement deleteWorkspaceName;
	@FindBy(css = ".text-purple-600")
	private WebElement numOfProjects;
	@FindBy(css = ".c-button.btn")
	private WebElement cancellationFormCompleteBtn;
	@FindBy(css = ".text-gray-600 .mr-3")
	private WebElement firstWorkspace;

	public LoggedInPage(WebDriver driver) {
		super(driver);
	}

	public void logout() {
		click(openUserDropDownMenu);
		wait.until(ExpectedConditions.elementToBeClickable(logoutBtn));
		click(logoutBtn);
	}

	public void openTemplates() {
		click(templates);
	}

	public void openCancellationSurvey() {
		click(surveyCategory);
		click(cancelationSurvey);
		wait.until(ExpectedConditions.elementToBeClickable(cancellationFormCompleteBtn));
	}

	public void openYourAccount() {
		click(openUserDropDownMenu);
		click(yourAccount);
	}

	public void startNewProject() {
		AllureAttachment.attachElementScreenshot(newProjectBtn);
		click(newProjectBtn);
	}

	public void chooseBlankCanvas() {
		click(blankCanvas);
	}

	public void createNewWorkspace(String name) {
		click(newWorkspacePlusBtn);
		fillText(newWorkspaceField, name);
		AllureAttachment.attachElementScreenshot(createNewWorkspaceBtn);
		click(createNewWorkspaceBtn);
		wait.until(ExpectedConditions.elementToBeClickable(firstWorkspace));
	}

	public void deleteNewProject() {
		click(newProjectDropdownMenu);
		sleep(500);
		click(deleteNewProject);
		AllureAttachment.attachElementScreenshot(deleteProjectConfirmBtn);
		click(deleteProjectConfirmBtn);
		wait.until(ExpectedConditions.elementToBeClickable(newProjectBtn));
	}

	public void deleteWorkplace(String name) {
		click(workplaceDropdownMenu);
		click(deleteWorkspace);
		fillText(deleteWorkspaceName, name);
		AllureAttachment.attachElementScreenshot(createNewWorkspaceBtn);
		click(createNewWorkspaceBtn);
		wait.until(ExpectedConditions.elementToBeClickable(newProjectBtn));
	}

	// validation
	public String getTemplateHeadline() {
		return getText(templatesHeader);
	}

	public String getEmailConfirmationMsg() {
		return getText(confirmEmailMsg);
	}

	public String getWorkspaceHeader() {
		return getText(workspaceNameHeader);
	}

	public String getNumOfProjects() {
		return getText(numOfProjects);
	}

}
