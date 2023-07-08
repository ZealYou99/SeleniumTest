import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class WebSelenium1 {
    WebDriver driver = new ChromeDriver();
    @Test
    public void test() throws InterruptedException, IOException {

        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        WebElement box = driver.findElement(By.id("login_credentials"));
        String allUsernames = box.getText();
        String[] lines = allUsernames.split("\n"); // usernames 1-4 working
        List<String> usernames = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            usernames.add(lines[i + 1]);
        }
        box = driver.findElement(By.className("login_password"));
        lines = box.getText().split("\n");
        String password = lines[1];
        box = driver.findElement(By.id("user-name"));
        box.sendKeys(usernames.get(0));
        box = driver.findElement(By.id("password"));
        box.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        box = driver.findElement(By.xpath("//input[@id='login-button']"));
        box.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("shopping_cart_container")));
        List<WebElement> buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        for (int i = 0; i < 6; i++) {
            buttonsAddCart.get(i).click();
        }
        List<WebElement> buttonsRemoveCart = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        for (int i = 0; i < 6; i++) {
            buttonsRemoveCart.get(i).click();
        }
        buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        buttonsAddCart.get(0).click();
        Assert.assertTrue("Czy pojawia sie czerwony element z liczba", driver.findElement(By.cssSelector("span.shopping_cart_badge")).isDisplayed());
        WebElement redBadgeCart = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        for (int i = 0; i < 5; i++) {
            buttonsAddCart.get(i + 1).click();
            Assert.assertEquals(i + 2 + "", redBadgeCart.getText());
        }
        buttonsRemoveCart = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        for (int i = 0; i < 6; i++) {
            buttonsRemoveCart.get(i).click();
        }
        //klikanie po filtrze z do a
        changeSelect("za");
        buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        buttonsAddCart.get(0).click();
        Assert.assertTrue("Czy pojawia sie czerwony element z liczba", driver.findElement(By.cssSelector("span.shopping_cart_badge")).isDisplayed());
        redBadgeCart = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        for (int i = 0; i < 5; i++) {
            buttonsAddCart.get(i + 1).click();
            Assert.assertEquals(i + 2 + "", redBadgeCart.getText());
        }
        buttonsRemoveCart = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        for (int i = 0; i < 6; i++) {
            buttonsRemoveCart.get(i).click();
        }
        //klikanie po filtrze cen
        changeSelect("lohi");
        buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        buttonsAddCart.get(0).click();
        Assert.assertTrue("Czy pojawia sie czerwony element z liczba", driver.findElement(By.cssSelector("span.shopping_cart_badge")).isDisplayed());
        redBadgeCart = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        for (int i = 0; i < 5; i++) {
            buttonsAddCart.get(i + 1).click();
            Assert.assertEquals(i + 2 + "", redBadgeCart.getText());
        }
        buttonsRemoveCart = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        for (int i = 0; i < 6; i++) {
            buttonsRemoveCart.get(i).click();
        }
        //klikanie po filtrze cen
        changeSelect("hilo");
        buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        buttonsAddCart.get(0).click();
        Assert.assertTrue("Czy pojawia sie czerwony element z liczba", driver.findElement(By.cssSelector("span.shopping_cart_badge")).isDisplayed());
        redBadgeCart = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        for (int i = 0; i < 5; i++) {
            buttonsAddCart.get(i + 1).click();
            Assert.assertEquals(i + 2 + "", redBadgeCart.getText());
        }
        buttonsRemoveCart = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        for (int i = 0; i < 6; i++) {
            buttonsRemoveCart.get(i).click();
        }
        //powrot do az, dodanie kilku produktów, sprawdzanie koszyka
        changeSelect("az");
        buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        List<Integer> products = new ArrayList<>(List.of(1, 3, 5));
        for (int product : products) {
            buttonsAddCart.get(product).click();
        }
        WebElement shoppingCart = driver.findElement(By.cssSelector("a.shopping_cart_link")); //wejscie do koszyka
        shoppingCart.click();
        List<WebElement> productsTags = driver.findElements(By.cssSelector("div.cart_item"));
        Assert.assertEquals(products.size(), productsTags.size());
        box = driver.findElement(By.cssSelector("button.btn.btn_secondary.back.btn_medium"));
        box.click();
        buttonsRemoveCart = driver.findElements(By.cssSelector("button.btn.btn_secondary.btn_small.btn_inventory"));
        Assert.assertEquals(products.size(), buttonsRemoveCart.size());
        buttonsAddCart = driver.findElements(By.cssSelector("button.btn.btn_primary.btn_small.btn_inventory"));
        int xd = 0;
        for (WebElement webElement : buttonsAddCart) {
            webElement.click();
            xd++;
        }
        shoppingCart = driver.findElement(By.cssSelector("a.shopping_cart_link")); //wejscie do koszyka
        shoppingCart.click();
        productsTags = driver.findElements(By.cssSelector("div.cart_item"));
        Assert.assertEquals(products.size() + xd, productsTags.size());
        box = driver.findElement(By.cssSelector("button.btn.btn_action.btn_medium.checkout_button"));
        box.click();
        File file = new File("InfoForSauceTest.txt");
        BufferedReader readFromFile = new BufferedReader(new FileReader(file));
        String name = readFromFile.readLine();
        String surName = readFromFile.readLine();
        String zipCode = readFromFile.readLine();
        List<WebElement> inputs = driver.findElements(By.cssSelector("input.input_error.form_input"));
        inputs.get(0).sendKeys(name);
        inputs.get(1).sendKeys(surName);
        inputs.get(2).sendKeys(zipCode);
        box = driver.findElement(By.cssSelector("input.submit-button.btn.btn_primary.cart_button.btn_action"));
        box.click();
        List<WebElement> pricesAsWeb = driver.findElements(By.cssSelector("div.inventory_item_price"));
        double number;
        double finalPrice = 0;
        for (int i = 0; i < pricesAsWeb.size(); i++) {
            number = Double.parseDouble(pricesAsWeb.get(i).getText().substring(1));
            finalPrice += number;
        }
        //czek czy cena sie zgadza
        finalPrice += Double.parseDouble(driver.findElement(By.cssSelector("div.summary_tax_label")).getText().substring(6));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.summary_info_label.summary_total_label")).getText().substring(8), Double.toString(finalPrice));
        //konczenie zamowienia
        driver.findElement(By.cssSelector("button.btn.btn_action.btn_medium.cart_button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.pony_express")));
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        //koniec czesci 1
        //sprawdzenie locked usera
        box = driver.findElement(By.id("user-name"));
        box.sendKeys(usernames.get(1));
        Thread.sleep(3000);
        box = driver.findElement(By.id("password"));
        box.sendKeys(password);
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        box = driver.findElement(By.xpath("//input[@id='login-button']"));
        box.click();

        //scrapper
        driver.navigate().refresh();
        driver.findElement(By.id("user-name")).sendKeys(usernames.get(0));
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("login-button")).click();
        List<Product> stolenProducts = new LinkedList<>();
        List<WebElement> boxes = driver.findElements(By.cssSelector("div.inventory_item"));
        for (int i = 0; i < boxes.size(); i++) {
            stolenProducts.add(new Product(boxes.get(i).findElement(By.cssSelector("div.inventory_item_name")).getText(), boxes.get(i).findElement(By.cssSelector("div.inventory_item_desc")).getText(), boxes.get(i).findElement(By.cssSelector("div.inventory_item_price")).getText(), boxes.get(i).findElement(By.tagName("img")).getAttribute("src")));
        }
        System.out.println(stolenProducts.get(0));
        //koniec programu
        driver.quit();

    }
    private void changeSelect(String value){
        Select select = new Select(driver.findElement(By.cssSelector("select.product_sort_container"));
        select.selectByValue(value);
    }
    private void redBadgeAppear(){};
}
