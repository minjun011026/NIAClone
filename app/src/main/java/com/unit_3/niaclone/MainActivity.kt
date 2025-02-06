package com.unit_3.niaclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.unit_3.niaclone.ui.NiaApp
import com.unit_3.niaclone.ui.theme.NIACloneTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NIACloneTheme {
                NiaApp()
            }
        }
    }
}