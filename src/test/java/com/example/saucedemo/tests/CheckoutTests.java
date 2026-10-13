package com.example.saucedemo.tests;

import com.example.saucedemo.pages.CartPage;
import com.example.saucedemo.pages.CheckoutPage;
import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckoutTests extends BaseTest {

    @Test(description = "Успешное оформление заказа")
    public void successfulCheckout() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillInfo("Ivan", "Ivanov", "123456")
                .continueCheckout();

        Assert.assertTrue(checkoutPage.getTotal().contains("Total"),
                "На странице Overview должна быть итоговая сумма");

        checkoutPage.finish();
        Assert.assertEquals(checkoutPage.getCompleteHeader(), "Thank you for your order!",
                "Ожидалось подтверждение успешного заказа");
    }

    @Test(description = "Валидация обязательного поля First Name")
    public void checkoutValidationEmptyFirstName() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");
        inventoryPage.addBackpackToCart();

        CartPage cartPage = inventoryPage.openCart();
        CheckoutPage checkoutPage = cartPage.checkout();
        checkoutPage.fillInfo("", "Ivanov", "123456")
                .continueCheckout();

        String error = checkoutPage.getErrorText();
        Assert.assertTrue(error.contains("First Name is required"),
                "Ожидалась ошибка 'First Name is required', фактически: " + error);
    }
}