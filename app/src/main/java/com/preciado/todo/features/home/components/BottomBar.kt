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
import androidx.compose.runtime.MutableState
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
    listId: Int = 0,
    onIncompleteButtonClicked: () -> Unit,
    onCompleteButtonClicked: () -> Unit,
) {
    var listIdState = remember {
        mutableStateOf(listId)
    }
    var isIncompleteButtonEnabled = remember {
        mutableStateOf(false)
    }
    var isCompleteButtonEnabled = remember {
        mutableStateOf(false)
    }
//    if(listId != 0){
//        vm.setIncompleteButtonEnabled(true)
//    }
    if(listId != 0 && !listIdState.value.equals(listId)){
        listIdState.value = listId
        isCompleteButtonEnabled.value = true
        isIncompleteButtonEnabled.value = false
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
                        toggleEnablers(isIncompleteButtonEnabled, isCompleteButtonEnabled)
                    },
                    enabled = isIncompleteButtonEnabled.value
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_close_24),
                        contentDescription = "Incomplete Tasks"
                    )
                }

                Button(
                    onClick = {
                        onCompleteButtonClicked()
                        toggleEnablers(isIncompleteButtonEnabled,isCompleteButtonEnabled)
                    },
                    enabled = isCompleteButtonEnabled.value
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
                enabled = listIdState.value != 0
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add new Task"
                )
            }
        }
    }
}




fun toggleEnablers(b1: MutableState<Boolean>, b2: MutableState<Boolean>){
    b1.value = b1.value == false
    b2.value = b2.value != true
}


