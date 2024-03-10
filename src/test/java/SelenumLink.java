import DB.CreateTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SelenumLink {
    private WebDriver driver;

    public SelenumLink(WebDriver driver) {
        this.driver = driver;
    }

    public List<SeleniumLinkInfo> getLinks() {
        List<SeleniumLinkInfo> links = new ArrayList<>();
        List<WebElement> linkElements = driver.findElements(By.xpath("//*[@id=\"designbox_1639\"]/div/div[2]/ul/li/a"));

        for (WebElement linkElement : linkElements) {
            String name = linkElement.getText();
            String href = linkElement.getAttribute("href");
            links.add(new SeleniumLinkInfo(name, href));
        }
        return links;
    }

    public void printLinks(List<SeleniumLinkInfo> links) {
        CreateTable.createMainTable("main");
        for (SeleniumLinkInfo link : links) {
            System.out.println("Name: " + link.getName());
            System.out.println("Link: " + link.getHref());
            CreateTable.insertDataIntoMainTable("main", link.getName(), link.getHref());
        }
    }
}

class SeleniumLinkInfo {
    private String name;
    private String href;

    public SeleniumLinkInfo(String name, String href) {
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
