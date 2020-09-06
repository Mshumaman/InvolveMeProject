package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage {

	@FindBy(css = ".form-horizontal.form-register .e-form-heading")
	private WebElement registerTitle;

	public RegisterPage(WebDriver driver) {
		super(driver);
	}

	public String getRegisterTitle() {
		return getText(registerTitle);
	}
}
