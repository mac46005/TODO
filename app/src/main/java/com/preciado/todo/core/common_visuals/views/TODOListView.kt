package com.preciado.todo.core.common_visuals.views

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun <T> TODOListView(
    backButtonVisible: Boolean = true,
    onBackButtonClicked: (() -> Unit) = {},
    onFloatingActionButtonClicked: (() -> Unit),
    list: List<T>,
    emptyListMessage: @Composable () -> Unit,
    itemView: @Composable (T) -> Unit,
){
    TODOTheme() {
        TODOMainView(
            backButtonVisible = backButtonVisible,
            onBackButtonClicked = onBackButtonClicked,
            floatingActionButtonEnabled = true,
            onFloatingActionButtonClicked = onFloatingActionButtonClicked
        ) {
            if(list.isEmpty()){
                emptyListMessage()
            }else{
                LazyColumn(
                    modifier = Modifier.padding(top = it.calculateTopPadding())
                ){
                    items(list){
                        itemView(it)
                    }
                }
            }

        }
    }

}

@Preview
@Composable
fun PreviewTODOListView(){
    TODOListView(
        onFloatingActionButtonClicked = { /*TODO*/ },
        list = listOf<TODOList>(TODOList(0, "Starred List")),
        emptyListMessage = { /*TODO*/ }) {item ->
        Text(text = item.name)
    }
}