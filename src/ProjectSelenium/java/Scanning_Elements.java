import DB.CreateTable;
import DB.InsertData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Scanning_Elements {
    private WebDriver driver;
    private String tablename;

    public Scanning_Elements(WebDriver driver) {
        this.driver = driver;
    }

    public void ScaningElem(List<SubSubCategory.SubSubLinkInfo> subSubLinkInfos) {
        for (SubSubCategory.SubSubLinkInfo link : subSubLinkInfos) {
            driver.get(link.getHref());

            List<ElementThird> elements = new ArrayList<>();
            List<WebElement> elementLink = driver.findElements(By.xpath("//*[@id=\"designbox_1714\"]/div/div/ul/li/div/div[2]/a[2]"));

            for (WebElement linkElement : elementLink) {
                WebElement NameElement = linkElement.findElement(By.xpath("//*[@id=\"designbox_1714\"]/div/div/ul/li[1]/div/div[4]/a"));
                String name = NameElement.getText();
                String href = linkElement.getAttribute("href");

                WebElement priceElement = linkElement.findElement(By.xpath("//*[@id=\"designbox_1714\"]/div/div/ul/li/div/div[5]/p/span[2]"));
                String price = priceElement.getText();
                elements.add(new ElementThird(name, href, price));
            }
            printElement(elements, link.getName());
        }
    }

    private void printElement(List<ElementThird> elements, String tableName) {
        CreateTable.createElementTable("element");
        for (ElementThird el : elements) {
            System.out.println("Name: " + el.getName());
            System.out.println("Link: " + el.getHref());
            System.out.println("Price: " + el.getprice());
            InsertData.insertDataIntoElementTable("element", el.getName(), el.getHref(), tableName, el.getprice());
        }
    }


    static class ElementThird {
        private String name;
        private String href;
        private String price;

        public ElementThird(String name, String href, String price) {
            this.name = name;
            this.href = href;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        public String getprice() {
            return price;
        }
    }
}