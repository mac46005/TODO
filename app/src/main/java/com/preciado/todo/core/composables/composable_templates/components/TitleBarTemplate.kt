package com.preciado.todo.core.composables.composable_templates.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TitleBarTemplate(
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center,
    propagateMinConstraints: Boolean = false,
    backButton:@Composable (BoxScope.(Modifier) -> Unit) = {},
    title: @Composable (BoxScope.(Modifier) -> Unit) = {},
    moreOptions: @Composable (BoxScope.(Modifier) -> Unit) = {},
){
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = contentAlignment,
        propagateMinConstraints = propagateMinConstraints
    ){
        backButton(Modifier.align(Alignment.CenterStart))
        title(Modifier.align(Alignment.Center))
        moreOptions(Modifier.align(Alignment.CenterEnd))
    }
}