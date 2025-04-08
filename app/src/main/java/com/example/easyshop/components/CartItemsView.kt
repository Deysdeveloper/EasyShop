package com.example.easyshop.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.easyshop.AppUtil
import com.example.easyshop.Model.ProductModel
import com.example.easyshop.ui.theme.GlobalNavigation
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.nio.file.WatchEvent

@Composable
fun CartItemView(modifier: Modifier,productId: String,qty:Long) {
    var product by remember {
        mutableStateOf(ProductModel())
    }

    LaunchedEffect(key1 = Unit) {
        Firebase.firestore.collection("data")
            .document("stock").collection("products")
            .document(productId).get().addOnCompleteListener {
                if (it.isSuccessful) {
                    val result = it.result.toObject(ProductModel::class.java)
                    if (result != null) {
                        product = result
                    }
                }
            }
    }
    var context = LocalContext.current
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                GlobalNavigation.navController.navigate("product-details/${product.id}")
            },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = product.images.firstOrNull(), contentDescription = product.title,
                modifier = Modifier.height(100.dp)
                    .width(100.dp)
            )
            Column(
                modifier = Modifier.padding(8.dp)
                    .weight(1f)
            ) {
                Text(
                    text = product.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(8.dp)
                )
                Text(
                    text = "$" + product.actualprice,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        AppUtil.removeItemFromCart(productId, context)
                    }) {
                        Text(text = "-", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                    Text(text = "$qty", fontSize = 16.sp)
                    IconButton(onClick = {
                        AppUtil.addItemToCart(productId, context)
                    }) {
                        Text(text = "+", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            IconButton(onClick = {
                AppUtil.removeItemFromCart(productId, context, removeAll = true)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove from cart"
                )
            }
        }



    }


}