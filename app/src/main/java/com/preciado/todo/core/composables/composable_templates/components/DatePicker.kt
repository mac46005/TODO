package com.preciado.todo.core.composables.composable_templates.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import java.time.LocalDateTime

@Composable
fun DatePicker(
    dateText: MutableState<String>
){
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

    var selectedDateText by remember{dateText}
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            selectedDateText = "$selectedYear-$selectedMonth-$selectedDayOfMonth"
        }, year, month, dayOfMonth
    )

    Column() {
        Text(text = if(selectedDateText.isNotEmpty()) selectedDateText else "Select a date")
        Button(onClick = { datePicker.show() }) {
            Text(text = "Select a date")
        }
    }
}