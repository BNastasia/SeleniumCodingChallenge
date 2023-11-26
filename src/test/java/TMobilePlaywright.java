import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TMobilePlaywright {
    private static Page page;

    public static void main(String[] args) {
        try(Playwright playwright = Playwright.create()) {
            Browser browser = playwright
                    .chromium()
                    .launch(new BrowserType.LaunchOptions()
                            .setHeadless(false));

            page = browser.newPage();

            page.navigate("https://www.t-mobile.com/tablets");
            setFilter("Deals", "Special offer");
//            setFilter("Brands", "Alcatel", "Apple", "TCL");
            setFilter("Brands", "All");
            setFilter("Operating System", "All");
        }
    }

    public static void setFilter(String filter, String... subfilters) {
        page.locator("[data-testid='desktop-filter-group-name']").getByText(filter).click();

        for(String subfilter : subfilters) {
            if(subfilter.equals("All")) {
                List<Locator> allOptions = page.locator("//input[@name='" + filter + "']/parent::span").all();
                for (Locator option : allOptions) {
                    option.click();
                }
            } else {
                page.locator("[data-testid='desktop-filter-option']").getByText(subfilter).click();
            }
        }
    }
}
