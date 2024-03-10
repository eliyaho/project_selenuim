import DB.CreateTable;
//import jdk.javadoc.internal.doclets.toolkit.util.links.LinkInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;


public class SubCategory {
    private WebDriver driver;

    public SubCategory(WebDriver driver) {
        this.driver = driver;
    }
    public static String tablename;
    public void processLinks(List<SeleniumLinkInfo> links) {

        for (SeleniumLinkInfo link : links) {
            System.out.println("sub"+link.getHref()+link.getName());
            this.tablename =  link.getName();
            driver.get(link.getHref());
            List<LinkInfo> subLinkInfos = new ArrayList<>();
            List<WebElement> subLinkButtons = driver.findElements(By.xpath("//*[@id=\"pl_web_page_wrap\"]/div[7]/div/table/tbody/tr/td/div/div[2]/ul/li/div/a[2]"));
            if (subLinkButtons != null) {
                for (WebElement button : subLinkButtons) {
                    String name = button.getText();
                    String href = button.getAttribute("href");
                    subLinkInfos.add(new LinkInfo(name, href));
                }
                ClickButon(subLinkInfos);
            }

        }
    }
    private void ClickButon(List<LinkInfo> subLinkInfos) {
        CreateTable.createSubTable("table_two");
        for (LinkInfo subLink : subLinkInfos) {
            System.out.println(subLink.getName());
            System.out.println(subLink.getHref());
            driver.get(subLink.getHref());
            CreateTable.insertDataIntoSubTable("table_two", subLink.getName(), subLink.getHref(),tablename);
//            SubSubCategory subsubCategory = new SubSubCategory(driver);
//            subsubCategory.processLinks(subLinkInfos);
        }

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
}
