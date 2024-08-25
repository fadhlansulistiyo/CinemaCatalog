package com.fadhlansulistiyo.cinemacatalog.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.fadhlansulistiyo.cinemacatalog.ui.theme.CinemaCatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinemaCatalogTheme {
                CinLogApp()
            }
        }
    }
}