import DB.CreateTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class ThirdCategory {
    private WebDriver driver;

    public ThirdCategory(WebDriver driver) {
        this.driver = driver;
    }


    public List<ElementThird> getLinks() {
        List<ElementThird> elements = new ArrayList<>();
        List<WebElement> elementT = driver.findElements(By.xpath("//*[@id=\"designbox_1714\"]/div/div/ul/a"));
        WebElement priceElement = driver.findElement(By.xpath("//*[@id=\"total1179\"]"));
        for (WebElement linkElement : elementT) {
            String name = linkElement.getText();
            String href = linkElement.getAttribute("href");

            // לחפש ולקחת את המחיר

            String price = priceElement.getText();

            elements.add(new ElementThird(name, href, price));
        }

        printElement(elements);
        return elements;
    }


    private void printElement(List<ElementThird> Element) {
//        CreateTable.createElementTable("Element");
        for (ElementThird El : Element) {
            System.out.println("Name: " + El.getName());
            System.out.println("Link: " + El.getHref());
            System.out.println("Link: " + El.getprice());
//            CreateTable.insertElementTable("Element", El.getName(), El.getHref(),"A");
        }
    }

    class ElementThird {
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
