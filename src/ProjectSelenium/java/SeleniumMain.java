import org.openqa.selenium.WebDriver;

import java.util.List;

public class SeleniumMain {

    public static void main(String[] args) {
        WebDriver driver = setup();
        teststeps(driver);
        driver.quit();
    }

    public static WebDriver setup() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get("https://www.foodappeal-online.com/");
        try {
            driver.manage().window().maximize();
        } catch (Exception e) {
            System.out.println("max size window ");
        }
        return driver;
    }

    public static void teststeps(WebDriver driver) {

        ScaningLink scaningLink = new ScaningLink(driver);
        List<SeleniumLinkInfo> links = scaningLink.getLinks();
        scaningLink.printLinks(links);

        SubCategory subCategory = new SubCategory(driver);
        subCategory.processLinks(links);

    }
}
