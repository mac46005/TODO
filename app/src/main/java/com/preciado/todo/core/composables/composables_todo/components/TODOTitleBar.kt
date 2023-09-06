package com.preciado.todo.core.composables.composables_todo.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.preciado.todo.R
import com.preciado.todo.core.composables.composable_templates.components.TitleBarTemplate
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun TODOTitleBar(
    backButtonVisible: Boolean = true,
    onBackButtonClicked: (() -> Unit)? = null,
    moreOptions: @Composable (BoxScope.(Modifier) -> Unit) = {}
){
    TODOTheme() {
        TitleBarTemplate(
            backButton = { modifier ->
                Button(
                    modifier = modifier
                        .alpha(if (backButtonVisible) 1f else 0f),
                    onClick = onBackButtonClicked?: { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back Button")
                }
            },
            title = { modifier ->
                Text(
                    modifier = modifier,
                    text = "TODO", fontSize = 70.sp
                )
            },
            moreOptions = { modifier ->
                moreOptions(modifier)
            }
        )
    }
}

@Preview
@Composable
fun PreviewTODOTitleBar(){
    TODOTitleBar()
}