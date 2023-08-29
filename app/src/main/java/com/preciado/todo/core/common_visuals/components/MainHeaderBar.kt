package com.preciado.todo.core.common_visuals.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.preciado.todo.R

@Composable
fun MainHeaderBar(
    navController: NavController,
    backButtonEnabled: Boolean = true,
    popBackDestination: String = ""
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.Center
    ){
        if (backButtonEnabled == true) {
            Button(
                modifier = Modifier.align(Alignment.CenterStart),
                onClick = {
                    navController.popBackStack(popBackDestination,false,false)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_arrow_back_24), contentDescription = "Back Button")
            }
        }
        Text(
            text = "TODO",
            fontSize = 60.sp,
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier.align(Alignment.CenterEnd),
            //TODO add navigation to edit user preferences
            onClick = { navController.navigate("") },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {
            Icon(painter = painterResource(id = R.drawable.baseline_attribution_24), contentDescription = "")
        }
    }
}

@Preview
@Composable
fun PreviewMainTitleBar(){
    MainHeaderBar(navController = rememberNavController())
}