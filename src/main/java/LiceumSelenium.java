import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class LiceumSelenium {
    @Test
    public void loTest() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.x-kom.pl");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebElement box1 = driver.findElement(By.cssSelector("button.sc-15ih3hi-0.sc-1p1bjrl-9.dRLEBj"));
        box1.click();
        List<WebElement> boxList = driver.findElements(By.cssSelector("div.sc-13hctwf-5.bdvNWx"));
        for (int i = 0; i < 8; i++) {
            boxList.get(i).click();
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(2000));
        box1 = driver.findElement(By.cssSelector("div.sc-13hctwf-5.csZKHr"));
        box1.click();
        box1 = driver.findElement(By.cssSelector("a.sc-1h16fat-0.dNrrmO.sc-joe0ba-0.jDCGMF"));
        box1.click();
        boxList.clear();
        boxList = driver.findElements(By.cssSelector("span[data-name='productPrice']"));
        System.out.println(boxList.get(0).getText()); //that way we can get price of every product on loaded site
        box1 = driver.findElement(By.cssSelector("span.sc-3qnozx-5.cTHYmg"));
        box1.click();
        boxList.clear();
        boxList = driver.findElements(By.cssSelector("input.sc-1s2eiz4-0.ezdavF.sc-13rzjau-2.sc-13rzjau-4.kluCfr"));
        boxList.get(0).sendKeys("3300");
        boxList.get(1).sendKeys("3350");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        box1 = driver.findElement(By.cssSelector("button.sc-15ih3hi-0.sc-1j3ie3s-1.hmHJCy.sc-1yu46qn-1.jSIfzG"));
        box1.click();
        //zjebana strona dla podludzi z z attentionspanem <1 sekunda jebac ich okok
    }
}
