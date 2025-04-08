package com.example.easyshop.Pages

import android.R
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.request.Disposable
import com.example.easyshop.AppUtil
import com.example.easyshop.Model.UserModel
import com.example.easyshop.components.CartItemView
import com.example.easyshop.ui.theme.GlobalNavigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

@Composable

fun CartPage(modifier: Modifier)
{

    var userModel = remember {
        mutableStateOf(UserModel())
    }

    DisposableEffect(key1 = Unit){
       var listner= Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
            .addSnapshotListener {it,_->
                if(it!=null)
                {
                    val result=it.toObject(UserModel::class.java)?:UserModel()
                    if(result!=null)
                        userModel.value=result
                }

            }
        onDispose {
            listner.remove()
        }
    }

    Spacer(modifier.height(12.dp))
    Column(modifier.fillMaxSize().padding(16.dp))
    {
        //Text(text = userModel.value.toString())

        Text(
            text = "Your Cart",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn() {
            items(userModel.value.CartItems.toList(),key={it.first}) {(productId,qty)->
                CartItemView(modifier,productId = productId, qty = qty)
            }

        }
        ExtendedFloatingActionButton(onClick = {
            GlobalNavigation.navController.navigate("checkout")
        },
            modifier.fillMaxWidth().
            height(50.dp),) {
            Text(text="Checkout")
        }
    }
}

