package org.example.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomExpectedConditions {
    public static ExpectedCondition<Boolean> waitForNonEmptyText(final WebElement element) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = element.getAttribute("value");
                return !text.isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for input field to be filled";
            }
        };
    }
}