package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CalculatorPage {
    private WebDriver driver;

    @FindBy(how = How.XPATH, using = "//*[@id=\"vatcalculator\"]/div[2]/div[2]/select")
    private WebElement countrySelect;
    @FindBy(how = How.XPATH, using = "//*[@id=\"vatcalculator\"]/div[4]/div[2]")
    private List<WebElement> vatOptions;
    @FindBy(id = "NetPrice")
    private WebElement netPriceInput;
    @FindBy(id = "VATsum")
    private WebElement vatSumInput;
    @FindBy(id = "Price")
    private WebElement grossPriceInput;
    @FindBy(id = "dropdownLanguages")
    private WebElement languagueSelector;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSelectedCountry() {
        WebElement selectedCountry = countrySelect.findElement(By.xpath(".//option[@selected='selected']"));
        return selectedCountry.getText();
    }
}
