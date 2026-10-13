package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {
    private final By checkoutButton = By.cssSelector("#checkout, [data-test='checkout']");
    private final By itemName = By.cssSelector(".inventory_item_name");
    private final By itemPrice = By.cssSelector(".inventory_item_price");
    private final By removeBackpack = By.cssSelector("button[data-test='remove-sauce-labs-backpack']");
    private final By cartItems = By.cssSelector(".cart_item");

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public CheckoutPage checkout() {
        System.out.println("URL перед checkout: " + driver.getCurrentUrl());
        System.out.println("Кнопка checkout найдена: " + driver.findElements(checkoutButton).size());
        System.out.println("HTML кнопки checkout: " + driver.findElements(checkoutButton).stream()
                .map(e -> e.getAttribute("outerHTML")).findFirst().orElse("не найдена"));

        try {
            click(checkoutButton);
        } catch (TimeoutException e) {
            System.out.println("Обычный клик не удался, пробуем JS-клик");
            jsClick(checkoutButton);
        }

        wait.until(ExpectedConditions.urlContains("checkout-step-one"));
        System.out.println("URL после checkout: " + driver.getCurrentUrl());
        return new CheckoutPage(driver, wait);
    }

    public String getItemName() {
        return getText(itemName);
    }

    public String getItemPrice() {
        return getText(itemPrice);
    }

    public CartPage removeBackpack() {
        System.out.println("Кнопка Remove найдена: " + driver.findElements(removeBackpack).size());
        System.out.println("HTML кнопки Remove: " + driver.findElements(removeBackpack).stream()
                .map(e -> e.getAttribute("outerHTML")).findFirst().orElse("не найдена"));
        click(removeBackpack);
        return this;
    }

    public boolean isCartEmpty() {
        try {
            wait.until(ExpectedConditions.numberOfElementsToBe(cartItems, 0));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean isBackpackRemoved() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(removeBackpack));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }
}