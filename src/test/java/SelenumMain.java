import org.openqa.selenium.WebDriver;

import java.util.List;

public class SelenumMain {

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

        SelenumLink seleniumLinks = new SelenumLink(driver);
        List<SeleniumLinkInfo> links = seleniumLinks.getLinks();
        seleniumLinks.printLinks(links);

        //change to return
        SubCategory subCategory = new SubCategory(driver);
        subCategory.processLinks(links);


//        SubSubCategory  subsubCategory = new SubSubCategory(driver);
//        List<SubCategory.LinkInfo> Slinks = subsubCategory.getLinks();
//        subsubCategory.processLinks(Slinks);
    }
}
