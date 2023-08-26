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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.MutableLiveData
import com.preciado.todo.R

private const val TAG = "BottomBar"

@Composable
fun BottomBar(
    listId: Int,
    onIncompleteButtonClicked: ()->Unit,
    onCompleteButtonClicked: ()->Unit,
    onAddListItemClicked: () -> Unit
) {


    val isIncompleteButtonEnabled = remember {
        mutableStateOf(false)
    }

    Log.i(TAG, "BottomBar: listId: $listId")

    val isCompleteButtonEnabled = remember {
        mutableStateOf(false)
    }
    if(listId != 0){
        isIncompleteButtonEnabled.value = true
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
                        isIncompleteButtonEnabled.value = isIncompleteButtonEnabled.value != true
                        isCompleteButtonEnabled.value = isCompleteButtonEnabled.value == false
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
                        isIncompleteButtonEnabled.value = isIncompleteButtonEnabled.value != true
                        isCompleteButtonEnabled.value = isCompleteButtonEnabled.value == false

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
                onClick = onAddListItemClicked,
                enabled = listId != 0
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add new Task"
                )
            }
        }
    }
}


