
package com.gamercloud.pc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GamerCloudApp() {
    val background = Color(0xFF0A101C)
    val cyan = Color(0xFF00C8FF)

    var connected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(background)
            .padding(22.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "GAMER CLOUD PC",
            color = Color.White,
            fontSize = 27.sp,
            fontWeight = FontWeight.ExtraBold
        )

        Text(
            text = "YOUR GAMES. YOUR CLOUD.",
            color = cyan,
            fontSize = 11.sp,
            letterSpacing = 2.sp
        )

        Spacer(modifier = Modifier.height(55.dp))

        Text(
            text = "TU PC EN LA NUBE",
            color = Color.White,
            fontSize = 23.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = if (connected) "Servidor conectado" else "Esperando conexión",
            color = if (connected) Color.Green else Color.LightGray
        )

        Spacer(modifier = Modifier.height(35.dp))

        Button(
            onClick = { connected = !connected },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp),
            shape = RoundedCornerShape(18.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = cyan
            )
        ) {
            Text(
                text = if (connected) "DESCONECTAR" else "JUGAR",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "SERVIDORES  •  MIS JUEGOS  •  AJUSTES",
            color = Color.LightGray,
            fontSize = 11.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "GAMER CLOUD PC  •  VERSION 1.0",
            color = Color.Gray,
            fontSize = 10.sp
        )
    }
}
