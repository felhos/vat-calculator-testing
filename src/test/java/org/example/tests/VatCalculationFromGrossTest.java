package org.example.tests;

import org.example.util.ReferenceCalculator;
import org.example.util.ResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VatCalculationFromGrossTest extends CalculatorPageBaseTest{
    /*Given all mandatory fields (country, vat rate, one of the amounts) are provided,
    the website will calculate and show the other 2 amounts which were not provided
    originally as an input value*/
    private double vatRate;
    @BeforeEach
    public void getVatRate() {
        WebElement selectedVatRateRadio = calculatorPage.getVatOptions().stream()
                .filter(we -> we.isSelected()).findFirst().orElse(null);
        vatRate = Double.parseDouble(selectedVatRateRadio.getAttribute("value"));
    }
    @BeforeEach
    public void selectVatInput() {
        WebElement button = driver.findElement(By.id("F3"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }
    @ParameterizedTest
    @ValueSource(strings = {"100", "7500", "12345"})
    public void testCalculationFromNetWithSmallNumbersFromGross(String grossPrice) {
        calculatorPage.inputGrossPrice(grossPrice);
        ResultSet expected = ReferenceCalculator.calculateFromGross(Double.parseDouble(grossPrice), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"999999999", "897564231", "123456789"})
    //the test shows no error, but numbers over 9 digits only show their first 9 digits
    public void testCalculationFromNetWithBigNumbersFromNet(String grossPrice) {
        calculatorPage.inputGrossPrice(grossPrice);
        ResultSet expected = ReferenceCalculator.calculateFromGross(Double.parseDouble(grossPrice), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100", "-7500", "-123456789"})
    public void testCalculationFromNetWithNegativeNumbersFromNet(String grossPrice) {
        calculatorPage.inputGrossPrice(grossPrice);
        ResultSet expected = ReferenceCalculator.calculateFromGross(Double.parseDouble(grossPrice), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"fifty", "10fe14", "m&ms"})
    public void testCalculationFromNetWithNonnumericalInputFromNet(String grossPrice) {
        calculatorPage.inputGrossPrice(grossPrice);
        String expected = "NaN";
        String actual = calculatorPage.getNetPriceInput().getAttribute("value");
        assertEquals(expected, actual);
    }
}
