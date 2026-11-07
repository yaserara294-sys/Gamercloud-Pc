
package com.gamercloud.pc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor =
            android.graphics.Color.rgb(10, 16, 28)

        window.navigationBarColor =
            android.graphics.Color.rgb(10, 16, 28)

        window.setSoftInputMode(
            android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
        )

        setContent {
            MaterialTheme(
                colorScheme = darkColorScheme(
                    primary = Color(0xFF00C8FF),
                    secondary = Color(0xFF168DFF),
                    background = Color(0xFF0A101C),
                    surface = Color(0xFF111C2B)
                )
            ) {
                MainNavigation()
            }
        }
    }
}
