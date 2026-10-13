package com.example.saucedemo.tests;

import com.example.saucedemo.pages.InventoryPage;
import com.example.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortingTests extends BaseTest {

    @Test(description = "Сортировка товаров по имени Z-A")
    public void sortByNameDescending() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");

        inventoryPage.sortBy("za");
        List<String> actual = inventoryPage.getItemNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Comparator.reverseOrder());

        Assert.assertEquals(actual, expected,
                "Товары должны быть отсортированы по имени Z-A");
    }

    @Test(description = "Сортировка товаров по цене Low-High")
    public void sortByPriceAscending() {
        LoginPage loginPage = openLoginPage();
        InventoryPage inventoryPage = loginPage.loginAs("standard_user", "secret_sauce");

        inventoryPage.sortBy("lohi");
        List<Double> actual = inventoryPage.getItemPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Comparator.naturalOrder());

        Assert.assertEquals(actual, expected,
                "Цены должны быть отсортированы по возрастанию");
    }
}