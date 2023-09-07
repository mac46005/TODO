package com.preciado.todo.core.composables.composable_templates.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> FormTemplate(
    modifier: Modifier = Modifier,
    formColumnModifier: Modifier = Modifier,
    submitButton: @Composable (BoxScope.() -> Unit),
    formContent: @Composable (ColumnScope.() -> Unit)
){
    Box(modifier = modifier){
        Column(modifier = formColumnModifier) {
            formContent(this)
        }
        submitButton(this)
    }
}