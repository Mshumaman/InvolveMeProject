package involve.me.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
	@FindBy(css = ".other-link.login")
	private WebElement loginLink;
	@FindBy(css = ".other-link.register")
	private WebElement registerLink;
	@FindBy(css = "div.navbar-collapse.collapse.scroll-nav.clearfix > ul > li:nth-child(5)")
	private WebElement resourcesDropMenu;


	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void openLogin() {
		click(loginLink);
	}

	public void openRegister() {
		click(registerLink);
	}

}
