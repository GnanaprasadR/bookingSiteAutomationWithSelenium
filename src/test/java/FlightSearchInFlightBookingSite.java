import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class FlightSearchInFlightBookingSite {
    public static void main(String[] args) {
        // Set up Chrome in Incognito mode
        String driverPath = System.getProperty("user.dir") + "/lib/chromedriver/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        WebDriver driver = new ChromeDriver();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            driver.get("https://www.ixigo.com/flights");
            driver.manage().window().maximize();

            try {
                WebElement closePopup = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[aria-label='Close']")));
                closePopup.click();
            } catch (Exception ignored) {}


            WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div")));
            fromInput.click();
            WebElement fromInputValue = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Chennai International Airport']")));
            fromInputValue.click();

            WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input")));
            toInput.click();
            toInput.sendKeys("Sydney (SYD)");
            toInput.sendKeys(Keys.ENTER);
            WebElement toInputValues = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[3]/div[1]/div[1]/div[1]/span")));
            toInputValues.click();

            WebElement departDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[2]/div/div/div[2]/button[32]/abbr")));
            departDateInput.click();

            WebElement addAdult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[1]/div[2]/div/button[2]")));
            addAdult.click();
            WebElement addChild = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[2]/div[2]/div/button[2]")));
            addChild.click();

            WebElement travelersDone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Done']")));
            travelersDone.click();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, -200);");
            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
            searchButton.click();

            Thread.sleep(5000);
            String mainTab = driver.getWindowHandle();
            Set<String> allTabs = driver.getWindowHandles();
            Iterator<String> iterator = allTabs.iterator();

            while(iterator.hasNext()) {
                String nextTab = iterator.next();
                if (!nextTab.equals(mainTab)) {
                    driver.switchTo().window(nextTab);
                    break;
                }
            }

            String currentUrl = driver.getCurrentUrl();
            System.out.println("Final URL after search: " + currentUrl);
            assert currentUrl.contains("from=MAA") && currentUrl.contains("to=SYD") : "URL does not contain expected cities";

            driver.switchTo().window(mainTab);
            System.out.println("Successfull returned to main tab");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}