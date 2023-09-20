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

public class VatCalculationFromVatTest extends CalculatorPageBaseTest{
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
        WebElement button = driver.findElement(By.id("F2"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
    }
    @ParameterizedTest
    @ValueSource(strings = {"100", "7500", "12345"})
    public void testCalculationFromNetWithSmallNumbersFromVat(String vatSum) {
        calculatorPage.inputVatSum(vatSum);
        ResultSet expected = ReferenceCalculator.calculateFromVat(Double.parseDouble(vatSum), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }
}
