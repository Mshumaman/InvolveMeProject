package involve.me.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/*
 * In this class I have XXX the selenium methods with my methods
 * This give me the ability to do highlight for all events in the automation project
 * and decrease duplication of code.
 */
public class BasePage {
	WebDriver driver;
	Actions actions;
	JavascriptExecutor js;
	WebDriverWait wait;

	@SuppressWarnings("deprecation")
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;
		wait = new WebDriverWait(driver, 15);
	}

	public void click(WebElement el) {
		highlightElement(el, "blue", "orange");
		el.click();
	}

	public void fillText(WebElement el, String text) {
		highlightElement(el, "green", "yellow");
		el.clear();
		el.sendKeys(text);
	}

	public void clearText(WebElement el) {
		el.clear();
	}

	public String getText(WebElement el) {
		highlightElement(el, "red", "cyan");
		return el.getText();
	}

	public String getAttribute(WebElement el, String attribute) {
		return el.getAttribute(attribute);
	}

	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void alertOK(String Text) {
		driver.switchTo().alert().sendKeys(Text);
		driver.switchTo().alert().accept();
	}

	public void noTextAlertOK() {
		driver.switchTo().alert().accept();
	}
	// mouse options

	public void moveTo(WebElement el) {
		actions.moveToElement(el).build().perform();
	}

	public void dragAndDrop(WebElement el, WebElement el2) {
		actions.dragAndDrop(el, el2).build().perform();
	}

	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 */
	private void highlightElement(WebElement element, String borderColor, String backgroundColor) {
		// keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color: " + backgroundColor + ";border: 1px solid " + borderColor + ";"
				+ originalStyle;
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Change the style
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ newStyle + "');},0);", element);

		// Change the style back after few miliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}
}
