import DB.CreateTable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SubSubCategory {
    private WebDriver driver;

    public SubSubCategory(WebDriver driver) {
        this.driver = driver;
    }
    public static String tablename;
    public void processLinks(List<SubCategory.LinkInfo> links) {
        for (SubCategory.LinkInfo link : links) {
            System.out.println("subsub"+link.getHref()+link.getName());
            this.tablename =  link.getName();
            driver.get(link.getHref());

            List<SubSubLinkInfo> subSubLinkInfos = new ArrayList<>();
            List<WebElement> subLinks = driver.findElements(By.xpath("//*[@id=\"pl_web_page_wrap\"]/div[7]/div/table/tbody/tr/td/div/div[2]/ul/li/div/a[2]"));
            if (subLinks != null) {
                for (WebElement linkElement : subLinks) {
                    String name = linkElement.getText();
                    String href = linkElement.getAttribute("href");
                    subSubLinkInfos.add(new SubSubLinkInfo(name, href));
                }
                printSubSubLinks(subSubLinkInfos);
            }else {
                ThirdCategory thirdCategory = new ThirdCategory(driver);
                thirdCategory.getLinks();
            }
        }
    }

    private void printSubSubLinks(List<SubSubLinkInfo> subSubLinkInfos) {
        CreateTable.createSubTable("table_three");
        for (SubSubLinkInfo link : subSubLinkInfos) {
            System.out.println("Name: " + link.getName());
            System.out.println("Link: " + link.getHref());
            CreateTable.insertDataIntoSubTable("table_three", link.getName(), link.getHref(),tablename);
        }
    }

    class SubSubLinkInfo {
        private String name;
        private String href;

        public SubSubLinkInfo(String name, String href) {
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
