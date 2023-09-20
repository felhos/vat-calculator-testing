package org.example.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class PriceInputFieldTest extends CalculatorPageBaseTest {
    //One of the following amounts are supported as an input: Net/VAT/Gross
    //This test class has failed test cases that are not due to the functionality
    //of the site. I couldn't manage to properly select the input.
    @Test
    public void testIfInputsExist() {
        assertNotNull(calculatorPage.getNetPriceInput());
        assertNotNull(calculatorPage.getVatSumInput());
        assertNotNull(calculatorPage.getGrossPriceInput());
    }

    @ParameterizedTest
    //F1,F2 and F3 are the ids of the radio select options for net, vat and gross respectively
    @ValueSource(strings = {"F1","F2","F3"})
    public void testInputIsEnabledAfterSelection(String idOfRadioSelect) {
        WebElement button = driver.findElement(By.id(idOfRadioSelect));
        System.out.println(button.getAttribute("id"));
        //click() still doesn't work, so the same JS solution is used as in VatRateSelectTest
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
        WebElement chosenInputField = calculatorPage.getNetPriceInput();
        ;
        switch (idOfRadioSelect) {
            case "F1":
                chosenInputField = calculatorPage.getNetPriceInput();
                break;
            case "F2":
                chosenInputField = calculatorPage.getVatSumInput();
                break;
            case "F3":
                chosenInputField = calculatorPage.getGrossPriceInput();
                break;
        }

        assertFalse(chosenInputField.getAttribute("class").contains("disabled"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"F1", "F2", "F3"})
    public void testInputIsDisabledAfterSelectionOfOtherOption(String idOfRadioSelect) {
        WebElement button = driver.findElement(By.id(idOfRadioSelect));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", button);
        WebElement notChosenInputField1 = calculatorPage.getNetPriceInput();
        WebElement notChosenInputField2 = calculatorPage.getNetPriceInput();
        switch (idOfRadioSelect) {
            case "F1":
                notChosenInputField1 = calculatorPage.getVatSumInput();
                notChosenInputField2 = calculatorPage.getGrossPriceInput();
                break;
            case "F2":
                notChosenInputField1 = calculatorPage.getNetPriceInput();
                notChosenInputField2 = calculatorPage.getGrossPriceInput();
                break;
            case "F3":
                notChosenInputField1 = calculatorPage.getNetPriceInput();
                notChosenInputField2 = calculatorPage.getVatSumInput();
                break;
        }

        assertTrue(notChosenInputField1.getAttribute("class").contains("disabled"));
        assertTrue(notChosenInputField2.getAttribute("class").contains("disabled"));
    }
}
