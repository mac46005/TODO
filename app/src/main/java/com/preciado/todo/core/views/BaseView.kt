package com.preciado.todo.core.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.preciado.todo.core.common_visuals.views.TBarView
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun BaseView(
    modifier: Modifier = Modifier, mainContent: @Composable (ColumnScope.() -> Unit)
) {
    TODOTheme(
    ) {
        Surface(
            color = MaterialTheme.colorScheme.background
        ) {
            TBarView(
                modifier = modifier,
                topBarTitle = {
                    Text(text = buildAnnotatedString {
                        pushStyle(
                            SpanStyle(
                                fontSize = 35.sp
                            )
                        )
                        append("TODO")
                        pop()
                    })
                },
                topBarMainHorizontalArrangement = Arrangement.Center,
                topBarInnerHorizontalArrangement = Arrangement.Center
            ) {
                mainContent(this)
            }
        }
    }

}

@Preview
@Composable
fun PreviewBaseView() {
    BaseView {

    }
}