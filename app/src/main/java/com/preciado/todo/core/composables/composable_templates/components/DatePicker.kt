package com.preciado.todo.core.composables.composable_templates.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
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

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.padding(start = 5.dp),
                onClick = { datePicker.show() },
                shape = RectangleShape
            ) {
                Text(text = "Select a date")
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if(selectedDateText.isNotEmpty()) selectedDateText else "Select a date",
                textAlign = TextAlign.Center
            )
        }
        Divider()
    }
}