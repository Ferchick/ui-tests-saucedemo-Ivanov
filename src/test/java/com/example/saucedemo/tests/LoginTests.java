package com.example.saucedemo.tests;

import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test(description = "Позитивная авторизация standard_user")
    public void positiveLogin() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");

        Assert.assertEquals(inventoryPage.getTitle(), "Products",
                "Ожидался заголовок 'Products' после входа");
        Assert.assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "URL должен содержать /inventory.html, фактический: " + driver.getCurrentUrl());
    }

    @Test(description = "Негативная авторизация locked_out_user")
    public void lockedOutUserLogin() {
        LoginPage loginPage = openLoginPage();
        loginPage.loginExpectingError("locked_out_user", "secret_sauce");

        String error = loginPage.getErrorText();
        Assert.assertTrue(error.contains("Epic sadface"),
                "Ожидалось сообщение об ошибке 'Epic sadface...', фактически: " + error);
        Assert.assertTrue(error.contains("locked out"),
                "Ожидалось упоминание блокировки, фактически: " + error);
    }
}