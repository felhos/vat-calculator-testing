package org.example.tests;

import org.example.util.ReferenceCalculator;
import org.example.util.ResultSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VatCalculationTest extends CalculatorPageBaseTest{
    /*Given all mandatory fields (country, vat rate, one of the amounts) are provided,
    the website will calculate and show the other 2 amounts which were not provided
    originally as an input value*/
    /*I couldn't solve the selection of the input type, so this test class is incomplete.
    It only tests the calculations with the default selection, which is net price.*/
    private double vatRate;
    @BeforeEach
    public void getVatRate() {
        WebElement selectedVatRateRadio = calculatorPage.getVatOptions().stream()
                .filter(we -> we.isSelected()).findFirst().orElse(null);
        vatRate = Double.parseDouble(selectedVatRateRadio.getAttribute("value"));
    }
    @ParameterizedTest
    @ValueSource(strings = {"100", "7500", "12345"})
    public void testCalculationFromNetWithSmallNumbers(String netPrice) {
        calculatorPage.inputNetPrice(netPrice);
        ResultSet expected = ReferenceCalculator.calculateFromNet(Double.parseDouble(netPrice), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"999999999", "897564231", "123456789"})
    //no test for meaningful error message
    //the test result is also misleading, the element holds the correct value, but is incorrectly displayed
    public void testCalculationFromNetWithBigNumbers(String netPrice) {
        calculatorPage.inputNetPrice(netPrice);
        ResultSet expected = ReferenceCalculator.calculateFromNet(Double.parseDouble(netPrice), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"-100", "-7500", "-123456789"})
    //no test for meaningful error message
    public void testCalculationFromNetWithNegativeNumbers(String netPrice) {
        calculatorPage.inputNetPrice(netPrice);
        ResultSet expected = ReferenceCalculator.calculateFromNet(Double.parseDouble(netPrice), vatRate);
        ResultSet actual = calculatorPage.readResults();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"fifty", "10fe14", "m&ms"})
    //no test for meaningful error message
    public void testCalculationFromNetWithNonnumericalInput(String netPrice) {
        calculatorPage.inputNetPrice(netPrice);
        String expected = "NaN";
        String actual = calculatorPage.getGrossPriceInput().getAttribute("value");
        assertEquals(expected, actual);
    }
}
