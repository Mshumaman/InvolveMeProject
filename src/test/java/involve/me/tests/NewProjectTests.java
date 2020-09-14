package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.LoggedInPage;
import involve.me.pageobjects.NewProjectPage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("New Project")
public class NewProjectTests extends BaseTest {

	/*
	 * In this test case, after logging in successfully, I will test creating a new
	 * blank project
	 */
	@Feature("Creating New Project")
	@Story("As a User when I create a new blank project successfully I expect to be in my new project page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "test for creating new project")
	@Description("E2E test for creating a new project")
	public void tc01_createNewProject() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.startNewProject();
		lip.chooseBlankCanvas();
		NewProjectPage npp = new NewProjectPage(driver);
		npp.fillProjectForm("My Automation Test Project");

		// validation
		String expected = "MY AUTOMATION TEST PROJECT";
		String actual = npp.getProjectName();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Creating New Project")
	@Story("As a User when I'm pressing the '+' sign in order to add page, the page should be added")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "add new project page test")
	@Description("Test for adding a page to the project")
	public void tc02_addNewPage() {
		NewProjectPage npp = new NewProjectPage(driver);
		npp.addNewPage();

		// validation
		String expected = "3";
		String actual = npp.getNumOfPages();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);
	}

	@Feature("Creating New Project")
	@Story("As a User when I pressing the 'Save & Exit' The project should be saved and navigated back the projects page")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "save and exit test")
	@Description("Test for saving the project and exiting")
	public void tc03_saveAndExit() {
		NewProjectPage npp = new NewProjectPage(driver);
		npp.saveAndExitProject();
	}

	@Feature("Delete New Project")
	@Story("As a User when I press delete project it should be deleted")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "delete project test")
	@Description("Test for deleting the project that was created")
	public void tc04_deleteProject() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.deleteNewProject();
	}

	@Feature("Workspace")
	@Story("When i create a new workspace it should appear and selected")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for creating new workspace")
	@Description("Creating a new 'Workspace'")
	public void tc05_createNewWorkspace() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.createNewWorkspace("Automation Testing");

		// validation
		String expected = "Automation Testing";
		String actual = lip.getWorkspaceHeader();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Workspace")
	@Story("When i delete a workspace it should be delted from the workspaces list")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for deleting the new workspace")
	@Description("Deleting the new 'Workspace'")
	public void tc06_deleteWorkspace() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.deleteWorkplace("Automation Testing");

		// validation
		String expected = "My Workspace";
		String actual = lip.getWorkspaceHeader();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Creating New Project")
	@Story("As a User when I try to create a project without a name, an error message should appear")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for creating project with empty name field")
	@Description("Test for creating a project without a name")
	public void tc07_emptyNewProjectField() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.startNewProject();
		lip.chooseBlankCanvas();
		NewProjectPage npp = new NewProjectPage(driver);
		npp.fillProjectForm("");

		// validation
		String expected = "This field is required.";
		String actual = npp.getProjectNameErrMsg();
		Assert.assertEquals(actual, expected);
	}

	@Feature("Creating New Project")
	@Story("As a User when I try to create a project with invalid name, an error message should appear")
	@Severity(SeverityLevel.NORMAL)
	@Test(description = "test for creating project with invalid name")
	@Description("Test for creating a project without a name that contains 1 letter")
	public void test_08_shortNewProjectName() {
		NewProjectPage npp = new NewProjectPage(driver);
		npp.fillProjectForm("a");

		// validation
		String expected = "Please enter at least 3 characters.";
		String actual = npp.getProjectNameErrMsg();
		Assert.assertEquals(actual, expected);
	}

}