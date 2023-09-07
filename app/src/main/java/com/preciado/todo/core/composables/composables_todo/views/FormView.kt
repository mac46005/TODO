package com.preciado.todo.core.composables.composables_todo.views

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.preciado.todo.core.composables.composable_templates.views.FormTemplate
import com.preciado.todo.core.composables.composables_todo.components.Header
import com.preciado.todo.data.CRUDEnum

@Composable
fun <T> FormView(
    navController: NavController,
    header: String,
    crudOperation: CRUDEnum = CRUDEnum.CREATE,
    model: T? = null,
    onBackButtonClicked: () -> Unit,
    submitButton: @Composable (BoxScope.() -> Unit),
    formModifier: Modifier = Modifier,
    formColumnModifier: Modifier = Modifier,
    formContent: @Composable (ColumnScope.() -> Unit)
){
    TODOMainView(
        backButtonVisible = true,
        onBackButtonClicked = onBackButtonClicked,

    ) { pv ->
        Column (
            modifier = Modifier.padding(top = pv.calculateTopPadding())
                ) {
            Header(header = header)
            Divider()
            FormTemplate<T>(
                modifier = formModifier,
                submitButton = submitButton,
                formColumnModifier = formColumnModifier
            ){
                formContent(this)
            }
        }
    }
}

@Preview
@Composable
fun PreviewFormView(){
}