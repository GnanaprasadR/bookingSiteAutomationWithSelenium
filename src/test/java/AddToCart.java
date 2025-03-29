import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.List;

public class AddToCart {

    public static void main(String[] args) throws InterruptedException {
        String driverPath = System.getProperty("user.dir") + "/lib/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();

        String[] itemsNeeded= {"Cucumber","Brocolli","Beetroot"};
        driver.get("https://rahulshettyacademy.com/seleniumPractise/");
        Thread.sleep(3000);
        addItems(driver,itemsNeeded);
        driver.quit();
    }

    public static  void addItems(WebDriver driver,String[] itemsNeeded) {
        int j=0;
        List<WebElement> products=driver.findElements(By.cssSelector("h4.product-name"));
        for(int i=0;i<products.size();i++) {
            String[] name=products.get(i).getText().split("-");
            String formattedName=name[0].trim();
            List<String> itemsNeededList = Arrays.asList(itemsNeeded);
            if(itemsNeededList.contains(formattedName)) {
                j++;
                driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
                if(j==itemsNeeded.length) {
                    break;
                }
            }
        }
    }
}
