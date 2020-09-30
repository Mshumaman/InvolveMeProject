package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import utils.AllureAttachment;

public class NewProjectPage extends BasePage {

	@FindBy(css = "#project-name")
	private WebElement projectNameField;
	@FindBy(css = ".swal-button.swal-button")
	private WebElement startEditBtn;
	@FindBy(css = ".c-menu-headline.content-menu-item")
	private WebElement headlineElement;
	@FindBy(css = ".shadow.empty_canvas")
	private WebElement mainCanvas;
	@FindBy(css = ".e-close.nav-link")
	private WebElement saveAndExit;
	@FindBy(css = "#accordeon-headline-text textarea")
	private WebElement headlineTextbox;
	@FindBy(css = "#accordeon-subheadline-text textarea")
	private WebElement sublineTextbox;
	@FindBy(css = ".content-item-edit-close button")
	private WebElement saveChanges;
	@FindBy(css = ".nav-link.project-name")
	private WebElement projectName;
	@FindBy(css = "#project-name-error")
	private WebElement projectNameErrMsg;
	@FindBy(css = ".fal.fa-plus")
	private WebElement newPageBtn;
	@FindBy(css = ".current .js-remove-page >img")
	private WebElement deletenewPage;
	@FindBy(css = ".e-page-item.current")
	private WebElement numOfPages;
	@FindBy(css = ".swal-button--confirm")
	private WebElement confirmDeletePageBtn;

	public NewProjectPage(WebDriver driver) {
		super(driver);
	}

	public void fillProjectForm(String name) {
		fillText(projectNameField, name);
		AllureAttachment.attachElementScreenshot(startEditBtn);
		click(startEditBtn);
	}

	public void dragAndDropHeadline() {
		dragAndDrop(headlineElement, mainCanvas);
	}

	public void saveAndExitProject() {
		AllureAttachment.attachElementScreenshot(saveAndExit);
		click(saveAndExit);
	}

	public void fillHeadlineTextboxes(String headline, String subline) {
		fillText(headlineTextbox, headline);
		fillText(sublineTextbox, subline);
		click(saveChanges);
	}

	public void addNewPage() {
		AllureAttachment.attachElementScreenshot(newPageBtn);
		click(newPageBtn);
		wait.until(ExpectedConditions.elementToBeClickable(deletenewPage));
	}

	public void deleteNewPage() {
		click(deletenewPage);
		AllureAttachment.attachElementScreenshot(confirmDeletePageBtn);
		click(confirmDeletePageBtn);
		wait.until(ExpectedConditions.elementToBeClickable(newPageBtn));
	}

	// validation
	public String getProjectName() {
		return getText(projectName);
	}

	public String getProjectNameErrMsg() {
		return getText(projectNameErrMsg);
	}

	public String getNumOfPages() {
		return getAttribute(numOfPages, "pagecount");
	}

}
