import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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


            WebElement fromInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter city or airport']")));
            fromInput.click();
            fromInput.sendKeys("Chennai (MAA)");
            Thread.sleep(2000); // Allow suggestions to load
            fromInput.sendKeys(Keys.ENTER);

            WebElement toInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//input[@placeholder='Enter city or airport'])[2]")));
            toInput.click();
            toInput.sendKeys("Sydney (SYD)");
            Thread.sleep(2000);
            toInput.sendKeys(Keys.ENTER);

            LocalDate departureDate = LocalDate.now().plusMonths(4);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            String departureDateString = departureDate.format(formatter);

            WebElement departDateInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Depart']")));
            departDateInput.click();
            WebElement dateElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@aria-label='" + departureDateString + "']")));
            dateElement.click();
            WebElement travelersButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'1 Traveller')]")));
            travelersButton.click();

            for (int i = 1; i < 3; i++) {
                WebElement addAdult = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Increase adults']")));
                addAdult.click();
                Thread.sleep(500);
            }

            // Add 1 child
            WebElement addChild = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@aria-label='Increase children']")));
            addChild.click();
            Thread.sleep(500);

            WebElement travelersDone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Done']")));
            travelersDone.click();

            WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Search')]")));
            searchButton.click();

            Thread.sleep(5000);
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Final URL after search: " + currentUrl);
            assert currentUrl.contains("Chennai") && currentUrl.contains("Sydney") : "URL does not contain expected cities";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}