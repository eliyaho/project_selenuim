import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubTableScanner {
    private WebDriver driver;

    public SubTableScanner(WebDriver driver) {
        this.driver = driver;
    }

    public String[] scanData() {
        WebElement dataElement = driver.findElement(By.xpath("//*[@id=\"designbox_1714\"]/div/div/ul"));
        String price = dataElement.getAttribute("ee_list_itemprice");
        String name = dataElement.getAttribute("ee_list_itemname");
        String fontSize = dataElement.getAttribute("font-size");

        return new String[]{price, name, fontSize};
    }
}
