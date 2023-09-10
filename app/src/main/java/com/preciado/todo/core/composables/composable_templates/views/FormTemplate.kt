package com.preciado.todo.core.composables.composable_templates.views

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun <T> FormTemplate(
    modifier: Modifier = Modifier,
    formColumnModifier: Modifier = Modifier,
    submitButton: @Composable (BoxScope.() -> Unit),
    formContent: @Composable (ColumnScope.() -> Unit)
){
    val scrollState = rememberScrollState()
    Box(modifier = modifier.fillMaxSize()){
        Column(modifier = formColumnModifier.scrollable(scrollState, orientation = Orientation.Vertical)) {
            formContent(this)
        }
        submitButton(this)
    }
}