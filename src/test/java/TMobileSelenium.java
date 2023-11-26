import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TMobileSelenium {
    static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.t-mobile.com/tablets");

        setFilter("Deals", "New");
//        setFilter("Deals", "All");
        setFilter("Brands", "Alcatel", "Apple", "TCL");
//        setFilter("Brands", "All");
        setFilter("Operating System", "All");

        driver.close();
    }

    static void click(WebElement nameOfCheckbox) {
        Actions actions = new Actions(driver);
        actions.moveToElement(nameOfCheckbox).click().perform();
    }

    public static void setFilter(String filter, String... subfilters) {
        WebElement fil = wait.until(ExpectedConditions.elementToBeClickable(By
                    .xpath("//legend[contains(text(), '" + filter + "')]")));
        click(fil);

        for(String subfilter : subfilters) {
            if(subfilter.equals("All")) {
                List<WebElement> allOption = driver.findElements(By
                        .xpath("//input[@name='" + filter + "']/parent::span"));
                for(WebElement option : allOption) {
                    click(option);
                }

            } else {
                WebElement el = driver
                        .findElement(By.xpath("//mat-checkbox[@data-testid='desktop-filter-option']//span[contains(text(),'" + subfilter + "')]"));
                click(el);
            }
        }
    }
}
