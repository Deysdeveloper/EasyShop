package com.example.easyshop.Model

import com.google.firebase.Timestamp

data class OrderModel(
    val id:String="",
    val userId: String,
    val date: Timestamp=Timestamp.now(),
    val items:Map<String, Long> = emptyMap(),
    val status: String="",
    val address:String="",

)
