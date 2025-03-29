import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class WindowHandlesViaSelenium {

    public static void main(String[] args) {
        String driverPath = System.getProperty("user.dir") + "/lib/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            driver.get("https://www.google.com");
            String parentWindow = driver.getWindowHandle();
            System.out.println("Parent Window Title: " + driver.getTitle());

            // Open first child tab
            ((JavascriptExecutor) driver).executeScript("window.open('https://www.ixigo.com/flights', '_blank');");

            // Open second child tab
            ((JavascriptExecutor) driver).executeScript("window.open('https://www.qantas.com', '_blank');");


            Set<String> allWindows = driver.getWindowHandles();
            Iterator<String> iterator = allWindows.iterator();

            String child1 = "";
            String child2 = "";

            // Iterate and get child window handles
            while (iterator.hasNext()) {
                String window = iterator.next();
                if (!window.equals(parentWindow)) {
                    if (child1.isEmpty()) {
                        child1 = window;
                    } else {
                        child2 = window;
                    }
                }
            }

            // Switching to 1st child
            driver.switchTo().window(child1);
            System.out.println("Child 1 (ixigo) Title: " + driver.getTitle());

            // Switching to 2nd child
            driver.switchTo().window(child2);
            System.out.println("Child 2 (Qantas) Title: " + driver.getTitle());

            // Back to parent
            driver.switchTo().window(parentWindow);
            System.out.println("Back to Parent Window: " + driver.getTitle());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}


