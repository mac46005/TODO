package com.preciado.todo.core.common_visuals.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    image: @Composable (() -> Unit)? = null,
    actions: @Composable (() -> Unit)? = null,
    mainColor: Color = Color.Transparent,
    mainPadding: Dp = 5.dp,
    mainHorizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    mainVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    innerColor: Color = Color.Transparent,
    innerPadding: Dp = 0.dp,
    innerWidth: Dp = 200.dp,
    innerHorizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    title: @Composable (RowScope.() -> Unit)
) {
    Row(
        modifier = modifier.background(mainColor).fillMaxWidth().padding(mainPadding),
        verticalAlignment = mainVerticalAlignment,
        horizontalArrangement = mainHorizontalArrangement
    ) {
        Row(
            modifier = Modifier.background(innerColor).width(innerWidth).padding(innerPadding),
            horizontalArrangement = innerHorizontalArrangement
        ) {
            if (image != null) {
                image()
            }

            title(this)
        }


        if (actions != null) {
            actions()
        }
    }
}


@Preview
@Composable
fun PreviewTobBar() {
    TopBar(
        image = { Text(text = "IMAGE") },
        actions = {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "ACTION", modifier = Modifier)
            }
        }
    ) {
        Text(text = "TITLE")
    }
}