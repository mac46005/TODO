package com.preciado.todo.core.composables_todo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.preciado.todo.core.composables_todo.components.Header
import com.preciado.todo.core.models.TODOList
import com.preciado.todo.ui.theme.TODOTheme

@Composable
fun <T> TODOListView(
    backButtonVisible: Boolean = true,
    onBackButtonClicked: (() -> Unit) = {},
    onFloatingActionButtonClicked: (() -> Unit),
    list: List<T>,
    emptyListMessage: @Composable () -> Unit,
    extraInfo: @Composable (() -> Unit) = {},
    itemView: @Composable (T) -> Unit,
){
    TODOTheme() {
        TODOMainView(
            backButtonVisible = backButtonVisible,
            onBackButtonClicked = onBackButtonClicked,
            floatingActionButtonEnabled = true,
            onFloatingActionButtonClicked = onFloatingActionButtonClicked
        ) {
            Column(
                modifier = Modifier.padding(top = it.calculateTopPadding())
            ){
                Header(header = "Your Lists")
                extraInfo()
                Divider(modifier = Modifier.fillMaxWidth())
                if(list.isEmpty()){
                    emptyListMessage()
                }else{
                    LazyColumn(

                    ){
                        items(list){
                            itemView(it)
                        }
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
        extraInfo = { Text(text = "Add extra info here")},
        emptyListMessage = { /*TODO*/ }) {item ->
        Text(text = item.name)
    }
}