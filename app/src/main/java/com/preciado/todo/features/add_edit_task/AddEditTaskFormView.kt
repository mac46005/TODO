package com.preciado.todo.features.add_edit_task

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composable_templates.components.DatePicker
import com.preciado.todo.core.composables.composable_templates.components.TimePicker
import com.preciado.todo.core.composables.composable_templates.components.TransparentTextField
import com.preciado.todo.core.composables.composables_todo.views.FormView
import com.preciado.todo.core.models.app_models.models.Task
import com.preciado.todo.core.models.app_models.models.TaskFrequency
import com.preciado.todo.data.CRUD_Operation
import com.preciado.todo.features.add_edit_task.core.AddEditTaskFormVM
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditTaskFormView(
    navController: NavController,
    crudOperation: CRUD_Operation = CRUD_Operation.CREATE,
    task: Task,
    vm: AddEditTaskFormVM? = hiltViewModel()
    ){

    vm!!.onLoad(navController,crudOperation, task)

    FormView<Task>(
        navController = navController,
        header = vm.title,
        onBackButtonClicked = {
            vm.onBackButtonClicked()
        },
        submitButton = {
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                vm.submitForm()
                }
            ) {
                Text(text = "Submit")
            }
        }
    ) {

        val nameState by vm.name.observeAsState()
        TransparentTextField(
            value = nameState!!,
            onValueChange = { vm.onNameChange(it) },
            placeHolder = "Name of task"
        )

        val detailsState by vm.details.observeAsState()
        TransparentTextField(
            value = detailsState!!,
            onValueChange = { vm.onDetailsChange(it)},
            placeHolder = "Place any details for completing this task",
            maxLines = 10,
            singleLine = false
        )

        DatePicker(dateText = vm.dateDue)

        TimePicker(timeText = vm.timeDue)


        val taskFrequency by vm.taskFrequency.observeAsState()
        var expandedState = remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            expandedState.value = true
        }) {
            Text(text = taskFrequency!!.name)
        }
        DropdownMenu(expanded = expandedState.value, onDismissRequest = { expandedState.value = false }) {
            for(e in TaskFrequency.values()){
                DropdownMenuItem(text = {
                    Text(text = e.name)
                }, onClick = {
                    vm.onTaskFrequencyChange(e)
                    expandedState.value = false
                })
            }
        }
    }
}