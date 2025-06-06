import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.easyshop.AppUtil
import com.example.easyshop.R
import com.example.easyshop.ViewModel.AuthViewModel

@Composable
fun SignupScreen(modifier: Modifier,navController: NavHostController, authViewModel: AuthViewModel=viewModel()) {

    var email by remember {
        mutableStateOf("")
    }
    var name by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    val context=LocalContext.current
    var isloading by remember {
        mutableStateOf(false)
    }
    Column(
        modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(
            text = "The best shopping App",
            modifier = modifier.fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Create Your account here",
            modifier = Modifier.fillMaxWidth(),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )

        Image(
            painter = painterResource(R.drawable.whatsapp_image_2025_02_07_at_7_40_39_am),
            contentDescription = "banner",
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email Address") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text(text = "Name") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation()
            )
        }
        Button(onClick = {
            isloading=true
            authViewModel.signup(email,name,password){sucess,errorMesaage->
                if(sucess)
                {
                    isloading=false
                    navController.navigate("home"){
                        popUpTo("auth"){
                            inclusive=true
                        }
                    }
                }
                else{
                    isloading=false
                    AppUtil.showToast(context,errorMesaage?:"Something went wrong")
                }

            }
        },
            enabled = !isloading,
            modifier = modifier.fillMaxWidth()
                .height(60.dp)){
            Text(text = if(isloading) "Creating Account" else "SignUp",
                fontSize = 20.sp)

        }
    }
}