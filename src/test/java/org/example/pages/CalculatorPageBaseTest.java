package org.example.pages;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

abstract class CalculatorPageBaseTest {
    private String projectRoot = System.getProperty("user.dir");
    private String driverPath = projectRoot + "/src/main/resources/drivers/chromedriver.exe";
    private WebDriver driver;
    private CalculatorPage calculatorPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get("https://www.calkoo.com/en/vat-calculator");
        calculatorPage = new CalculatorPage(driver);
        calculatorPage.acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
}