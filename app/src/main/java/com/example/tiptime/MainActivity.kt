package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{
            calculateTip()
        }

        binding.costOfServiceEditText.setOnEditorActionListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
        }
    }

    private fun calculateTip(): Unit {
        val stringInTextField = binding.costOfServiceEditText.text.toString() // перевели введеный текст (сумма) в строку
        val cost = stringInTextField.toDoubleOrNull() // строку в double
        if (cost == null) {
            //binding.tipResult.text = ""
            resultForm(0.0)
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.amazing -> 0.20
            R.id.good -> 0.15
            else -> 0.10
        }
        var tip = tipPercentage * cost  // высчитываем чаевые

        // включено ли округление в большую сторону?
        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip) // если да, то округляем
        }
        resultForm(tip)
    }
    private fun resultForm(tip: Double) {
        val formattedTip = NumberFormat.getCurrencyInstance().apply {
         //currency = Currency.getInstance("RUB")
        }
        // вызываем метод форматирования результата в зависимости от валюты?
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip.format(tip)) // передаём в параметр текст - строку, в которую помещаем переменную,хранящую результат после форматирования

    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean { // Проверка на нужность поддержания экр.клавы в открытом состоянии
        if (keyCode == EditorInfo.IME_ACTION_NEXT) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}