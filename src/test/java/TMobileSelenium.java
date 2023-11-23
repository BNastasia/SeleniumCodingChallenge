import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TMobileSelenium {
    static WebDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.get("https://www.t-mobile.com/tablets");

//        setFilter("Deals", "New");
        setFilter("Deals", "All");
//        setFilter("Brands", "Alcatel", "Apple", "TCL");
        setFilter("Brands", "All");
        setFilter("Operating System", "All");

        driver.close();
    }

    static void click(WebElement nameOfCheckbox) {
        Actions actions = new Actions(driver);
        actions.moveToElement(nameOfCheckbox).click().perform();
    }
    public static void setFilter(String filter, String... subfilters) {
        if ("Deals".equals(filter)) {
            wait.until(ExpectedConditions.elementToBeClickable(By
                    .xpath("//fieldset[@class='ng-star-inserted'][1]"))).click();

            WebElement checkboxNew = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-1-input")));
            WebElement checkboxSpOffer = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-2-input")));

            for (String subfilter : subfilters) {
                switch (subfilter) {
                    case "New" -> click(checkboxNew);
                    case "Special offer" -> click(checkboxSpOffer);
                    case "All" -> {
                        click(checkboxNew);
                        click(checkboxSpOffer);
                    }
                    default -> throw new IllegalArgumentException("Invalid subfilter for 'Deals': " + subfilter);
                }
            }
        } else if ( "Brands".equals(filter)) {
            wait.until(ExpectedConditions.elementToBeClickable(By
                    .xpath("//fieldset[@class='ng-star-inserted'][2]"))).click();

            WebElement checkboxAlcatel = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-3-input")));
            WebElement checkboxApple = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-4-input")));
            WebElement checkboxSamsung = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-5-input")));
            WebElement checkboxTMobile = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-6-input")));
            WebElement checkboxTCL = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-7-input")));

            for(String subfilter : subfilters) {
                switch (subfilter) {
                    case "Alcatel" -> click(checkboxAlcatel);
                    case "Apple" -> click(checkboxApple);
                    case "Samsung" -> click(checkboxSamsung);
                    case "T-Mobile®" -> click(checkboxTMobile);
                    case "TCL" -> click(checkboxTCL);
                    case "All" -> {
                        click(checkboxAlcatel);
                        click(checkboxApple);
                        click(checkboxSamsung);
                        click(checkboxTMobile);
                        click(checkboxTCL);
                    }
                    default -> throw new IllegalArgumentException("Invalid subfilter for 'Brands': " + subfilter);
                }
            }
        } else if ("Operating System".equals(filter)) {
            wait.until(ExpectedConditions.elementToBeClickable(By
                    .xpath("//fieldset[@class='ng-star-inserted'][3]"))).click();

            WebElement checkboxAndroid = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-8-input")));
            WebElement checkboxiPadOS = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-9-input")));
            WebElement checkboxOther = wait.until(ExpectedConditions.elementToBeClickable(By.id("mat-checkbox-10-input")));

            for(String subfilter : subfilters) {
                switch (subfilter) {
                    case "Android" -> click(checkboxAndroid);
                    case "iPadOS" -> click(checkboxiPadOS);
                    case "Other" -> click(checkboxOther);
                    case "All" -> {
                        click(checkboxAndroid);
                        click(checkboxiPadOS);
                        click(checkboxOther);
                    }
                    default -> throw new IllegalArgumentException("Invalid subfilter for 'Operating System': " + subfilter);
                }
            }
        }
    }
}
