
package com.gamercloud.pc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = android.graphics.Color.rgb(10, 16, 28)
        window.navigationBarColor = android.graphics.Color.rgb(10, 16, 28)

        setContent {
            MaterialTheme(
                colorScheme = androidx.compose.material3.darkColorScheme(
                    primary = Color(0xFF00C8FF),
                    background = Color(0xFF0A101C),
                    surface = Color(0xFF111C2B)
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0A101C)
                ) {
                    GamerCloudApp()
                }
            }
        }
    }
}
