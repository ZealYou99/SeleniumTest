import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.lang.model.element.Element;
import java.io.*;
import java.sql.Driver;
import java.time.Duration;
import java.util.List;
import java.util.Scanner;

public class KickSelenium {
    @Test
    public void KickTest() throws IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://kick.com");
        WebElement logInImput = driver.findElement(By.className("inner-label"));
        logInImput.click();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        File file = new File("F:\\Pass.txt");
        BufferedReader getMaillPsswrd = new BufferedReader(new FileReader(file));
        String mail = getMaillPsswrd.readLine();
        String password = getMaillPsswrd.readLine();
        List<WebElement> weLi = driver.findElements(By.id("input-element"));
        weLi.get(0).sendKeys(mail);
        weLi.get(1).sendKeys(password);
        logInImput = driver.findElement(By.cssSelector("button.variant-action.size-md[type='submit']"));
        logInImput.click();
        //cloudflare :(
    }
}
