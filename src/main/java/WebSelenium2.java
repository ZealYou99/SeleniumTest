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
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        SoftAssert softAssert = new SoftAssert();
        String wantedSelect, afterSelect;
        Select select;
        List<WebElement> options = driver.findElements(By.tagName("option"));
        for (int i = 1; i < options.size(); i++) {
            select = new Select(driver.findElement(By.cssSelector("select.product_sort_container")));
            select.selectByIndex(i);
            options = driver.findElements(By.tagName("option"));
            wantedSelect = options.get(i).getText();
            select = new Select(driver.findElement(By.cssSelector("select.product_sort_container")));
            afterSelect = select.getFirstSelectedOption().getText();
            softAssert.assertEquals(afterSelect, wantedSelect);
        }
        softAssert.assertAll("Czy wszystkie selecty dzialaja");
        driver.quit();
    }

    //Sprawdzenie czy ceny sie zgadzaja z produktami
    @Test
    public void secondTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        List<WebElement> products = driver.findElements(By.cssSelector("div.inventory_item"));
        SoftAssert softAssert = new SoftAssert();
        String price;
        for (int i = 0; i < products.size(); i++) {
            products = driver.findElements(By.cssSelector("div.inventory_item"));
            price = products.get(i).findElement(By.cssSelector("div.inventory_item_price")).getText().substring(1);
            products.get(i).findElement(By.tagName("a")).click();
            softAssert.assertEquals(driver.findElement(By.cssSelector("div.inventory_details_price")).getText().substring(1),price);
            driver.navigate().back();

        }
        softAssert.assertAll();
        driver.quit();
    }

    //Sprawdzenie czy ceny są liczbami wymiernymi dodatnimi
    @Test
    public void thirdTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        List<WebElement> products = driver.findElements(By.cssSelector("div.inventory_item"));
        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < products.size(); i++) {
            products = driver.findElements(By.cssSelector("div.inventory_item"));
            //sprawdzenie cen na stronie glownej
            softAssert.assertTrue(NumberUtils.isCreatable(products.get(i).findElement(By.cssSelector("div.inventory_item_price")).getText().substring(1)));
            products.get(i).findElement(By.tagName("a")).click();
            softAssert.assertTrue(NumberUtils.isCreatable(driver.findElement(By.cssSelector("div.inventory_details_price")).getText().substring(1)));
            driver.navigate().back();
        }
        softAssert.assertAll();
        driver.quit();
    }

    //Sprawdzenie czy przyciski dzialaja
    @Test
    public void fourthTest() {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        List<WebElement> buttons = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
        }
        buttons = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        Assert.assertTrue(buttons.isEmpty());
        driver.quit();
    }
    //
    @Test
    public void fifthTest() {

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        List<WebElement> buttons = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
        }
        buttons = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).click();
        }
        buttons =  driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        Assert.assertTrue(buttons.isEmpty());
        driver.quit();
    }
    //Sprawdzenie czy imputy dzialaja
    @Test
    public void sixthTest()
    {
        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        driver.findElement(By.cssSelector("a.shopping_cart_link")).click();
        driver.findElement(By.cssSelector("button.btn.btn_action.btn_medium.checkout_button")).click();
        SoftAssert softAssert = new SoftAssert();
        List<WebElement> inputs = driver.findElements(By.cssSelector("input.input_error.form_input"));
        String test = "Test123#XD$";
        for (int i = 0; i < inputs.size(); i++) {
            inputs.get(i).sendKeys(test);
            softAssert.assertEquals(inputs.get(i).getAttribute("value"),test);
        }
        softAssert.assertAll();
        driver.quit();
    }

}
