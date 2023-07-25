package com.preciado.todo.core.common_visuals.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.preciado.todo.core.common_visuals.components.TopBar

@Composable
fun TBarView(
    modifier: Modifier = Modifier,
    contentColumnModifier: Modifier = Modifier.padding(5.dp).fillMaxSize(),
    topBarModifier: Modifier = Modifier,
    topBarImage: @Composable (() -> Unit)? = null,
    topBarActions: @Composable (() -> Unit)? = null,
    topBarMainColor: Color = Color.Transparent,
    topBarMainPadding: Dp = 5.dp,
    topBarMainHorizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    topBarMainVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    topBarInnerColor: Color = Color.Transparent,
    topBarInnerPadding: Dp = 0.dp,
    topBarInnerWidth: Dp = 200.dp,
    topBarInnerHorizontalArrangement: Arrangement.Horizontal = Arrangement.SpaceBetween,
    topBarInnerVerticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    topBarTitle: @Composable (RowScope.() -> Unit),
    mainContent: @Composable (ColumnScope.() -> Unit)
){
    Column(
        modifier.fillMaxSize()
    ) {
        TopBar(
            modifier = topBarModifier,
            image = topBarImage,
            actions = topBarActions,
            mainColor = topBarMainColor,
            mainPadding =  topBarMainPadding,
            mainHorizontalArrangement = topBarMainHorizontalArrangement,
            mainVerticalAlignment = topBarMainVerticalAlignment,
            innerColor = topBarInnerColor,
            innerPadding = topBarInnerPadding,
            innerWidth = topBarInnerWidth,
            innerHorizontalArrangement = topBarInnerHorizontalArrangement,
            innerVerticalAlignment = topBarInnerVerticalAlignment
        ) {
            topBarTitle(this)
        }
        Column(modifier = contentColumnModifier) {
            mainContent(this)
        }
    }
}

@Preview
@Composable
fun PreviewTBarView(){
    TBarView(
        topBarTitle = { Text(text = "TITLE")},
    ) {
        Text(text = "MAIN CONTENT")
    }
}