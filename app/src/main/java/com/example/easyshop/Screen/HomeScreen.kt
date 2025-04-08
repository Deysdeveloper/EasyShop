package com.example.easyshop.Screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.easyshop.Pages.CartPage
import com.example.easyshop.Pages.FavoritePage
import com.example.easyshop.Pages.HomePage
import com.example.easyshop.Pages.ProfilePage
import com.example.easyshop.ViewModel.AuthViewModel

@Composable
fun HomeScreen(modifier: Modifier, navController: NavHostController, authViewModel: AuthViewModel = viewModel())
{
    val navItemList= listOf(
        NavItem(label = "Home", icon = Icons.Default.Home),
        NavItem(label = "Favorite", icon = Icons.Default.Favorite),
        NavItem(label = "Cart", icon = Icons.Default.ShoppingCart),
        NavItem(label = "Profile", icon = Icons.Default.Person),
    )
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                navItemList.forEachIndexed{index, navItem ->
                NavigationBarItem(
                    selected = index==selectedIndex,
                    onClick = {
                        selectedIndex = index
                    },
                    icon = { Icon(imageVector =navItem.icon , contentDescription =navItem.label )
                    },
                    label = {Text(text = navItem.label)
                    })
                }
            }
        }

    ){
        ContentScreen(modifier.padding(it),selectedIndex)

    }

}
@Composable
fun ContentScreen(modifier: Modifier,selectedIndex:Int)
{
    when(selectedIndex)
    {
        0-> HomePage(
            modifier = Modifier)
        1-> FavoritePage(modifier = Modifier)
        2->CartPage(modifier = Modifier)
        3->ProfilePage(modifier = Modifier)

    }
}
data class NavItem(
    val label:String,
    val icon:ImageVector
)