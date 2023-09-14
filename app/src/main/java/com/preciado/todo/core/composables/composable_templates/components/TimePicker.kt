package com.preciado.todo.core.composables.composable_templates.components

import android.app.TimePickerDialog
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Calendar

@Composable
fun TimePicker(
    timeText: MutableState<String>
) {
    val context = LocalContext.current
    var selectedTimeText by remember { timeText }
    val calendar = Calendar.getInstance()
    val hour = calendar[Calendar.HOUR_OF_DAY]
    val minute = calendar[Calendar.MINUTE]

    val timePicker = TimePickerDialog(
        context,
        { _, selectedHour: Int, selectedMinute: Int ->
            selectedTimeText = "T$selectedHour:$selectedMinute:00.00"
        }, hour, minute, false
    )

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.padding(start = 5.dp),
                shape = RectangleShape,
                onClick = { timePicker.show() }
            ) {
                Text(text = "Select a time")
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = if (selectedTimeText.isNotEmpty()) selectedTimeText else "Select a time",
                textAlign = TextAlign.Center
            )

        }
        Divider()
    }
}