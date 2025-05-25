package com.example.easyshop

import android.app.Activity
import android.content.Context
import android.widget.Toast
import com.example.easyshop.ui.theme.GlobalNavigation
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.razorpay.Checkout
import org.json.JSONObject

object AppUtil {

    fun showToast(context: Context,message:String)
    {
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
    }
    fun addItemToCart(productId: String, context: Context) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            showToast(context, "User not logged in")
            return
        }

        val userDoc = Firebase.firestore.collection("users").document(currentUser.uid)

        userDoc.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val currentCart = task.result.get("CartItems") as? Map<String, Long> ?: emptyMap()
                val currentQuantity = currentCart[productId] ?: 0
                val updatedQuantity = currentQuantity + 1

                val updatedCart = mapOf("CartItems.$productId" to updatedQuantity)

                userDoc.update(updatedCart).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(context, "Item added to cart")
                    } else {
                        showToast(context, "Something went wrong")
                    }
                }
            } else {
                showToast(context, "Failed to load user cart")
            }
        }
    }
    fun removeItemFromCart(productId: String, context: Context,removeAll:Boolean=false) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            showToast(context, "User not logged in")
            return
        }

        val userDoc = Firebase.firestore.collection("users").document(currentUser.uid)

        userDoc.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val currentCart = task.result.get("CartItems") as? Map<String, Long> ?: emptyMap()
                val currentQuantity = currentCart[productId] ?: 0
                val updatedQuantity = currentQuantity - 1

                val updatedCart =
                    if(updatedQuantity<=0 || removeAll)
                        mapOf("CartItems.$productId" to FieldValue.delete())
                    else
                    mapOf("CartItems.$productId" to updatedQuantity)

                userDoc.update(updatedCart).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showToast(context, "Item removed from cart")
                    } else {
                        showToast(context, "Something went wrong")
                    }
                }
            } else {
                showToast(context, "Failed to load user cart")
            }
        }
    }

    fun getDiscountPercentage():Float
    {
        return 10.0f
    }
    fun getTaxPercentage():Float
    {
        return 18.0f

    }
    fun razorpayapikey(): String
    {
        return "rzp_test_y9yNnlbWdtFFsm"
    }
    fun StartPayments(amount:Float)
    {
        val checkout = Checkout()
        checkout.setKeyID(razorpayapikey())

        val options= JSONObject()
        options.put("name","EasyShop")
        options.put("description","Pay Now")
        options.put("amount",(amount*100))
        options.put("currency","INR")

        checkout.open(GlobalNavigation.navController.context as Activity,options)
    }
}