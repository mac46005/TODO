package com.preciado.todo.core.common_visuals.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentAlignment: Alignment = Alignment.TopStart,
    background: Color = Color.Transparent,
    itemContent: @Composable (BoxScope.() -> Unit),
    ){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp).clickable{
            onClick()
                                          }
            .background(background),
        contentAlignment = contentAlignment
    ){
        itemContent(this)
        Divider(modifier = Modifier.align(Alignment.BottomCenter).padding(top = 25.dp))
    }
}

@Preview
@Composable
fun PreviewListItem(){
    ListItem(onClick = {}){
        Text(text = "Item Content")
    }
}