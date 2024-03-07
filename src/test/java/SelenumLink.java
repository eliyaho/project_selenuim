import DB.CreateTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import DB.InsertData;


public class SelenumLink {
    private WebDriver driver;

    public SelenumLink(WebDriver driver) {
        this.driver = driver;
    }

    public List<LinkInfo> getLinks() {
        List<LinkInfo> links = new ArrayList<>();
        List<WebElement> linkElements = driver.findElements(By.xpath("//*[@id=\"designbox_1639\"]/div/div[2]/ul/li/a"));

        for (WebElement linkElement : linkElements) {
            String name = linkElement.getText();
            String href = linkElement.getAttribute("href");
            links.add(new LinkInfo(name, href));
        }

        return links;
    }

    public void printLinks(List<LinkInfo> links) {
        for (LinkInfo link : links) {
            System.out.println("Name: " + link.getName());
            System.out.println("Link: " + link.getHref());

            // יצירת טבלה במסד הנתונים עבור כל שורה ברשימת הלינקים
            CreateTable.createTable(link.getName());

//            InsertData.insertData(link.getName(), link.getHref());
        }
    }


}
