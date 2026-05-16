# Booking Site Automation with Selenium

A Selenium WebDriver automation project covering real-world web testing scenarios including login workflows, live flight search, and browser multi-window/tab handling. Built in Java to simulate end-to-end user journeys on a travel booking website.

---

## What This Project Tests

This project targets **a real flight booking website** alongside a **demo login application**, covering common UI automation scenarios that SDETs encounter in day-to-day project work:

| Test Scenario | Description |
|---|---|
| Login Page Automation | Enter credentials, validate login success/failure |
| Flight Search | Search for flights with departure/destination inputs, validate search results load |
| Window Switching | Handle browser popups and switch between multiple windows/tabs |
| Tab Handling | Open new tabs programmatically and assert content across tabs |

---

## Tech Stack

| Tool | Purpose |
|---|---|
| Java | Core language |
| Selenium WebDriver 4.x | Browser automation |
| ChromeDriver | Chrome browser driver (bundled in `lib/chromedriver`) |
| JUnit / TestNG | Test structure and assertions |
| Maven | Build and dependency management |

---

## Prerequisites

- Java JDK 8 or above installed
- Maven 3.6+ installed
- Google Chrome browser installed
- ChromeDriver version compatible with your installed Chrome version

> **Note:** This project uses a bundled ChromeDriver under `lib/chromedriver/`. If your Chrome version differs, replace it with the matching driver from [chromedriver.chromium.org](https://chromedriver.chromium.org/downloads).

---

## Project Structure

```
bookingSiteAutomationWithSelenium/
├── lib/
│   └── chromedriver/           # Bundled ChromeDriver binary
├── src/
│   └── main/java/
│       ├── tests/              # Test classes for each scenario
│       └── pages/              # Page-level interaction methods
├── .gitignore
├── pom.xml                     # Project dependencies
└── README.md
```

---

## How to Run

**Clone the repository:**
```bash
git clone https://github.com/GnanaprasadR/bookingSiteAutomationWithSelenium.git
cd bookingSiteAutomationWithSelenium
```

**Run all tests:**
```bash
mvn clean test
```

---

## Key Automation Concepts Demonstrated

- **Explicit and Implicit Waits** — avoiding flaky tests caused by dynamic page loading
- **Window Handle Management** — `getWindowHandles()` and `switchTo().window()`
- **ChromeDriver Configuration** — setting up ChromeOptions and driver path
- **Locator Strategies** — using ID, XPath, CSS Selectors for reliable element identification

---

## Author

**Gnanaprasad R**
SDET | 10 years of experience in software testing
[LinkedIn](https://www.linkedin.com/in/gnanaprasad-r-556082b7) • [GitHub](https://github.com/GnanaprasadR)
