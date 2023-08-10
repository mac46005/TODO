package com.preciado.todo.features.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.preciado.todo.R
import kotlin.math.round

@Composable
fun CompletedTasksTab(
    onClick: ()->Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Green, shape = CircleShape.also{ roundedCornerShape -> round(.3) })
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.padding(start = 5.dp).width(200.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(text = "Tasks completed")
                Text(text = "4/5")
            }

            Button(
                onClick = onClick,
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_expand_24),
                    contentDescription = "Expand completed tasks"
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewCompletedTasksTab() {
    CompletedTasksTab(
        onClick = {

        }
    )
}