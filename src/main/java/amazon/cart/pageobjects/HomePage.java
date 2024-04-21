package amazon.cart.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.cart.abstractcomponents.AbstractComponent;

public class HomePage extends AbstractComponent {

	WebDriver driver;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "twotabsearchtextbox")
	WebElement searchBox;

	public ProductCatalogPage searchProduct(String productName) {
		searchBox.sendKeys(productName + Keys.ENTER);
		ProductCatalogPage productPage = new ProductCatalogPage(driver);
		return productPage;
	}

	public void goTo(String url) {
		driver.get(url);
	}

}
