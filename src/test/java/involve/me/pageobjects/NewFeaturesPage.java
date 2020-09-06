package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NewFeaturesPage extends BasePage {
	@FindBy(css = ".auto-container > h1")
	private WebElement headlineMsg;

	public NewFeaturesPage(WebDriver driver) {
		super(driver);
	}

	public String getHeadlineMsg() {
		return getText(headlineMsg);

	}
}
