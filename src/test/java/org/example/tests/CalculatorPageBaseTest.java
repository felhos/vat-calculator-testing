package org.example.tests;


import org.example.pages.CalculatorPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

abstract class CalculatorPageBaseTest {
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final String DRIVER_PATH = PROJECT_ROOT + "/src/main/resources/drivers/chromedriver.exe";
    protected WebDriver driver;
    protected CalculatorPage calculatorPage;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", DRIVER_PATH);
        driver = new ChromeDriver();
        driver.get("https://www.calkoo.com/en/vat-calculator");
        calculatorPage = new CalculatorPage(driver);
        calculatorPage.acceptCookies();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}