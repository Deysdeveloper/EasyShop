package com.example.easyshop.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.easyshop.R

@Composable
fun AuthScreen(modifier: Modifier, navController: NavHostController)
{
    Column(modifier.fillMaxSize().
    padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(painter = painterResource(R.drawable.lockscreen),
            contentDescription = "banner",
            modifier=Modifier.fillMaxWidth()
                .height(300.dp))

        Spacer(modifier.height(5.dp))

        Text(text = "Welcome to EasyShop",
            style = TextStyle(
                fontSize =30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center


            )
        )
        Spacer(modifier.height(1.dp))

        Text(text = "Best Ecommerce app for all your needs",
            style = TextStyle(
                textAlign = TextAlign.Center


            )
        )
       // Spacer(modifier.height(1.dp))

        Button(onClick = { navController.navigate("login")},
            modifier.fillMaxWidth()
            .height(60.dp)){
            Text(text = "Login",
                fontSize = 20.sp)

        }
        OutlinedButton(onClick = { navController.navigate("signup")},
            modifier.fillMaxWidth()
            .height(60.dp)){
            Text(text = "SignUp",
                fontSize = 20.sp)

        }
    }
}