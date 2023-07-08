import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class WebSelenium2 {
    //Testowanie glitch usera z tej strony https://www.saucedemo.com/
    //duzo testów krotkich testow zeby przetestwoac innych uzytko trzea zmienic stringa okok
    //testy maja z zalozenia failowac za kazdym razem dla tego problem usera
    // a dzialac normalnie
    public static String username = "standard_user"; // podaj z tej strony idgaf standard_user , problem_user
    public static String password = "secret_sauce";
    WebDriver driver = new ChromeDriver();

    //Sprawdzenie Selecta czy dziala A do Z, Z do A, Low to High, High to Low
    @Test
    public void firstTest() throws InterruptedException {
        logIn();
        List<WebElement> options = driver.findElements(By.tagName("option"));
        for (int i = 0; i < options.size(); i++) {
            changeSelect("select.product_sort_container", i);
            verifySelect("option", "select.product_sort_container", i);
        }
        closeSite();
    }


    //Sprawdzenie czy ceny sie zgadzaja z produktami
    @Test
    public void secondTest() {
        logIn();
        List<WebElement> products;
        for (int i = 0; i < getForLength("div.inventory_item"); i++) {
            products = driver.findElements(By.cssSelector("div.inventory_item"));
            verifyPrice(products.get(i));
            back();
        }
        closeSite();
    }

    //Sprawdzenie czy ceny są liczbami wymiernymi dodatnimi
    @Test
    public void thirdTest() {
        logIn();
        List<WebElement> products;
        for (int i = 0; i < getForLength("div.inventory_item"); i++) {
            products = driver.findElements(By.cssSelector("div.inventory_item"));
            verifyBothPricesAreNumeric(products.get(i));
            back();
        }
        closeSite();
    }

    //Sprawdzenie czy przyciski add to cart dzialaja
    @Test
    public void fourthTest() {
        logIn();
        clickButtons("button.btn.btn_primary.btn_small.btn_inventory");
        verifyButtonsClicked("button.btn.btn_primary.btn_small.btn_inventory");
        closeSite();
    }

    //Sprawdzenie czy przyciski remove dzialaja
    @Test
    public void fifthTest() {
        logIn();
        clickButtons("button.btn.btn_primary.btn_small.btn_inventory");
        clickButtons("button.btn.btn_secondary.btn_small.btn_inventory");
        verifyButtonsClicked("button.btn.btn_secondary.btn_small.btn_inventory");
        closeSite();
    }

    //Sprawdzenie czy imputy dzialaja
    @Test
    public void sixthTest() {
        logIn();
        toCheckout();
        findAndFillInputs("input.input_error.form_input");
        verifyInputs("input.input_error.form_input");
        closeSite();
    }

    private void clickButtons(String css) {
        List<WebElement> buttons = driver.findElements(By.cssSelector(css));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
        }
    }

    private void verifyButtonsClicked(String css) {
        Assert.assertTrue(driver.findElements(By.cssSelector(css)).isEmpty());
    }

    private void verifyBothPricesAreNumeric(WebElement webElement) {
        Assert.assertTrue(NumberUtils.isCreatable(webElement.findElement(By.cssSelector("div.inventory_item_price")).getText().substring(1)));
        webElement.findElement(By.tagName("a")).click();
        Assert.assertTrue(NumberUtils.isCreatable(driver.findElement(By.cssSelector("div.inventory_details_price")).getText().substring(1)));
    }

    private void verifyPrice(WebElement webElement) {

        String price = webElement.findElement(By.cssSelector("div.inventory_item_price")).getText().substring(1);
        webElement.findElement(By.tagName("a")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText().substring(1), price);
    }

    private void changeSelect(String css, int iterator) {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        select.selectByIndex(iterator);
    }

    private void verifySelect(String tag, String css, int iterator) {
        Select select = new Select(driver.findElement(By.cssSelector(css)));
        List<WebElement> options = driver.findElements(By.tagName(tag));
        Assert.assertEquals(select.getFirstSelectedOption().getText(), options.get(iterator).getText());
    }

    private void verifyInputs(String css) {
        List<WebElement> inputs = driver.findElements(By.cssSelector(css));
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < inputs.size(); i++) {
            softAssert.assertEquals(inputs.get(i).getAttribute("value"), "Test123@#_+;'{}");
        }
        softAssert.assertAll();
    }

    private void findAndFillInputs(String css) {
        List<WebElement> inputs = driver.findElements(By.cssSelector(css));
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).sendKeys("Test123@#_+;'{}");
        }
    }

    private void closeSite() {
        driver.quit();
    }

    private void toCheckout() {
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        driver.findElement(By.cssSelector("button.btn.btn_action.btn_medium.checkout_button")).click();
    }

    private void logIn() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
    }

    private void back() {
        driver.navigate().back();
    }

    private int getForLength(String css) {
        return driver.findElements(By.cssSelector(css)).size();
    }
}
