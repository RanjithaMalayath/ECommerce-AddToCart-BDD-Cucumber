package amazon.cart.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import amazon.cart.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".a-size-medium.a-color-base.sc-product-price")
	List<WebElement> cartProductsPrice;

	@FindBy(xpath = "//span[contains(@class,'sc-product-price')]")
	List<WebElement> cartTotalProducts;

	@FindBy(xpath = "//span[@id='sc-subtotal-amount-buybox']/span")
	WebElement subTot;

	public int getCartProductsPrice() {
		int cartProdPrice = 0;
		for (WebElement cartProductPrice : cartProductsPrice) {
			String cartItemprice = cartProductPrice.getText().trim();
			cartProdPrice = formatPrice(cartItemprice);
		}
		return cartProdPrice;
	}

	public int getCartSubTotal() {

		int subTotal = 0;
		for (WebElement cartProductPrice : cartProductsPrice) {
			String cartItemprice = cartProductPrice.getText().trim();
			int cartProdPrice = formatPrice(cartItemprice);
			subTotal += cartProdPrice;
		}
		return subTotal;

	}

	public int getSubTotal() {
		String tot = subTot.getText();
		int tott = formatPrice(tot);
		return tott;
	}

	public int calculateTotal() {
		int sum = 0;

		for (WebElement cartProduct : cartTotalProducts) {
			String cartPriceText = cartProduct.getText();
			int cartPrice = formatPrice(cartPriceText);
			sum = sum + cartPrice;
		}

		return sum;
	}
}
