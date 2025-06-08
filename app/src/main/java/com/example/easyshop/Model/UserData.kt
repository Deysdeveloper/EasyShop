package com.example.easyshop.Model

data class UserModel(
    val name:String="",
    val email:String="",
    val uid:String="",
    val CartItems:Map<String, Long> = emptyMap(),
    val address:String="",
)
