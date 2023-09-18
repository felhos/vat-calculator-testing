package org.example.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class VatRateSelectTest extends CalculatorPageBaseTest {
    //User must be able to choose a valid VAT rate for the selected country
    private static final String DEFAULT_COUNTRY_CODE_FOR_VAT_TEST = "45";

    @BeforeEach
    public void selectCountryWithMultipleVatOptions() {
        //Somewhat arbitrary choice of Bangladesh, because it has many
        //different VAT rates, including not whole numbers.
        Select countrySelect = new Select(calculatorPage.getCountrySelect());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        countrySelect.selectByValue(DEFAULT_COUNTRY_CODE_FOR_VAT_TEST);
        wait.until(ExpectedConditions.attributeToBe(calculatorPage.getCountrySelect(), "value", DEFAULT_COUNTRY_CODE_FOR_VAT_TEST));
    }

    @ParameterizedTest
    @ValueSource(strings = {"2.25", "7.5", "10", "15"})
    public void testVatRateSelection(String vatrate) {
        WebElement desiredRadioOption = calculatorPage.getVatOptions().stream()
                .filter(we -> we.getAttribute("value").equals(vatrate)).findFirst().orElse(null);
        //desiredRadioOption.click(); does not work, couldn't find the reason for it
        //the following work-around is not ideal
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", desiredRadioOption);
        assertTrue(desiredRadioOption.isSelected());
    }
}
