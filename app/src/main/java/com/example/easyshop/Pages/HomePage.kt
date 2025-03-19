package com.example.easyshop.Pages

import androidx.compose.foundation.hoverable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.easyshop.ViewModel.AuthViewModel
import com.example.easyshop.components.BannerView
import com.example.easyshop.components.CategoriesView
import com.example.easyshop.components.HeaderView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun HomePage(modifier: Modifier) {
    Column(
        modifier.fillMaxSize()
            .padding(16.dp))
    {
        Spacer(modifier.height(100.dp))
        HeaderView(modifier)
        Spacer(modifier.height(10.dp))
        BannerView(modifier)
        Spacer(modifier.height(20.dp))
        Text(text = "Categories",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier.height(10.dp))
        CategoriesView(modifier)
    }
}