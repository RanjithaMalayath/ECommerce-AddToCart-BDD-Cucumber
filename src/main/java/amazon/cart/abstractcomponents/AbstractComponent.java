package amazon.cart.abstractcomponents;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;

	}

	public void waitForElementToAppear(By productPrices) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(productPrices));

	}

	public void waitForElementToBeClickable(By clickable) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.elementToBeClickable(clickable));

	}

	public void switchToChildWindow() {
		Set<String> handles = driver.getWindowHandles();
		String parentHandle = driver.getWindowHandle();

		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public int formatPrice(String productPrice) {
		productPrice = productPrice.trim().replaceAll(",", "");
		productPrice = productPrice.replaceAll("\\.\\d{2}", "");
		int prodPrice = Integer.parseInt(productPrice);
		return prodPrice;
	}

}
