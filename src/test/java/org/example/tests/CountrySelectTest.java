package org.example.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountrySelectTest extends CalculatorPageBaseTest {
    private Select countrySelect;

    @BeforeEach
    public void setUpCountrySelectTest() {
        countrySelect = new Select(calculatorPage.getCountrySelect());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Germany", "Afghanistan", "Thailand", "Kenya", "New Zealand"})
    public void testCountrySelection(String countryName) {
        //Only tests the site in English language. The value attribute is numeric and
        //shared across languages, but are hidden from the user.
        countrySelect.selectByVisibleText(countryName);
        String result = calculatorPage.getSelectedCountryName();
        assertEquals(countryName, result);
    }
}
