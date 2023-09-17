package org.example.pages;

import org.example.util.ResultSet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
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
    @FindBy(how = How.XPATH, using = "//*[@id=\"vatcalculator\"]/div[10]/div[3]/input")
    private WebElement resetButton;

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getSelectedCountry() {
        WebElement selectedCountry = countrySelect.findElement(By.xpath(".//option[@selected='selected']"));
        return selectedCountry.getText();
    }

    public void InputNetPrice(double net) {
        netPriceInput.sendKeys(String.valueOf(net));
    }

    public void InputVatSum(double vat) {
        vatSumInput.sendKeys(String.valueOf(vat));
    }

    public void InputGrossPrice(double gross) {
        grossPriceInput.sendKeys(String.valueOf(gross));
    }

    public void ResetPrices() {
        resetButton.click();
    }

    public ResultSet readResults() {
        String net = netPriceInput.getText();
        String vat = vatSumInput.getText();
        String gross = grossPriceInput.getText();
        return new ResultSet(Double.parseDouble(net),Double.parseDouble(vat),Double.parseDouble(gross));
    }

    private WebElement waitForCookiePopup() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        By popupXPath = By.xpath("/html/body/div[5]/div[2]/div[1]/div[2]/div[2]/button[1]/p");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(popupXPath));
    }

    public void acceptCookies() {
        waitForCookiePopup().click();
    }
}
