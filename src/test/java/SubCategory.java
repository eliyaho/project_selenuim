import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SubCategory {
    private WebDriver driver;

    public SubCategory(WebDriver driver) {
        this.driver = driver;
    }

    public void processLinks(List<LinkInfo> links) {
        for (LinkInfo link : links) {
            driver.get(link.getHref());
            List<WebElement> subLinks = driver.findElements(By.xpath("//*[@id=\"pl_web_page_wrap\"]/div[7]/div/table/tbody/tr/td/div/div[2]/ul"));

            for (WebElement subLink : subLinks) {
                System.out.println("name: " + subLink.getText());
                System.out.println("link: " + subLink.getAttribute("href"));
            }
        }
    }

//    public void processButton(String xpath) {
//        WebElement button = driver.findElement(By.xpath(xpath));
//        button.click();
//
//        List<WebElement> links = driver.findElements(By.xpath("//*[@id=\"pl_web_page_wrap\"]/div[7]/div/table/tbody/tr/td/div/div[2]/ul/li/a"));
//        for (WebElement link : links) {
//            System.out.println("name: " + link.getText());
//            System.out.println("link: " + link.getAttribute("href"));
//        }
//    }
}

class LinkInfo {
    private String name;
    private String href;

    public LinkInfo(String name, String href) {
        this.name = name;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }
}
