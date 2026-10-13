# UI autotests for SauceDemo

Maven + Java 17 + Selenium WebDriver + TestNG.

## Запуск

mvn clean test

## Сценарии

- Позитивная авторизация (standard_user)
- Негативная авторизация (locked_out_user)
- Добавление и удаление товара в корзине
- Оформление заказа и валидация полей
- Сортировка товаров (Z-A, Low-High)

## Структура

- src/test/java/com/example/saucedemo/pages — Page Object
- src/test/java/com/example/saucedemo/tests — тесты
- target/surefire-reports — отчёты

## Ссылки

- Репозиторий: https://github.com/Ferchick/ui-tests-saucedemo-Ivanov
- Ветка: feature/Group_Ivanov_saucedemo