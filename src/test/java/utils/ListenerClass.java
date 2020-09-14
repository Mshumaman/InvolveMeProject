package utils;

import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import org.openqa.selenium.WebDriver;

public class ListenerClass extends TestListenerAdapter {

	@Override
	public void onTestFailure(ITestResult result) {
		Object webDriverAttribute = result.getTestContext().getAttribute("WebDriver");
		if (webDriverAttribute instanceof WebDriver) {
			AllureAttachment.attachScreenshot((WebDriver) webDriverAttribute);
			if (webDriverAttribute instanceof WebDriver) {
				AllureAttachment.getPageSource((WebDriver) webDriverAttribute);
			}
		}
	}
}
