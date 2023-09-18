package org.example.tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

public class MaximumPrecisionTest extends CalculatorPageBaseTest{
    //Amounts can be entered with maximum 2 decimal digit precision
    public static Stream<Arguments> parameters() {
        return Stream.of(
                of("100", "100"),
                of("5.25", "5.25"),
                of("100,4", "100,4"),
                of("2000.2523", "2000.25"),
                of("9999999.9999", "9999999.99")
        );
    }
    @ParameterizedTest
    @MethodSource("parameters")
    public void testMaximumPrecisionOfInput(String input, String expected)  {
        calculatorPage.inputNetPrice(input);
        String actual = calculatorPage.getNetPriceInput().getAttribute("value");
        assertEquals(actual, expected);
    }
}
