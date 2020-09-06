package involve.me.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import involve.me.pageobjects.LoggedInPage;
import involve.me.pageobjects.NewProjectPage;

public class NewProjectTests extends BaseTest {

	/*
	 * In this test case, after logging in successfully, I will test creating a new blank project
	 */
	@Test(description = "test for creating new project")
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

	@Test(description = "add new project page test")
	public void tc02_addNewPage() {
		NewProjectPage npp = new NewProjectPage(driver);
		npp.addNewPage();
		
		// validation
		String expected = "3";
		String actual = npp.getNumOfPages();
		System.out.println(actual);
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "save and exit test")
	public void tc03_saveAndExit() {
		NewProjectPage npp = new NewProjectPage(driver);
		npp.saveAndExitProject();

	}

	@Test(description = "delete project test")
	public void tc04_deleteProject() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.deleteNewProject();

	}

	@Test(description = "test for creating new workspace")
	public void tc05_createNewWorkspace() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.createNewWorkspace("Automation Testing");
		
		// validation
		String expected = "Automation Testing";
		String actual = lip.getWorkspaceHeader();
		Assert.assertEquals(actual, expected);

	}

	@Test(description = "test for deleting the new workspace")
	public void tc06_deleteWorkspace() {
		LoggedInPage lip = new LoggedInPage(driver);
		lip.deleteWorkplace("Automation Testing");
		
		// validation
		String expected = "My Workspace";
		String actual = lip.getWorkspaceHeader();
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "test for creating project without a name")
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

	@Test(description = "test for creating project with name that contains 1 letter")
	public void test_08_shortNewProjectName() {
		NewProjectPage npp = new NewProjectPage(driver);
		npp.fillProjectForm("a");
		
		// validation
		String expected = "Please enter at least 3 characters.";
		String actual = npp.getProjectNameErrMsg();
		Assert.assertEquals(actual, expected);
	}

}