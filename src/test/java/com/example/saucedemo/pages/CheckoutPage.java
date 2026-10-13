package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    private final By firstName = By.cssSelector("#first-name, [data-test='firstName']");
    private final By lastName = By.cssSelector("#last-name, [data-test='lastName']");
    private final By postalCode = By.cssSelector("#postal-code, [data-test='postalCode']");
    private final By continueButton = By.cssSelector("#continue, [data-test='continue']");
    private final By finishButton = By.cssSelector("#finish, [data-test='finish']");
    private final By completeHeader = By.cssSelector(".complete-header");
    private final By error = By.cssSelector("[data-test='error']");
    private final By summaryTotal = By.cssSelector(".summary_total_label");

    public CheckoutPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public CheckoutPage fillInfo(String first, String last, String zip) {
        type(firstName, first);
        type(lastName, last);
        type(postalCode, zip);
        return this;
    }

    public CheckoutPage continueCheckout() {
        click(continueButton);
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.urlContains("checkout-step-two"),
                    ExpectedConditions.visibilityOfElementLocated(error)
            ));
        } catch (Exception ignored) {
        }
        return this;
    }

    public CheckoutPage finish() {
        click(finishButton);
        wait.until(ExpectedConditions.urlContains("checkout-complete"));
        return this;
    }

    public String getCompleteHeader() {
        return getText(completeHeader);
    }

    public String getErrorText() {
        return getText(error);
    }

    public String getTotal() {
        return getText(summaryTotal);
    }
}