package com.example.easyshop.ui.theme

import SignupScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easyshop.Pages.CategoryProductsPage
import com.example.easyshop.Pages.CheckoutPage
import com.example.easyshop.Pages.HomePage
import com.example.easyshop.Pages.ProductDetailsPage
import com.example.easyshop.Screen.AuthScreen
import com.example.easyshop.Screen.HomeScreen
import com.example.easyshop.Screen.LoginScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun AppNavigation(modifier: Modifier)
{
    val navController = rememberNavController()
    GlobalNavigation.navController= navController

    val isLoggedIn=Firebase.auth.currentUser!=null
    val firstpage=if(isLoggedIn) "home" else "auth"
    NavHost(navController = navController, startDestination = firstpage) {

        composable("auth") {
            AuthScreen(modifier, navController)
        }
        composable("login") {
            LoginScreen(modifier, navController)
        }
        composable("signup") {
            SignupScreen(modifier, navController)

        }
        composable("home") {
            HomeScreen(modifier, navController)

        }
        composable("category-products/{categoryId}") {
            var categoryId = it.arguments?.getString("categoryId")
            CategoryProductsPage(modifier, categoryId ?: "")

        }
        composable("product-details/{productId}") {
            var productId = it.arguments?.getString("productId")
            ProductDetailsPage(modifier, productId ?: "")
        }
        composable("checkout") {
            CheckoutPage(modifier)
        }


        }

}
object GlobalNavigation{
    lateinit var navController: NavHostController
}