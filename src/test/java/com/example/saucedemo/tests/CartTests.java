package com.example.saucedemo.tests;

import com.example.saucedemo.pages.CartPage;
import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test(description = "Добавление товара в корзину и проверка состава")
    public void addBackpackToCart() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");

        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.getCartCount(), "1",
                "Счётчик корзины должен быть 1, фактически: " + inventoryPage.getCartCount());

        CartPage cartPage = inventoryPage.openCart();
        Assert.assertTrue(cartPage.getItemName().contains("Sauce Labs Backpack"),
                "В корзине должен быть Sauce Labs Backpack, фактически: " + cartPage.getItemName());
        Assert.assertTrue(cartPage.getItemPrice().contains("29.99"),
                "Цена Sauce Labs Backpack должна содержать 29.99, фактически: " + cartPage.getItemPrice());
    }

    @Test(description = "Удаление товара из корзины")
    public void removeBackpackFromCart() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.openCart();
        cartPage.removeBackpack();

        Assert.assertTrue(cartPage.isBackpackRemoved() || cartPage.isCartEmpty(),
                "После удаления кнопка Remove должна исчезнуть, либо корзина должна стать пустой");
    }
}