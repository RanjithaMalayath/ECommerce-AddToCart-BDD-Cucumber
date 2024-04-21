package amazon.cart.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.cart.abstractcomponents.AbstractComponent;

public class ProductCatalogPage extends AbstractComponent {

	WebDriver driver;

	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".a-size-medium.a-color-base.a-text-normal")
	List<WebElement> products;

	@FindBy(xpath = "(//span[@class='a-price-whole'])")
	WebElement proPrice;

	By productsBy = By.cssSelector(".a-color-state.a-text-bold");

	// By addToCartBy = By.xpath("//span[@class='a-button-text' and text()='Add to
	// Cart']");

	// By addedMessageBy =
	// By.xpath("//div[@id='attachDisplayAddBaseAlert']//h4[@class='a-alert-heading'
	// and text()='Added to Cart']");

	// By skipButtonBy = By.id("attachSiNoCoverage");

	@FindBy(linkText = "Go to Cart")
	WebElement cartButton1;

	@FindBy(css = "#attach-sidesheet-view-cart-button")
	WebElement cartButton2;

	@FindBy(xpath = "//div[@id='NATC_SMART_WAGON_CONF_MSG_SUCCESS']/h1")

	WebElement addedToCartMessage1;

	@FindBy(xpath = "//div[@id='attachDisplayAddBaseAlert']//h4[@class='a-alert-heading' and text()='Added to Cart']")

	WebElement addedToCartMessage2;

	@FindBy(xpath = "(//span[@id='submit.add-to-cart'])[1]")

	WebElement addToCartButton;

	@FindBy(xpath = "//span[@id='attachSiNoCoverage']")

	WebElement skipButton;

	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public void selectProductByIndex(int index) {

		WebElement nthItem = getProductList().stream().skip(index - 1).findFirst().orElse(null);
		if (nthItem != null) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nthItem);
			nthItem.click();
		} else {
			System.out.println("Product at index " + index + " not found.");
		}
		switchToChildWindow();
	}

	public WebElement getProductPriceElementByPosition(int position) {
		String xpath = "(//div[@class='a-section a-spacing-small a-spacing-top-small'])[" + position
				+ "] //span[@class='a-price-whole']";
		return driver.findElement(By.xpath(xpath));
	}

	public WebElement addToCartByPosition(int position) {
		String xpath = "(//span[@id='submit.add-to-cart'])[" + position + "]";
		return driver.findElement(By.xpath(xpath));
	}

	public int getProductPrice(int position) throws InterruptedException {

		WebElement proPriceElement = getProductPriceElementByPosition(position);
		String productPrice = proPriceElement.getText();
		int prodPrice = formatPrice(productPrice);
		return prodPrice;
	}

	public void addProductToCart(int position) throws InterruptedException {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollBy(0,500);");
		WebElement addToCartButton = addToCartByPosition(position);
		addToCartButton.click();

		// Thread.sleep(3000);

		try {

			if (skipButton.isDisplayed()) {

				skipButton.click();
			}
		} catch (NoSuchElementException e) {

		}
	}

	public String getProductAddedToCartText() {

		try {
			return addedToCartMessage1.getText();
		} catch (Exception e) {

			try {
				return addedToCartMessage2.getText();
			} catch (Exception ex) {

				return null;
			}
		}
	}

	public CartPage goToCart() {

		try {
			cartButton1.click();
		} catch (Exception e) {

			try {
				cartButton2.click();
			} catch (Exception ex) {

			}
		}

		CartPage cart = new CartPage(driver);
		return cart;
	}
}
