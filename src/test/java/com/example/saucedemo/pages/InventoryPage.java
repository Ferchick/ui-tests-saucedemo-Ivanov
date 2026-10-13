package com.example.saucedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {
    private final By title = By.cssSelector(".title");
    private final By cartBadge = By.cssSelector(".shopping_cart_badge");
    private final By cartLink = By.cssSelector("a.shopping_cart_link, [data-test='shopping-cart-link']");
    private final By sortSelect = By.cssSelector(".product_sort_container");
    private final By itemNames = By.cssSelector(".inventory_item_name");
    private final By itemPrices = By.cssSelector(".inventory_item_price");

    public InventoryPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public String getTitle() {
        return getText(title);
    }

    public String getCartCount() {
        return isVisible(cartBadge) ? getText(cartBadge) : "0";
    }

    public InventoryPage addBackpackToCart() {
        click(By.cssSelector("button[data-test='add-to-cart-sauce-labs-backpack']"));
        return this;
    }

    public CartPage openCart() {
        click(cartLink);
        wait.until(ExpectedConditions.urlContains("cart.html"));
        return new CartPage(driver, wait);
    }

    public InventoryPage sortBy(String value) {
        Select select = new Select(waitVisible(sortSelect));
        select.selectByValue(value);
        try {
            wait.until(ExpectedConditions.stalenessOf(
                    driver.findElements(itemNames).get(0)));
        } catch (Exception ignored) {
            // список мог не успеть стать stale — просто продолжаем
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(itemNames));
        return this;
    }

    public List<String> getItemNames() {
        return driver.findElements(itemNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getItemPrices() {
        return driver.findElements(itemPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "").trim()))
                .collect(Collectors.toList());
    }
}