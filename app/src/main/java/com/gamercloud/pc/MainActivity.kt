package com.gamercloud.pc

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pantalla = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(12, 18, 32))
        }

        val titulo = TextView(this).apply {
            text = "GAME CLOUD PC"
            textSize = 30f
            setTextColor(Color.WHITE)
            gravity = Gravity.CENTER
        }

        val descripcion = TextView(this).apply {
            text = "\nTu PC gamer en la nube\n\nWindows 11\nAlmacenamiento: 100 GB\nModo: Gaming\n\nPróximamente: conexión al servidor"
            textSize = 18f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        pantalla.addView(titulo)
        pantalla.addView(descripcion)

        setContentView(pantalla)
    }
}
