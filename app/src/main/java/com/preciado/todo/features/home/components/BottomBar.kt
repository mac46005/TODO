package com.preciado.todo.features.home.components

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.preciado.todo.R
import com.preciado.todo.data.CRUDEnum
import com.preciado.todo.features.home.core.BottomBarViewModel

private const val TAG = "BottomBar"

@Composable
fun BottomBar(
    navController: NavController,
    listId: Int,
    onIncompleteButtonClicked: () -> Unit,
    onCompleteButtonClicked: () -> Unit,
    vm: BottomBarViewModel = hiltViewModel()
) {
    //TODO Make ui view changes of this value when user presses new list item
    val listIdState by vm.listId.observeAsState()

    val isIncompleteButtonEnabled by vm.isIncompleteTaskButtonEnabled.observeAsState()
    val isCompleteButtonEnabled by vm.isCompleteTaskButtonEnabled.observeAsState()


//    if(listId != 0){
//        vm.setIncompleteButtonEnabled(true)
//    }
    if(listId == 0){
        vm.setListId(listId)
        vm.setIncompleteButtonEnabled(false)
        vm.setCompleteButtonEnabled(false)
    }
    if(listId != 0 && vm.isListIdNew(listId)){
        vm.setListId(listId)
        vm.setIncompleteButtonEnabled(true)
        vm.setCompleteButtonEnabled(false)
    }

    BottomAppBar(containerColor = MaterialTheme.colorScheme.primaryContainer) {


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Button(
                    onClick = {
                        onIncompleteButtonClicked()
                        vm.toggleButtons()
                    },
                    enabled = isIncompleteButtonEnabled!!
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Incomplete Tasks"
                    )
                }

                Button(
                    onClick = {
                        onCompleteButtonClicked()
                        vm.toggleButtons()
                    },
                    enabled = isCompleteButtonEnabled!!
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_check_24),
                        contentDescription = "Completed Tasks"
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate("add_edit_list_task/${CRUDEnum.CREATE.ordinal}/${listId}/0")
                },
                enabled = listIdState != 0
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add new Task"
                )
            }
        }
    }
}


