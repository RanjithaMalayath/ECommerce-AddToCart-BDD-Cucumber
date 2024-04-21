package amazon.cart.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import amazon.cart.TestComponents.BaseTest;
import amazon.cart.pageobjects.CartPage;
import amazon.cart.pageobjects.HomePage;
import amazon.cart.pageobjects.ProductCatalogPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition extends BaseTest {

	public HomePage home;
	ProductCatalogPage productPage;
	CartPage cart;
	int prodPrice;
	int cartItemPrice;

	@Given("I am on Amazon.in page")
	public void go_To_Application() throws IOException {
		home = launchApplication();
	}

	@Then("I verify expected page is loaded")
	public void verify_Title() {
		String title = getTitle();
		Assert.assertTrue(title.contains("Shop"));
	}

	@When("I type {string} in the search field and press Enter")
	public void type_Item_To_Search(String item) {
		productPage = home.searchProduct(item);
	}

	@When("^I type item (.+) in the search field and press Enter$")
	public void type_Items_To_Search(String items) {
		productPage = home.searchProduct(items);
	}

	@When("I get the price of the item by position {string}")
	public void get_Item_Price(String priceIndex) throws InterruptedException {
		int index = Integer.parseInt(priceIndex);
		prodPrice = productPage.getProductPrice(index);
	}

	@When("I select item {string} from the list")
	public void select_Item_From_List(String itemNo) {
		int index = Integer.parseInt(itemNo);
		productPage.selectProductByIndex(index);
	}

	@When("I add the item {string} to the cart by clicking on Add to Cart button")
	public void click_On_Add_To_Cart(String itemNo) throws InterruptedException {
		int index = Integer.parseInt(itemNo);
		productPage.addProductToCart(index);
	}

	@Then("I should validate {string} success message")
	public void verify_Added_To_Cart_Message(String successMessage) {
		String addedTocartText = productPage.getProductAddedToCartText();
		Assert.assertEquals(addedTocartText, successMessage);
	}

	@When("I open the cart")
	public void go_To_Cart() {
		cart = productPage.goToCart();
	}

	@Then("The price in the cart should match the price on the product page")
	public void verify_Item_Price() {
		cartItemPrice = cart.getCartProductsPrice();
		Assert.assertEquals(cartItemPrice, prodPrice);
	}

	@Then("The subtotal in the cart should match the total on the product page")
	public void verify_cart_SubTotal() {
		int sumOfCartProducts = cart.calculateTotal();
		int subTot = cart.getSubTotal();
		Assert.assertEquals(sumOfCartProducts, subTot);
		tearDown();
	}

}
