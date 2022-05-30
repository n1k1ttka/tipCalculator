package com.example.tiptime

import android.app.Activity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    @get:Rule() //
    val activity = ActivityScenarioRule(MainActivity::class.java) // сообщает устройству о запуске действия

    @Test
    fun calculate_20_percent_tip() {
        /* TODO: Шаг 1 - поиск компонента пользовательского интерфейса для взаимодействия
                Ищем EditText (поле ввода цены) с помощью onView()
                 Функция onView() принимает параметр объекта ViewMatcher, который соответствует определенному критерию
                 Функция withID() возвращает ViewMatcher с переданным ему ID - R.id.cost_of_service_edit_text
                 onView() возвращает ViewInteraction, который
         */
        onView(withId(R.id.cost_of_service_edit_text)) // TODO: Проще говоря ввели 50 в поле ввода
            .perform(typeText("50.00")) // .perform() - для вызова? функции ввода текста

        onView(withId(R.id.calculate_button)) // TODO: Нажали на кнопку
            .perform(click())

        onView(withId(R.id.tip_result)) // TODO: Проверка на совпадение фактического содержимого с ожидаемым
            .check(matches(withText(containsString("$10.00"))))
    }
}